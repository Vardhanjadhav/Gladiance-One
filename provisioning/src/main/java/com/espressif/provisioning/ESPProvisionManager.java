// Copyright 2020 Espressif Systems (Shanghai) PTE LTD
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.espressif.provisioning;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.espressif.provisioning.device_scanner.BleScanner;
import com.espressif.provisioning.device_scanner.WiFiScanner;
import com.espressif.provisioning.listeners.BleScanListener;
import com.espressif.provisioning.listeners.QRCodeScanListener;
import com.espressif.provisioning.listeners.WiFiScanListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * App can use this class to provision device. It has APIs to scan devices, scan QR code and connect with the device to get
 * object of ESPDevice.
 */
public class ESPProvisionManager {

    private static final String TAG = "ESP:" + ESPProvisionManager.class.getSimpleName();

    private static ESPProvisionManager provision;

    private ESPDevice espDevice;
    private BleScanner bleScanner;
    private WiFiScanner wifiScanner;
    private Context context;
    private Handler handler;
    private boolean isScanned = false;

    String Mac;
    String key,value;

    // Assume "MyPrefs" is the name of your SharedPreferences file
//    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();
//
//    // Assume "myStringKey" is the key under which you want to store the string
//    String myString = "Hello, World!";
//editor.putString("myStringKey", myString);
//editor.apply();





    /**
     * This method is used to get singleton instance of
     *
     * @param context Context
     * @return Returns
     */
    public static ESPProvisionManager getInstance(Context context) {

        if (provision == null) {
            provision = new ESPProvisionManager(context);
        }
        return provision;
    }

    public ESPProvisionManager(Context context) {
        this.context = context;

        handler = new Handler();
    }

    /**
     * This method is used to get ESPDevice object with given transport and security.
     *
     * @param transportType Transport type.
     * @param securityType  Security type.
     * @return Returns ESPDevice.
     */
    public ESPDevice createESPDevice(ESPConstants.TransportType transportType, ESPConstants.SecurityType securityType) {

        espDevice = new ESPDevice(context, transportType, securityType);
        return espDevice;
    }

    /**
     * This method is used to get ESPDevice object with given transport and security.
     *
     * @return Returns ESPDevice.
     */
    public ESPDevice getEspDevice() {
        return espDevice;
    }

