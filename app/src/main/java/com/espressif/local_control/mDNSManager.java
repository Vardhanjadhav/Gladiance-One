package com.espressif.local_control;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.text.TextUtils;
import android.util.Log;

import com.espressif.AppConstants;
import com.espressif.ui.activities.EspApplication;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class mDNSManager {
    private static final String TAG = mDNSManager.class.getSimpleName();

    private static mDNSManager mdnsManager;

    private String serviceType;

    private Context context;
    private mDNSEvenListener listener;

    // Declare DNS-SD related variables for service discovery
    private NsdManager mNsdManager;
    private NsdManager.ResolveListener resolveListener;
    private NsdManager.DiscoveryListener discoveryListener;
    private AtomicBoolean resolveListenerBusy = new AtomicBoolean(false);
    private ConcurrentLinkedQueue<NsdServiceInfo> pendingNsdServices = new ConcurrentLinkedQueue<NsdServiceInfo>();
    private List<NsdServiceInfo> resolvedNsdServices = Collections.synchronizedList(new ArrayList<NsdServiceInfo>());

    private EspApplication espApp;

    public static mDNSManager getInstance(Context context, String serviceType, mDNSEvenListener listener, EspApplication eApp) {

        if (mdnsManager == null) {
            mdnsManager = new mDNSManager(context, serviceType, listener, eApp);
        }
        return mdnsManager;
    }

    private mDNSManager(Context context, String serviceType, mDNSEvenListener listener, EspApplication eApp) {
        this.context = context;
        this.listener = listener;
        this.serviceType = serviceType;
        this.espApp = eApp;
        this.mNsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
    }

    // Initialize Listeners
    public void initializeNsd() {
        Log.d(TAG, "Initialize Network service discovery");
        // Initialize only resolve listener
        initializeResolveListener();
    }

    // Start discovering services on the network
    public void discoverServices() {

        Log.d(TAG, "Discover Services");
        // Cancel any existing discovery request
        stopDiscovery();

        Log.d(TAG, "discover Services Started.");
        initializeDiscoveryListener();

        // Start looking for available audio channels in the network
        mNsdManager.discoverServices(serviceType, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
    }

    // Stop DNS-SD service discovery
    public void stopDiscovery() {

        Log.d(TAG, "Stop Discovery");
        if (discoveryListener != null) {
            try {
                mNsdManager.stopServiceDiscovery(discoveryListener);
            } finally {
            }
            discoveryListener = null;
        }
    }

    private void initializeDiscoveryListener() {

        // Instantiate a new DiscoveryListener
        discoveryListener = new NsdManager.DiscoveryListener() {

            // Called as soon as service discovery begins.
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.e(TAG, "Service discovery started : " + regType);
            }

            @Override
            public void onServiceFound(NsdServiceInfo serviceInfo) {

                // A service was found! Do something with it
                Log.e(TAG, "Service discovery success : " + serviceInfo);

                if (serviceInfo.getServiceType().equals(serviceType)) {

                    // If the resolver is free, resolve the service to get all the details
                    if (resolveListenerBusy.compareAndSet(false, true)) {

                        mNsdManager.resolveService(serviceInfo, resolveListener);

                    } else {

                        // Resolver was busy. Add the service to the list of pending services
                        // But check if it is already exist in queue or not.

                        String serviceName = serviceInfo.getServiceName();
                        Iterator iterator = pendingNsdServices.iterator();
                        boolean isExist = false;

                        while (iterator.hasNext()) {
                            NsdServiceInfo nsdServiceInfo = (NsdServiceInfo) iterator.next();
                            if (nsdServiceInfo.getServiceName().equals(serviceName)) {
                                isExist = true;
                                break;
                            }
                        }

                        if (!isExist) {
                            pendingNsdServices.add(serviceInfo);
                        } else {
                            Log.e(TAG, "Service is already available in queue");
                        }
                    }

                } else {
                    Log.e(TAG, "Unknown Service Type: " + serviceInfo.getServiceType());
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
                Log.e(TAG, "Service lost : " + service);

                String serviceName = service.getServiceName();
                espApp = new EspApplication(context.getApplicationContext());
//                EspApplication espApp = new EspApplication(context.getApplicationContext());
                Log.i(TAG, "Local device list size before remove : " + espApp.localDeviceMap.size());
                Iterator<Map.Entry<String, EspLocalDevice>> itr = espApp.localDeviceMap.entrySet().iterator();

                while (itr.hasNext()) {

                    // Get the entry at this iteration
                    Map.Entry<String, EspLocalDevice> entry = itr.next();
                    String nodeId = entry.getKey();
                    EspLocalDevice device = entry.getValue();

                    // Check if this value is the required value
                    if (device.getServiceName().equals(serviceName)) {
                        Log.e(TAG, "Service Name Matched. Removed lost service");
                        // Remove this entry from HashMap
                        itr.remove();
                    }
                }

                Log.i(TAG, "Local device list size after remove : " + espApp.localDeviceMap.size());

                // If the lost service was in the queue of pending services, remove it
                Iterator<NsdServiceInfo> iterator = pendingNsdServices.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getServiceName().equals(service.getServiceName())) {
                        iterator.remove();
                    }
                }

                // If the lost service was in the list of resolved services, remove it
                synchronized (resolvedNsdServices) {
                    iterator = resolvedNsdServices.iterator();
                    while (iterator.hasNext()) {
                        if (iterator.next().getServiceName().equals(service.getServiceName())) {
                            iterator.remove();
                        }
                    }
                }
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.e(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                stopDiscovery();
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                mNsdManager.stopServiceDiscovery(this);
            }
        };
    }

    // Instantiate DNS-SD resolve listener to get extra information about the service
    private void initializeResolveListener() {

        resolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {

                // Called when the resolve fails. Use the error code to debug.
                Log.e(TAG, "Resolve failed " + errorCode);

                // Process the next service waiting to be resolved
                resolveNextInQueue();
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {

                Log.d(TAG, "Resolve Succeeded. " + serviceInfo.getHost());

                // Register the newly resolved service into our list of resolved services
                resolvedNsdServices.add(serviceInfo);

                // Process the newly resolved service
                // Obtain port and IP
                InetAddress hostAddress = serviceInfo.getHost();
                int hostPort = serviceInfo.getPort();

                Log.d(TAG, "Host address : " + hostAddress + " and port : " + hostPort);
                Map<String, byte[]> attr = serviceInfo.getAttributes();
                HashMap<String, String> endPointList = new HashMap<>();
                String nodeId = "";

                for (Map.Entry<String, byte[]> entry : attr.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    Log.i(TAG, "Key : " + key);
                    Log.i(TAG, "Value : " + new String(value));

                    if (key.equals(AppConstants.KEY_NODE_ID)) {
                        nodeId = new String(value);
                    } else {
                        endPointList.put(key, new String(value));
                    }
                }

                String addr = hostAddress.toString();
                addr = addr.replace("/", "");
                if (!TextUtils.isEmpty(nodeId)) {
                    EspLocalDevice device = new EspLocalDevice(nodeId, addr, hostPort);
                    device.setServiceName(serviceInfo.getServiceName());
                    listener.deviceFound(device);
                }

                // Process the next service waiting to be resolved
                resolveNextInQueue();
            }
        };
    }

    // Resolve next NSD service pending resolution
    private void resolveNextInQueue() {

        Log.d(TAG, "resolveNextInQueue");
        // Get the next NSD service waiting to be resolved from the queue
        NsdServiceInfo nextNsdService = pendingNsdServices.poll();
        if (nextNsdService != null) {
            // There was one. Send to be resolved.
            mNsdManager.resolveService(nextNsdService, resolveListener);
        } else {
            // There was no pending service. Release the flag
            resolveListenerBusy.set(false);
        }
    }

    public interface mDNSEvenListener {

        void deviceFound(EspLocalDevice device);
    }
}