    /**
     * This method scans QR code from, get the device information and checks whether this device is available or not.
     * If device is available in scanning (BLE / Wi-Fi), then it will return ESPDevice.
     *
     * @param activityContext     Activity Context.
     * @param cameraSourcePreview CameraSourcePreview in which CameraSource needs to be display to scan QR code.
     * @param qrCodeScanListener  QRCodeScanListener
     */
    @RequiresPermission(allOf = {Manifest.permission.CAMERA, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void scanQRCode(final Activity activityContext, final CameraSourcePreview cameraSourcePreview, final QRCodeScanListener qrCodeScanListener) {

        isScanned = false;
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(activityContext)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        CameraSource cameraSource = new CameraSource.Builder(activityContext, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true)
                .build();

        if (cameraSource != null) {
            try {
                cameraSourcePreview.start(cameraSource);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override
            public void release() {
            }

            @Override
            @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0 && !isScanned) {

                    Log.d(TAG, "Barcodes size : " + barcodes.size());
                    Barcode barcode = barcodes.valueAt(0);
                    Log.d(TAG, "QR Code Data1 : " + barcode.rawValue);
                    String scannedData = barcode.rawValue;

                    try {
                        JSONObject jsonObject = new JSONObject(scannedData);

                        String deviceName = jsonObject.optString("name");
                        String pop = jsonObject.optString("pop");
                        String transport = jsonObject.optString("transport");
                        String mac = jsonObject.optString("mac");
                        int security = jsonObject.optInt("security", ESPConstants.SecurityType.SECURITY_2.ordinal());
                        String userName = jsonObject.optString("username");
                        String password = jsonObject.optString("password");
                        isScanned = true;

                        if (qrCodeScanListener != null) {
                            qrCodeScanListener.qrCodeScanned();
                        }

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                cameraSourcePreview.release();
                            }
                        });

                        ESPConstants.TransportType transportType = null;
                        ESPConstants.SecurityType securityType = null;

                        if (!TextUtils.isEmpty(transport)) {

                            if (transport.equalsIgnoreCase("softap")) {

                                transportType = ESPConstants.TransportType.TRANSPORT_SOFTAP;

                            } else if (transport.equalsIgnoreCase("ble")) {

                                transportType = ESPConstants.TransportType.TRANSPORT_BLE;

                            } else {
                                qrCodeScanListener.onFailure(new RuntimeException("Transport type not supported"));
                            }

                        } else {
                            qrCodeScanListener.onFailure(new RuntimeException("Transport is not available"));
                        }

                        securityType = setSecurityType(security);

                        espDevice = new ESPDevice(context, transportType, securityType);
                        espDevice.setDeviceName(deviceName);
                        espDevice.setProofOfPossession(pop);
                        //  espDevice.setMac(mac);
                        espDevice.setUserName(userName);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && transportType.equals(ESPConstants.TransportType.TRANSPORT_SOFTAP)) {

                            WiFiAccessPoint wiFiDevice = new WiFiAccessPoint();
                            wiFiDevice.setWifiName(deviceName);
                            wiFiDevice.setPassword(password);
                            espDevice.setWifiDevice(wiFiDevice);
                            qrCodeScanListener.deviceDetected(espDevice);
                        } else {
                            isDeviceAvailable(espDevice, password, qrCodeScanListener);
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                        qrCodeScanListener.onFailure(new RuntimeException("QR code not valid"));
                    }
                }


                //      fetchData();

            }
        });
    }



    /**
     * This method scans QR code from, get the device information and checks whether this device is available or not.
     * If device is available in scanning (BLE / Wi-Fi), then it will return ESPDevice.
     *
     * @param codeScanner        CodeScanner in which CameraSource needs to be display to scan QR code.
     * @param qrCodeScanListener QRCodeScanListener
     */
    @RequiresPermission(allOf = {Manifest.permission.CAMERA, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void scanQRCode(final CodeScanner codeScanner, final QRCodeScanListener qrCodeScanListener) {

        isScanned = false;
        List<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.QR_CODE);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                String scannedData = result.getText();

                if (!TextUtils.isEmpty(scannedData) && !isScanned) {

                    Log.d(TAG, "QR Code Data2 : " + scannedData);

                    try {
                        JSONObject jsonObject = new JSONObject(scannedData);

                        String deviceName = jsonObject.optString("name");
                        String pop = jsonObject.optString("pop");
                        String mac = jsonObject.optString("mac");
                        Mac = mac; //we get mac here
                        Log.d(TAG, "Mac " +Mac);
                        String transport = jsonObject.optString("transport");
                        int security = jsonObject.optInt("security", ESPConstants.SecurityType.SECURITY_2.ordinal());
                        String userName = jsonObject.optString("username");
                        String password = jsonObject.optString("password");
                        isScanned = true;

                        saveMacId(Mac);

                        if (qrCodeScanListener != null) {
                            qrCodeScanListener.qrCodeScanned();
                        }

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                codeScanner.releaseResources();
                            }
                        });

                        ESPConstants.TransportType transportType = null;
                        ESPConstants.SecurityType securityType = null;

                        if (!TextUtils.isEmpty(transport)) {

                            if (transport.equalsIgnoreCase("softap")) {

                                transportType = ESPConstants.TransportType.TRANSPORT_SOFTAP;

                            } else if (transport.equalsIgnoreCase("ble")) {

                                transportType = ESPConstants.TransportType.TRANSPORT_BLE;

                            } else {
                                Log.e(TAG, "" + transport + " Transport type is not supported");
                                qrCodeScanListener.onFailure(new RuntimeException("Transport type is not supported"));
                                return;
                            }
                        } else {
                            // COMMENTED OUT ON 01-04-2024 FOR INPUT DEVICE PROVISIONING TESTING
//                            Log.e(TAG, "Transport is not available in QR code data");
//                            qrCodeScanListener.onFailure(new RuntimeException("QR code is not valid"), scannedData);
//                            return;
                        }

                        if (!pop.equals("")) {
                            securityType = setSecurityType(security);

                            espDevice = new ESPDevice(context, transportType, securityType);
                            espDevice.setDeviceName(deviceName);
                            espDevice.setProofOfPossession(pop);
//                        espDevice.setMac(mac);

                            espDevice.setUserName(userName);

                            //      fetchData(nodeId);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && transportType.equals(ESPConstants.TransportType.TRANSPORT_SOFTAP)) {

                                WiFiAccessPoint wiFiDevice = new WiFiAccessPoint();
                                wiFiDevice.setWifiName(deviceName);
                                wiFiDevice.setPassword(password);
                                espDevice.setWifiDevice(wiFiDevice);
                                qrCodeScanListener.deviceDetected(espDevice);
                            } else {
                                isDeviceAvailable(espDevice, password, qrCodeScanListener);
                            }
                        }
                        else
                        {////start from here
                            //    GetNodeId(userId, loginDeviceId, mac, gaaProjectSpaceRef, gaaProjectSpaceTypePlannedDeviceRef);
                            espDevice = new ESPDevice(context, ESPConstants.TransportType.TRANSPORT_INPUT, ESPConstants.SecurityType.SECURITY_0);
                            qrCodeScanListener.deviceDetected(espDevice);
                            String a = "InputDevice";
                            saveInput(a);
                            return;

                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                        qrCodeScanListener.onFailure(new RuntimeException("QR code is not valid"), scannedData);
                        Toast.makeText(context, "Excep1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    qrCodeScanListener.onFailure(new RuntimeException("QR code is not valid"), scannedData);
                    Toast.makeText(context, "Excep2", Toast.LENGTH_SHORT).show();

                }
            }

            private void saveInput(String A) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs_Input", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Input", A);
                editor.apply();
            }

            public String getString(String key, String defaultValue) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs_Input", Context.MODE_PRIVATE);
                return sharedPreferences.getString(key, defaultValue);
            }
//            public void goToMyActivity() {
//                // Create an Intent to start the MyActivity
//                Intent intent = new Intent(context, AddDeviceActivity.class);
//
//                // If you need to pass data to MyActivity, you can add extras to the intent
//                intent.putExtra("key", "value");
//
//                // Start the MyActivity
//                context.startActivity(intent);
//            }

        });
        // espDevice.getMac());
    }

    public void saveMacId(String mac) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mac", mac);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }



//    private void fetchData(String nodeId) {
//
//
//            ApiService apiService = RetrofitClient.getClient().create(MyApiService.class);
//
//            Call<ResponseModelNode> call = apiService.getData(nodeId);
//            call.enqueue(new Callback<ResponseModelNode>() {
//                @Override
//                public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
//                    if (response.isSuccessful()) {
//                        YourResponseModel data = response.body();
//                        // Handle the data here
//                    } else {
//                        // Handle unsuccessful response
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseModelNode> call, Throwable t) {
//                    // Handle failure
//                }
//            });
//
//    }

    /**
     * This method is used to scan BLE devices.
     *
     * @param filters            The scan filters that will be used
     * @param bleScannerListener BleScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void searchBleEspDevices(List<ScanFilter> filters, BleScanListener bleScannerListener) {

        Log.d(TAG, "Search for BLE devices");
        bleScanner = new BleScanner(context, bleScannerListener);
        bleScanner.startScan(filters);
    }

    /**
     * This method is used to scan BLE devices.
     *
     * @param scanSettings       The scan settings that will be used
     * @param bleScannerListener BleScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void searchBleEspDevices(ScanSettings scanSettings, BleScanListener bleScannerListener) {

        Log.d(TAG, "Search for BLE devices");
        bleScanner = new BleScanner(context, bleScannerListener);
        bleScanner.startScan(scanSettings);
    }

    /**
     * This method is used to scan BLE devices.
     *
     * @param filters            The scan filters that will be used
     * @param scanSettings       The scan settings that will be used
     * @param bleScannerListener BleScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void searchBleEspDevices(List<ScanFilter> filters, ScanSettings scanSettings, BleScanListener bleScannerListener) {

        Log.d(TAG, "Search for BLE devices");
        bleScanner = new BleScanner(context, bleScannerListener);
        bleScanner.startScan(filters, scanSettings);
    }

    /**
     * This method is used to scan BLE devices.
     *
     * @param bleScannerListener BleScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void searchBleEspDevices(BleScanListener bleScannerListener) {

        Log.d(TAG, "Search for BLE devices");
        bleScanner = new BleScanner(context, bleScannerListener);
        bleScanner.startScan();
    }

    /**
     * This method is used to scan BLE devices with having given prefix in device name.
     *
     * @param prefix             Prefix to filter devices from device name.
     * @param bleScannerListener BleScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void searchBleEspDevices(String prefix, BleScanListener bleScannerListener) {

        Log.d(TAG, "Search for BLE devices");
        bleScanner = new BleScanner(context, prefix, bleScannerListener);
        bleScanner.startScan();
    }

    /**
     * This method is used to stop BLE scanning.
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_FINE_LOCATION})
    public void stopBleScan() {

        if (bleScanner != null) {
            bleScanner.stopScan();
        }
    }

    /**
     * This method is used to scan Wi-Fi devices.
     *
     * @param wiFiDeviceScanListener WiFiScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public void searchWiFiEspDevices(WiFiScanListener wiFiDeviceScanListener) {

        wifiScanner = new WiFiScanner(context, wiFiDeviceScanListener);
        wifiScanner.startScan();
    }

    /**
     * This method is used to scan Wi-Fi devices with having given prefix in device name.
     *
     * @param prefix                 Prefix to filter devices from device name.
     * @param wiFiDeviceScanListener WiFiScanListener for scanning callbacks.
     */
    @RequiresPermission(allOf = {Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public void searchWiFiEspDevices(String prefix, WiFiScanListener wiFiDeviceScanListener) {

        wifiScanner = new WiFiScanner(context, prefix, wiFiDeviceScanListener);
        wifiScanner.startScan();
    }

    private int searchCnt = 0;
    private boolean isDeviceFound = false;

    /**
     * This method will check given ESPDevice is available in scanning or not.
     * It will give callback to app with ESPDevice object if device is available in scanning.
     *
     * @param device             ESPDevice.
     * @param password           Password for Wi-Fi device.
     * @param qrCodeScanListener QRCodeScanListener to give callbacks to app.
     */
    private void isDeviceAvailable(final ESPDevice device, String password, QRCodeScanListener qrCodeScanListener) {

        searchCnt = 0;
        isDeviceFound = false;
        // Check device is available in scanning.
        SearchDeviceTask searchDeviceTask = new SearchDeviceTask(device, password, qrCodeScanListener);
        handler.post(searchDeviceTask);
    }

    private ESPConstants.SecurityType setSecurityType(int security) {
        switch (security) {
            case 0:
                return ESPConstants.SecurityType.SECURITY_0;
            case 1:
                return ESPConstants.SecurityType.SECURITY_1;
            case 2:
            default:
                return ESPConstants.SecurityType.SECURITY_2;
        }
    }

    class SearchDeviceTask implements Runnable {

        private ESPDevice device;
        private QRCodeScanListener listener;
        private String password;

        SearchDeviceTask(ESPDevice device1, String password1, QRCodeScanListener listener1) {
            searchCnt++;
            device = device1;
            listener = listener1;
            password = password1;
            isDeviceFound = false;
        }

        @Override
        @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_WIFI_STATE})
        public void run() {

            if (device.getTransportType().equals(ESPConstants.TransportType.TRANSPORT_BLE)) {

                searchBleEspDevices(new BleScanListener() {

                    @Override
                    public void scanStartFailed() {
                        listener.onFailure(new RuntimeException("Please turn on bluetooth and try again."));
                    }

                    @Override
                    @RequiresPermission(Manifest.permission.BLUETOOTH)
                    public void onPeripheralFound(BluetoothDevice btDevice, ScanResult scanResult) {

                        if (!isDeviceFound && btDevice != null && !TextUtils.isEmpty(scanResult.getScanRecord().getDeviceName())) {

                            if (scanResult.getScanRecord().getDeviceName().equals(device.getDeviceName())) {
                                // Device found
                                isDeviceFound = true;
                                String serviceUuid = "";

                                if (scanResult.getScanRecord().getServiceUuids() != null && scanResult.getScanRecord().getServiceUuids().size() > 0) {
                                    serviceUuid = scanResult.getScanRecord().getServiceUuids().get(0).toString();
                                }

                                device.setBluetoothDevice(btDevice);
                                device.setPrimaryServiceUuid(serviceUuid);
                            }
                        }
                    }

                    @Override
                    public void scanCompleted() {

                        Log.d(TAG, "scanCompleted");
                        Log.d(TAG, "isDeviceFound : " + isDeviceFound);
                        Log.d(TAG, "searchCnt : " + searchCnt);

                        if (!isDeviceFound) {

                            if (searchCnt != 3) {

                                SearchDeviceTask searchDeviceTask = new SearchDeviceTask(device, password, listener);
                                handler.postDelayed(searchDeviceTask, 500);
                            } else {
                                String errMsg = "" + device.getDeviceName() + " device not found";
                                listener.onFailure(new RuntimeException(errMsg));
                            }
                        } else {
                            listener.deviceDetected(device);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {

                        e.printStackTrace();
                        Log.d(TAG, "onFailure");
                        Log.d(TAG, "isDeviceFound : " + isDeviceFound);
                        Log.d(TAG, "searchCnt : " + searchCnt);

                        if (!isDeviceFound) {

                            if (searchCnt != 3) {

                                SearchDeviceTask searchDeviceTask = new SearchDeviceTask(device, password, listener);
                                handler.postDelayed(searchDeviceTask, 500);
                            } else {
                                String errMsg = "" + device.getDeviceName() + " device not found";
                                listener.onFailure(new RuntimeException(errMsg));
                            }
                        }
                    }
                });
            } else {

                searchWiFiEspDevices(new WiFiScanListener() {

                    @Override
                    public void onWifiListReceived(ArrayList<WiFiAccessPoint> scanResults) {

                        for (int i = 0; i < scanResults.size(); i++) {

                            if (scanResults.get(i).getWifiName().equals(device.getDeviceName())) {
                                // Device found
                                isDeviceFound = true;
                                WiFiAccessPoint wiFiDevice = scanResults.get(i);
                                wiFiDevice.setPassword(password);
                                device.setWifiDevice(wiFiDevice);
                                listener.deviceDetected(device);
                                break;
                            }
                        }

                        Log.d(TAG, "scanCompleted");
                        Log.d(TAG, "isDeviceFound : " + isDeviceFound);
                        Log.d(TAG, "searchCnt : " + searchCnt);

                        if (!isDeviceFound) {

                            if (searchCnt != 3) {

                                SearchDeviceTask searchDeviceTask = new SearchDeviceTask(device, password, listener);
                                handler.postDelayed(searchDeviceTask, 500);
                            } else {
                                String errMsg = "" + device.getDeviceName() + " device not found";
                                listener.onFailure(new RuntimeException(errMsg));
                            }
                        }
                    }

                    @Override
                    public void onWiFiScanFailed(Exception e) {

                        e.printStackTrace();

                        Log.d(TAG, "onFailure");
                        Log.d(TAG, "isDeviceFound : " + isDeviceFound);
                        Log.d(TAG, "searchCnt : " + searchCnt);

                        if (!isDeviceFound) {

                            if (searchCnt != 3) {

                                SearchDeviceTask searchDeviceTask = new SearchDeviceTask(device, password, listener);
                                handler.postDelayed(searchDeviceTask, 500);
                            } else {
                                String errMsg = "" + device.getDeviceName() + " device not found";
                                listener.onFailure(new RuntimeException(errMsg));
                            }
                        }
                    }
                });
            }
        }
    }
}
