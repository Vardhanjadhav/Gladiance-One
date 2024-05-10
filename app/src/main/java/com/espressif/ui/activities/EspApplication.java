package com.espressif.ui.activities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.espressif.AppConstants;
import com.espressif.JsonDataParser;
import com.espressif.cloudapi.ApiManager;
import com.espressif.cloudapi.ApiResponseListener;
import com.espressif.db.EspDatabase;
import com.espressif.local_control.EspLocalDevice;
import com.espressif.local_control.LocalControlApiManager;
import com.espressif.local_control.mDNSManager;
import com.espressif.provisioning.ESPProvisionManager;
import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.models.Action;
import com.espressif.ui.models.Automation;
import com.espressif.ui.models.Device;
import com.espressif.ui.models.EspOtaUpdate;
import com.espressif.ui.models.Group;
import com.espressif.ui.models.Param;
import com.espressif.ui.models.Scene;
import com.espressif.ui.models.Schedule;
import com.espressif.ui.models.Service;
import com.espressif.ui.models.UpdateEvent;
import com.espressif.wifi_provisioning.BuildConfig;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspApplication { // extends Application {

    private static final String TAG = EspApplication.class.getSimpleName();

    private AppState appState = AppState.NO_USER_LOGIN;

    public HashMap<String, EspNode> nodeMap;
    public HashMap<String, Schedule> scheduleMap;
    public HashMap<String, Scene> sceneMap;
    public HashMap<String, EspLocalDevice> localDeviceMap;
    public HashMap<String, Group> groupMap;
    public HashMap<String, Automation> automations;
    public EspOtaUpdate otaUpdateInfo;

    private static ArrayList<String> nodeIds = new ArrayList<>();
    private static ArrayList<String> scheduleIds = new ArrayList<>();
    private static ArrayList<String> sceneIds = new ArrayList<>();
    private static ArrayList<String> automationIds = new ArrayList<>();

    private SharedPreferences appPreferences;
    ApiManager apiManager;
    private EspDatabase espDatabase;
    private mDNSManager mdnsManager;
    private String deviceToken;

    public enum AppState {
        NO_USER_LOGIN,
        GETTING_DATA,
        GET_DATA_SUCCESS,
        GET_DATA_FAILED,
        NO_INTERNET,
        REFRESH_DATA
    }

    private Context appContext;


    private static EspApplication instance;

//    public void onCreate() {

//    @Override
    public EspApplication(Context _appContext) {
        this.appContext = _appContext;
//        super.onCreate();
        Log.d(TAG, "ESP Application is created");
        nodeMap = new HashMap<>();
        scheduleMap = new HashMap<>();
        sceneMap = new HashMap<>();
        localDeviceMap = new HashMap<>();
        groupMap = new HashMap<>();
        automations = new HashMap<>();

        espDatabase = EspDatabase.getInstance(appContext);

        appPreferences = appContext.getSharedPreferences(AppConstants.ESP_PREFERENCES, MODE_PRIVATE);
//        apiManager = com.espressif.cloudapi.ApiManager.getInstance(this);
       // apiManager = ApiManager.getInstance(appContext);
//        ESPProvisionManager.getInstance(this);

        EspApplication.instance = this;

        ESPProvisionManager.getInstance(appContext);
        if (BuildConfig.isLocalControlSupported) {
            mdnsManager = mDNSManager.getInstance(appContext.getApplicationContext(), AppConstants.MDNS_SERVICE_TYPE, listener, instance);
        }

//        if (isPlayServicesAvailable()) {
//            FirebaseMessaging.getInstance().setAutoInitEnabled(false);
//            setupNotificationChannels();
//        }



        if (mdnsManager != null)
        {
            mdnsManager.initializeNsd();
        }
    }

    public AppState getAppState() {
        return appState;
    }

    public void changeAppState(AppState newState, Bundle extras) {

        switch (newState) {
            case GETTING_DATA:
                if (BuildConfig.isLocalControlSupported) {
                    mdnsManager.initializeNsd();
                }
            case REFRESH_DATA:
                if (!appState.equals(newState)) {
                    appState = newState;
                    getNodesFromCloud();
//                    startLocalDeviceDiscovery();
                }
                EventBus.getDefault().post(new UpdateEvent(AppConstants.UpdateEventType.EVENT_STATE_CHANGE_UPDATE));
                break;

            case GET_DATA_FAILED:
                appState = newState;
                UpdateEvent updateEvent = new UpdateEvent(AppConstants.UpdateEventType.EVENT_STATE_CHANGE_UPDATE);
                if (extras != null) {
                    updateEvent.setData(extras);
                }
                EventBus.getDefault().post(updateEvent);
                startLocalDeviceDiscovery();
                break;

            case NO_USER_LOGIN:
                Intent loginActivity = new Intent(appContext, ConsentActivity.class);
//                Intent loginActivity = new Intent(this, ConsentActivity.class);
                loginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(loginActivity);
                appState = newState;
                EventBus.getDefault().post(new UpdateEvent(AppConstants.UpdateEventType.EVENT_STATE_CHANGE_UPDATE));
                break;

            case GET_DATA_SUCCESS:
            case NO_INTERNET:
                appState = newState;
                EventBus.getDefault().post(new UpdateEvent(AppConstants.UpdateEventType.EVENT_STATE_CHANGE_UPDATE));
                startLocalDeviceDiscovery();
                break;
        }
    }

    private void getNodesFromCloud() {
        ApiManager apiManager = new ApiManager(appContext, instance);
        SharedPreferences preferences9 = appContext.getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        apiManager.getNodes(nodeId2, new ApiResponseListener() {

            @Override
            public void onSuccess(Bundle data) {

                if (BuildConfig.isNodeGroupingSupported) {

                    apiManager.getUserGroups(null, new com.espressif.cloudapi.ApiResponseListener() {

                        @Override
                        public void onSuccess(Bundle data) {
                            changeAppState(AppState.GET_DATA_SUCCESS, null);
                        }

                        @Override
                        public void onResponseFailure(Exception exception) {
                            Bundle data = new Bundle();
                            data.putString(AppConstants.KEY_ERROR_MSG, exception.getMessage());
                            changeAppState(EspApplication.AppState.GET_DATA_FAILED, data);
                        }

                        @Override
                        public void onNetworkFailure(Exception exception) {
                            changeAppState(AppState.NO_INTERNET, null);
                        }
                    });
                } else {
                    changeAppState(AppState.GET_DATA_SUCCESS, null);
                }
            }

            @Override
            public void onResponseFailure(Exception exception) {
                Bundle data = new Bundle();
                data.putString(AppConstants.KEY_ERROR_MSG, exception.getMessage());
                changeAppState(EspApplication.AppState.GET_DATA_FAILED, data);
            }

            @Override
            public void onNetworkFailure(Exception exception) {
                changeAppState(AppState.NO_INTERNET, null);
            }
        });
    }

    public void refreshData() {
        if (!appState.equals(AppState.GETTING_DATA)) {
            changeAppState(AppState.REFRESH_DATA, null);
        }

//        startLocalDeviceDiscovery();
    }

    public void loginSuccess() {
        clearData();
    }

//    public void registerDeviceToken() {
//
//        if (!isPlayServicesAvailable()) {
//            Log.e(TAG, "Google Play Services not available.");
//            return;
//        }
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if (!task.isSuccessful()) {
//                    Log.d(TAG, "Fetching FCM registration token failed", task.getException());
//                    return;
//                }
//
//                // Get new FCM registration token
//                deviceToken = task.getResult();
//
//                // Log and toast
//                Log.e("FCM TOKEN  ", deviceToken);
//
//                if (!TextUtils.isEmpty(deviceToken)) {
//                    apiManager.registerDeviceToken(deviceToken, new com.espressif.cloudapi.ApiResponseListener() {
//                        @Override
//                        public void onSuccess(Bundle data) {
//                        }
//
//                        @Override
//                        public void onResponseFailure(Exception exception) {
//                        }
//
//                        @Override
//                        public void onNetworkFailure(Exception exception) {
//                        }
//                    });
//                }
//            }
//        });
//    }

//    public void logout(final ApiResponseListener listener) {
//
//        if (appState.equals(AppState.NO_USER_LOGIN)) {
//            return;
//        }
//
//        // Do logout and clear all data
//        if (!ApiManager.isOAuthLogin) {
//
//            apiManager.logout(new com.espressif.cloudapi.ApiResponseListener() {
//
//                @Override
//                public void onSuccess(Bundle data) {
//                    unregisterDeviceToken();
//                }
//
//                @Override
//                public void onResponseFailure(Exception exception) {
//                    // Ignore failure
//                    unregisterDeviceToken();
//                }
//
//                @Override
//                public void onNetworkFailure(Exception exception) {
//                    // Ignore failure
//                    unregisterDeviceToken();
//                }
//            });
//        } else {
//            unregisterDeviceToken();
//        }
//        clearUserSession();
//    }

    private void unregisterDeviceToken() {
        if (isPlayServicesAvailable()) {
            // Delete endpoint API
            apiManager.unregisterDeviceToken(deviceToken, new com.espressif.cloudapi.ApiResponseListener() {
                @Override
                public void onSuccess(Bundle data) {
                }

                @Override
                public void onResponseFailure(Exception exception) {
                }

                @Override
                public void onNetworkFailure(Exception exception) {
                }
            });
        }
    }

    public void clearUserSession() {

        clearData();
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.clear();
        editor.apply();

        SharedPreferences wifiNetworkPref = appContext.getSharedPreferences(AppConstants.PREF_FILE_WIFI_NETWORKS, MODE_PRIVATE);
        SharedPreferences.Editor wifiNetworkEditor = wifiNetworkPref.edit();
        wifiNetworkEditor.clear();
        wifiNetworkEditor.apply();

//        if (isPlayServicesAvailable()) {
//            FirebaseMessaging.getInstance().deleteToken();
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.cancelAll();
//        }

        Log.e(TAG, "Deleted all things from local storage.");
        changeAppState(AppState.NO_USER_LOGIN, null);
    }

    private void clearData() {
        EspDatabase.getInstance(appContext).getNodeDao().deleteAll();
        EspDatabase.getInstance(appContext).getGroupDao().deleteAll();
        EspDatabase.getInstance(appContext).getNotificationDao().deleteAll();
//        EspDatabase.getInstance(this).getNodeDao().deleteAll();
//        EspDatabase.getInstance(this).getGroupDao().deleteAll();
//        EspDatabase.getInstance(this).getNotificationDao().deleteAll();
        nodeMap.clear();
        scheduleMap.clear();
        sceneMap.clear();
        localDeviceMap.clear();
        groupMap.clear();
        automations.clear();
    }

    private void startLocalDeviceDiscovery() {
        if (BuildConfig.isLocalControlSupported) {
//            if (nodeMap.size() > 0) {
                mdnsManager.discoverServices();
//            }
        }
        else
        {
            int a = 0;
        }
    }

    public void stopLocalDeviceDiscovery() {
        if (BuildConfig.isLocalControlSupported) {
            mdnsManager.stopDiscovery();
        }
    }

    /**
     * This method is used to get copy of all devices.
     *
     * @return Copy of all devices array for the user.
     */
    public ArrayList<Device> getAllDevices() {
        ArrayList<Device> devices = new ArrayList<>();
        for (Map.Entry<String, EspNode> entry : nodeMap.entrySet()) {

            EspNode node = entry.getValue();
            if (node != null) {
                ArrayList<Device> espDevices = node.getDevices();
                Iterator<Device> iterator = espDevices.iterator();
                while (iterator.hasNext()) {
                    devices.add(new Device(iterator.next()));
                }
            }
        }
        return devices;
    }

    mDNSManager.mDNSEvenListener listener = new mDNSManager.mDNSEvenListener() {

        @Override
        public void deviceFound(EspLocalDevice newDevice) {

            nodeIds.clear();

            Log.e(TAG, "Device Found on Local Network");
//            final LocalControlApiManager localControlApiManager = new LocalControlApiManager(getApplicationContext());
            final LocalControlApiManager localControlApiManager = new LocalControlApiManager(appContext, instance);
            final String nodeId3 = newDevice.getNodeId();

            SharedPreferences preferences9 = appContext.getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");

            EspNode espNode = null;

            if (nodeId3.equals(nodeId2)) {
                espNode = new EspNode(nodeId2);
            }




            //local control get api call
            localControl();


            SharedPreferences preferences10 = appContext.getSharedPreferences("my_shared_prefet_local", MODE_PRIVATE);
            String local = preferences10.getString("KEY_USERNAMEst_local", "");

            Log.e(TAG, "deviceFoundlocal: "+local);


            try {
                JSONObject nodeJsonOuter = new JSONObject(local); // nodeJsonArray.optJSONObject(nodeIndex);
                if (nodeJsonOuter != null) {
                    JSONArray aryNodeDetails = nodeJsonOuter.optJSONArray("node_details");

                    if (aryNodeDetails != null) {
                        if (aryNodeDetails.length() >= 1) {
                            JSONObject nodeJson = aryNodeDetails.getJSONObject(0);

                            if (nodeJson != null) {

                                // Node ID
                                String nodeId = nodeJson.optString(AppConstants.KEY_ID);
                                Log.d(TAG, "Node id : " + nodeId);
                                nodeIds.add(nodeId);
//                                EspNode espNode;

                                if (nodeMap.get(nodeId) != null) {
                                    espNode = nodeMap.get(nodeId);
                                } else {
                                    espNode = new EspNode(nodeId);
                                }

                                // User role
                                String role = nodeJson.optString(AppConstants.KEY_ROLE);
                                espNode.setUserRole(role);

                                // Node Config
                                JSONObject configJson = nodeJson.optJSONObject(AppConstants.KEY_CONFIG);
                                if (configJson != null) {

                                    // If node is available on local network then ignore configuration received from cloud.
                                    if (!localDeviceMap.containsKey(nodeId)) {
                                        espNode = JsonDataParser.setNodeConfig(espNode, configJson);
                                    } else {
                                        Log.d(TAG, "Ignore config values for local node :" + nodeId);
                                    }

                                    espNode.setOnline(true);
                                    espNode.setConfigData(configJson.toString());
                                    nodeMap.put(nodeId, espNode);
                                }

                                // Node Params values
                                JSONObject paramsJson = nodeJson.optJSONObject(AppConstants.KEY_PARAMS);
                                if (paramsJson != null) {

                                    espNode.setParamData(paramsJson.toString());
                                    espDatabase.getNodeDao().insertOrUpdate(espNode);

                                    ArrayList<Device> devices = espNode.getDevices();
                                    ArrayList<Service> services = espNode.getServices();
                                    JSONObject scheduleJson = paramsJson.optJSONObject(AppConstants.KEY_SCHEDULE);
                                    JSONObject sceneJson = paramsJson.optJSONObject(AppConstants.KEY_SCENES);
                                    JSONObject timeJson = paramsJson.optJSONObject(AppConstants.KEY_TIME);
                                    JSONObject localControlJson = paramsJson.optJSONObject(AppConstants.KEY_LOCAL_CONTROL);

                                    // If node is available on local network then ignore param values received from cloud.
                                    if (!localDeviceMap.containsKey(nodeId) && devices != null) {

                                        for (int i = 0; i < devices.size(); i++) {

                                            ArrayList<Param> params = devices.get(i).getParams();
                                            String deviceName = devices.get(i).getDeviceName();
                                            JSONObject deviceJson = paramsJson.optJSONObject(deviceName);

                                            if (deviceJson != null) {

                                                for (int j = 0; j < params.size(); j++) {

                                                    Param param = params.get(j);
                                                    String key = param.getName();

                                                    if (!param.isDynamicParam()) {
                                                        continue;
                                                    }

                                                    if (deviceJson.has(key)) {
                                                        JsonDataParser.setDeviceParamValue(deviceJson, devices.get(i), param);
                                                    }
                                                }
                                            } else {
                                                Log.e(TAG, "Device JSON is null");
                                            }
                                        }
                                    } else {
                                        Log.d(TAG, "Ignore param values for local node :" + nodeId);
                                    }

                                    // Schedules
                                    if (scheduleJson != null) {

                                        JSONArray scheduleArrayJson = scheduleJson.optJSONArray(AppConstants.KEY_SCHEDULES);

                                        if (scheduleArrayJson != null) {

                                            for (int index = 0; index < scheduleArrayJson.length(); index++) {

                                                JSONObject schJson = scheduleArrayJson.getJSONObject(index);
                                                String scheduleId = schJson.optString(AppConstants.KEY_ID);
                                                String key = scheduleId;

                                                if (!TextUtils.isEmpty(scheduleId)) {

                                                    String name = schJson.optString(AppConstants.KEY_NAME);
                                                    key = key + "_" + name + "_" + schJson.optBoolean(AppConstants.KEY_ENABLED);

                                                    HashMap<String, Integer> triggers = new HashMap<>();
                                                    JSONArray triggerArray = schJson.optJSONArray(AppConstants.KEY_TRIGGERS);
                                                    for (int t = 0; t < triggerArray.length(); t++) {
                                                        JSONObject triggerJson = triggerArray.optJSONObject(t);
                                                        int days = triggerJson.optInt(AppConstants.KEY_DAYS);
                                                        int mins = triggerJson.optInt(AppConstants.KEY_MINUTES);
                                                        triggers.put(AppConstants.KEY_DAYS, days);
                                                        triggers.put(AppConstants.KEY_MINUTES, mins);
                                                        key = key + "_" + days + "_" + mins;
                                                    }

                                                    Schedule schedule = scheduleMap.get(key);
                                                    if (schedule == null) {
                                                        schedule = new Schedule();
                                                    }

                                                    schedule.setId(scheduleId);
                                                    schedule.setName(schJson.optString(AppConstants.KEY_NAME));
                                                    schedule.setEnabled(schJson.optBoolean(AppConstants.KEY_ENABLED));

                                                    scheduleIds.add(key);
                                                    schedule.setTriggers(triggers);
                                                    Log.d(TAG, "=============== Schedule : " + schedule.getName() + " ===============");

                                                    // Actions
                                                    JSONObject actionsSchJson = schJson.optJSONObject(AppConstants.KEY_ACTION);

                                                    if (actionsSchJson != null) {

                                                        ArrayList<Action> actions = schedule.getActions();
                                                        if (actions == null) {
                                                            actions = new ArrayList<>();
                                                            schedule.setActions(actions);
                                                        }

                                                        for (int deviceIndex = 0; deviceIndex < devices.size(); deviceIndex++) {

                                                            Device d = new Device(devices.get(deviceIndex));
                                                            ArrayList<Param> params = d.getParams();
                                                            String deviceName = d.getDeviceName();
                                                            JSONObject deviceAction = actionsSchJson.optJSONObject(deviceName);

                                                            if (deviceAction != null) {

                                                                Action action = null;
                                                                Device actionDevice = null;
                                                                int actionIndex = -1;

                                                                for (int aIndex = 0; aIndex < actions.size(); aIndex++) {

                                                                    Action a = actions.get(aIndex);
                                                                    if (a.getDevice().getNodeId().equals(nodeId) && deviceName.equals(a.getDevice().getDeviceName())) {
                                                                        action = actions.get(aIndex);
                                                                        actionIndex = aIndex;
                                                                    }
                                                                }

                                                                if (action == null) {
                                                                    action = new Action();
                                                                    action.setNodeId(nodeId);

                                                                    for (int k = 0; k < devices.size(); k++) {

                                                                        if (devices.get(k).getNodeId().equals(nodeId) && devices.get(k).getDeviceName().equals(deviceName)) {
                                                                            actionDevice = new Device(devices.get(k));
                                                                            actionDevice.setSelectedState(AppConstants.ACTION_SELECTED_ALL);
                                                                            break;
                                                                        }
                                                                    }

                                                                    if (actionDevice == null) {
                                                                        actionDevice = new Device(nodeId);
                                                                    }
                                                                    action.setDevice(actionDevice);
                                                                } else {
                                                                    actionDevice = action.getDevice();
                                                                }

                                                                ArrayList<Param> actionParams = new ArrayList<>();
                                                                if (params != null) {

                                                                    Iterator<Param> iterator = params.iterator();
                                                                    while (iterator.hasNext()) {
                                                                        Param p = iterator.next();
                                                                        actionParams.add(new Param(p));
                                                                    }

                                                                    Iterator itr = actionParams.iterator();

                                                                    while (itr.hasNext()) {

                                                                        Param p = (Param) itr.next();

                                                                        if (!p.isDynamicParam()) {
                                                                            itr.remove();
                                                                        } else if (p.getParamType() != null && p.getParamType().equals(AppConstants.PARAM_TYPE_NAME)) {
                                                                            itr.remove();
                                                                        } else if (!p.getProperties().contains(AppConstants.KEY_PROPERTY_WRITE)) {
                                                                            itr.remove();
                                                                        }
                                                                    }
                                                                }
                                                                actionDevice.setParams(actionParams);

                                                                for (int paramIndex = 0; paramIndex < actionParams.size(); paramIndex++) {

                                                                    Param p = actionParams.get(paramIndex);
                                                                    String paramName = p.getName();

                                                                    if (deviceAction.has(paramName)) {

                                                                        p.setSelected(true);
                                                                        JsonDataParser.setDeviceParamValue(deviceAction, devices.get(deviceIndex), p);
                                                                    }
                                                                }

                                                                for (int paramIndex = 0; paramIndex < actionParams.size(); paramIndex++) {

                                                                    if (!actionParams.get(paramIndex).isSelected()) {
                                                                        actionDevice.setSelectedState(AppConstants.ACTION_SELECTED_PARTIAL);
                                                                    }
                                                                }

                                                                if (actionIndex == -1) {
                                                                    actions.add(action);
                                                                } else {
                                                                    actions.set(actionIndex, action);
                                                                }
                                                                schedule.setActions(actions);

                                                            }
                                                        }
                                                    }
                                                    scheduleMap.put(key, schedule);
                                                }
                                            }
                                        }
                                    } else {
                                        Log.e(TAG, "Schedule JSON is null");
                                    }

                                    // Scenes
                                    if (sceneJson != null) {

                                        JSONArray sceneArrayJson = sceneJson.optJSONArray(AppConstants.KEY_SCENES);

                                        if (sceneArrayJson != null) {

                                            for (int index = 0; index < sceneArrayJson.length(); index++) {

                                                JSONObject scJson = sceneArrayJson.getJSONObject(index);
                                                String sceneId = scJson.optString(AppConstants.KEY_ID);
                                                String key = sceneId;

                                                if (!TextUtils.isEmpty(sceneId)) {

                                                    String name = scJson.optString(AppConstants.KEY_NAME);
                                                    String info = scJson.optString(AppConstants.KEY_INFO);
                                                    key = key + "_" + name + "_" + info;

                                                    Scene scene = sceneMap.get(key);
                                                    if (scene == null) {
                                                        scene = new Scene();
                                                    }

                                                    scene.setId(sceneId);
                                                    scene.setName(name);
                                                    scene.setInfo(info);
                                                    sceneIds.add(key);

                                                    Log.d(TAG, "=============== Scene : " + scene.getName() + " ===============");

                                                    // Actions
                                                    JSONObject actionsSceneJson = scJson.optJSONObject(AppConstants.KEY_ACTION);

                                                    if (actionsSceneJson != null) {

                                                        ArrayList<Action> actions = scene.getActions();
                                                        if (actions == null) {
                                                            actions = new ArrayList<>();
                                                            scene.setActions(actions);
                                                        }

                                                        for (int deviceIndex = 0; deviceIndex < devices.size(); deviceIndex++) {

                                                            Device d = new Device(devices.get(deviceIndex));
                                                            ArrayList<Param> params = d.getParams();
                                                            String deviceName = d.getDeviceName();
                                                            JSONObject deviceAction = actionsSceneJson.optJSONObject(deviceName);

                                                            if (deviceAction != null) {

                                                                Action action = null;
                                                                Device actionDevice = null;
                                                                int actionIndex = -1;

                                                                for (int aIndex = 0; aIndex < actions.size(); aIndex++) {

                                                                    Action a = actions.get(aIndex);
                                                                    if (a.getDevice().getNodeId().equals(nodeId) && deviceName.equals(a.getDevice().getDeviceName())) {
                                                                        action = actions.get(aIndex);
                                                                        actionIndex = aIndex;
                                                                    }
                                                                }

                                                                if (action == null) {
                                                                    action = new Action();
                                                                    action.setNodeId(nodeId);

                                                                    for (int k = 0; k < devices.size(); k++) {

                                                                        if (devices.get(k).getNodeId().equals(nodeId) && devices.get(k).getDeviceName().equals(deviceName)) {
                                                                            actionDevice = new Device(devices.get(k));
                                                                            actionDevice.setSelectedState(AppConstants.ACTION_SELECTED_ALL);
                                                                            break;
                                                                        }
                                                                    }

                                                                    if (actionDevice == null) {
                                                                        actionDevice = new Device(nodeId);
                                                                    }
                                                                    action.setDevice(actionDevice);
                                                                } else {
                                                                    actionDevice = action.getDevice();
                                                                }

                                                                ArrayList<Param> actionParams = new ArrayList<>();
                                                                if (params != null) {

                                                                    Iterator<Param> iterator = params.iterator();
                                                                    while (iterator.hasNext()) {
                                                                        Param p = iterator.next();
                                                                        actionParams.add(new Param(p));
                                                                    }

                                                                    Iterator itr = actionParams.iterator();

                                                                    while (itr.hasNext()) {

                                                                        Param p = (Param) itr.next();

                                                                        if (!p.isDynamicParam()) {
                                                                            itr.remove();
                                                                        } else if (p.getParamType() != null && p.getParamType().equals(AppConstants.PARAM_TYPE_NAME)) {
                                                                            itr.remove();
                                                                        } else if (!p.getProperties().contains(AppConstants.KEY_PROPERTY_WRITE)) {
                                                                            itr.remove();
                                                                        }
                                                                    }
                                                                }
                                                                actionDevice.setParams(actionParams);

                                                                for (int paramIndex = 0; paramIndex < actionParams.size(); paramIndex++) {

                                                                    Param p = actionParams.get(paramIndex);
                                                                    String paramName = p.getName();

                                                                    if (deviceAction.has(paramName)) {

                                                                        p.setSelected(true);
                                                                        JsonDataParser.setDeviceParamValue(deviceAction, devices.get(deviceIndex), p);
                                                                    }
                                                                }

                                                                for (int paramIndex = 0; paramIndex < actionParams.size(); paramIndex++) {

                                                                    if (!actionParams.get(paramIndex).isSelected()) {
                                                                        actionDevice.setSelectedState(AppConstants.ACTION_SELECTED_PARTIAL);
                                                                    }
                                                                }

                                                                if (actionIndex == -1) {
                                                                    actions.add(action);
                                                                } else {
                                                                    actions.set(actionIndex, action);
                                                                }
                                                                scene.setActions(actions);
                                                            }
                                                        }
                                                    }
                                                    sceneMap.put(key, scene);
                                                }
                                            }
                                        }
                                    } else {
                                        Log.e(TAG, "Scene JSON is null");
                                    }

                                    // Timezone
                                    if (timeJson != null && services != null) {
                                        for (int serviceIdx = 0; serviceIdx < services.size(); serviceIdx++) {
                                            Service service = services.get(serviceIdx);
                                            if (AppConstants.SERVICE_TYPE_TIME.equals(service.getType())) {
                                                ArrayList<Param> timeParams = service.getParams();
                                                if (timeParams != null) {
                                                    for (int paramIdx = 0; paramIdx < timeParams.size(); paramIdx++) {
                                                        Param timeParam = timeParams.get(paramIdx);
                                                        String dataType = timeParam.getDataType();
                                                        if (!TextUtils.isEmpty(dataType)) {
                                                            if (dataType.equalsIgnoreCase("string")) {
                                                                timeParam.setLabelValue(timeJson.optString(timeParam.getName()));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        Log.e(TAG, "Time JSON is not available");
                                    }

                                    // Local control
                                    if (localControlJson != null && services != null) {
                                        for (int serviceIdx = 0; serviceIdx < services.size(); serviceIdx++) {
                                            Service service = services.get(serviceIdx);
                                            if (AppConstants.SERVICE_TYPE_LOCAL_CONTROL.equals(service.getType())) {
                                                ArrayList<Param> localParams = service.getParams();
                                                if (localParams != null) {
                                                    for (int paramIdx = 0; paramIdx < localParams.size(); paramIdx++) {
                                                        Param localParam = localParams.get(paramIdx);
                                                        String dataType = localParam.getDataType();
                                                        if (!TextUtils.isEmpty(dataType)) {
                                                            if (dataType.equalsIgnoreCase("string")) {
                                                                localParam.setLabelValue(localControlJson.optString(localParam.getName()));
                                                            }
                                                            if (dataType.equalsIgnoreCase("int") || dataType.equalsIgnoreCase("integer")) {
                                                                localParam.setValue(localControlJson.optInt(localParam.getName()));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        Log.e(TAG, "Local control JSON is not available");
                                    }
                                }

                                // Node Status
                                JSONObject statusJson = nodeJson.optJSONObject(AppConstants.KEY_STATUS);

                                if (statusJson != null && !localDeviceMap.containsKey(nodeId)) {

                                    JSONObject connectivityObject = statusJson.optJSONObject(AppConstants.KEY_CONNECTIVITY);

                                    if (connectivityObject != null) {

                                        boolean nodeStatus = connectivityObject.optBoolean(AppConstants.KEY_CONNECTED);
                                        long timestamp = connectivityObject.optLong(AppConstants.KEY_TIMESTAMP);
                                        espNode.setTimeStampOfStatus(timestamp);

                                        if (espNode.isOnline() != nodeStatus) {
                                            espNode.setOnline(nodeStatus);
                                        }
                                    } else {
                                        Log.e(TAG, "Connectivity object is null");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {

            }

//            EspNode node = nodeMap.get(nodeId);



            Service localService = null;

            if (espNode == null) {
                Log.e(TAG, "Node is not available with id : " + nodeId3);
                return;
            } else {
                ArrayList<Service> services = espNode.getServices();

                for (int i = 0; i < services.size(); i++) {
                    Service s = services.get(i);
                    if (!TextUtils.isEmpty(s.getType()) && s.getType().equals(AppConstants.SERVICE_TYPE_LOCAL_CONTROL)) {
                        localService = s;
                        break;
                    }
                }
            }

            Log.e(TAG, "Found node " + nodeId3 + " on local network.");
            if (localDeviceMap.containsKey(nodeId3)) {
                Log.e(TAG, "Local Device session is already available");
                newDevice = localDeviceMap.get(nodeId3);
            } else {
                localDeviceMap.put(nodeId3, newDevice);
            }

            if (localService != null) {
                ArrayList<Param> popParams = localService.getParams();
                if (popParams != null) {
                    for (int paramIdx = 0; paramIdx < popParams.size(); paramIdx++) {
                        Param popParam = popParams.get(paramIdx);
                        if (AppConstants.PARAM_TYPE_LOCAL_CONTROL_POP.equalsIgnoreCase(popParam.getParamType())) {
                            String popValue = popParam.getLabelValue();
                            newDevice.setPop(popValue);
                        } else if (AppConstants.PARAM_TYPE_LOCAL_CONTROL_TYPE.equalsIgnoreCase(popParam.getParamType())) {
                            int type = (int) popParam.getValue();
                            newDevice.setSecurityType(type);
                        }
                    }
                }
            }

            final EspLocalDevice localDevice = newDevice;

            if (newDevice.getPropertyCount() == -1) {
                localControlApiManager.getPropertyCount(AppConstants.LOCAL_CONTROL_ENDPOINT, localDevice, new com.espressif.cloudapi.ApiResponseListener() {

                    @Override
                    public void onSuccess(Bundle data) {

                        if (data != null) {

                            int count = data.getInt(AppConstants.KEY_PROPERTY_COUNT, 0);
                            localDevice.setPropertyCount(count);

                            localControlApiManager.getPropertyValues(AppConstants.LOCAL_CONTROL_ENDPOINT, localDevice, new ApiResponseListener() {

                                @Override
                                public void onSuccess(Bundle data) {

                                    if (data != null) {

                                        String configData = data.getString(AppConstants.KEY_CONFIG);
                                        String paramsData = data.getString(AppConstants.KEY_PARAMS);

                                        Log.d(TAG, "Config data : " + configData);
                                        Log.d(TAG, "Params data : " + paramsData);

                                        if (!TextUtils.isEmpty(configData)) {

                                            JSONObject configJson = null;
                                            try {
                                                configJson = new JSONObject(configData);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            String id = configJson.optString(AppConstants.KEY_NODE_ID);
                                            EspNode node = nodeMap.get(id);

                                            EspNode localNode = JsonDataParser.setNodeConfig(node, configJson);

                                            if (node != null) {
                                                Log.e(TAG, "Found node " + localNode.getNodeId() + " on local network.");
                                                localNode.setAvailableLocally(true);
                                                localNode.setIpAddress(localDevice.getIpAddr());
                                                localNode.setPort(localDevice.getPort());
                                                localNode.setOnline(true);
                                                localDeviceMap.put(localNode.getNodeId(), localDevice);
                                            }

                                            if (!TextUtils.isEmpty(paramsData)) {

                                                JSONObject paramsJson = null;
                                                try {
                                                    paramsJson = new JSONObject(paramsData);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
//                                                JsonDataParser.setAllParams(EspApplication.this, localNode, paramsJson);
                                                JsonDataParser.setAllParams(EspApplication.instance, localNode, paramsJson);
                                                nodeMap.put(localNode.getNodeId(), localNode);
                                                EventBus.getDefault().post(new UpdateEvent(AppConstants.UpdateEventType.EVENT_LOCAL_DEVICE_UPDATE));
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onResponseFailure(Exception exception) {
                                    // Nothing to do
                                }

                                @Override
                                public void onNetworkFailure(Exception exception) {
                                    // Nothing to do
                                }
                            });
                        }
                    }

                    @Override
                    public void onResponseFailure(Exception exception) {
                        if (localDeviceMap.containsKey(nodeId3)) {
                            Log.e(TAG, "Remove local device from list");
                            localDeviceMap.remove(nodeId3);
                        }
                    }

                    @Override
                    public void onNetworkFailure(Exception exception) {
                        // Nothing to do
                    }
                });
            } else {
                Log.e(TAG, "Local device is already available and properties are already available");
            }
        }
    };

    // local control api call
    private void localControl() {


            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

            SharedPreferences preferences_node = appContext.getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId4 = preferences_node.getString("KEY_USERNAMEs", "");
            Log.d(TAG, "node id4: " +nodeId4);

            Call<Object> call = apiService.getAllLocalControlData(nodeId4);
            Log.e(TAG, "getDevice: "+nodeId4 );

            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.isSuccessful() && response.body() != null) {

//                        String n = String.valueOf(response.body());
                        Log.d(TAG, "onResponsepo: "+response.body());
                        Gson gson = new Gson();
                        String n = gson.toJson(response.body());
                        Log.e(TAG, "localcontrol: "+n );

                        SharedPreferences sharedPreferences_local = appContext.getSharedPreferences("my_shared_prefet_local", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences_local.edit();
                        //  Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: "+nodeId );
                        editor.putString("KEY_USERNAMEst_local", n);
                        editor.apply();

//                            List<DeviceInfo.Device> devices = response.body().getDevices();
//                            for (DeviceInfo.Device device : devices) {
//                                List<DeviceInfo.Param> params = device.getParams();
//                                Log.e(TAG, "Device Type: "+device.getName());
//                                for (DeviceInfo.Param param : params) {
//                                    String name = param.getUi_type();
//                                    Log.e(TAG, "onResponse: "+param.getName());
//                                    String uiType = param.getUi_type();
//                                    Log.e(TAG, "onResponse22222: "+param.getUi_type());
//                                    Log.e(TAG, "onResponse22222: "+param.getUi_type());



                        //arrayList.add(new Devices(Devices.getName(),Devices.getType(),Devices.getData_type(),param.getUi_type()));
                        // Use the name and uiType as needed
                //    }
                    //  arrayList.add(new Devices(device.getName(),device.getType(),device.getPrimary()));
            //    }
            } else {
            // Handle unsuccessful response
        }
//                        CardAdapter cardAdapter = new CardAdapter(arrayList);
//                        recyclerView.setAdapter(cardAdapter);
//                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(EspMainActivity.this,2, GridLayoutManager.VERTICAL,false);
//                        recyclerView.setLayoutManager(gridLayoutManager1);
        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {
            // Handle failure
        }
    });

    }

//    private void setupNotificationChannels() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            NotificationChannel nodeConnectedChannel = new NotificationChannel(AppConstants.CHANNEL_NODE_ONLINE_ID,
//                    getString(R.string.channel_node_connected), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationChannel nodeDisconnectedChannel = new NotificationChannel(AppConstants.CHANNEL_NODE_OFFLINE_ID,
//                    getString(R.string.channel_node_disconnected), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationChannel nodeAddedChannel = new NotificationChannel(AppConstants.CHANNEL_NODE_ADDED,
//                    getString(R.string.channel_node_added), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationChannel nodeRemovedChannel = new NotificationChannel(AppConstants.CHANNEL_NODE_REMOVED,
//                    getString(R.string.channel_node_removed), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationChannel nodeSharingChannel = new NotificationChannel(AppConstants.CHANNEL_NODE_SHARING,
//                    getString(R.string.channel_node_sharing), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationChannel alertChannel = new NotificationChannel(AppConstants.CHANNEL_ALERT,
//                    getString(R.string.channel_node_alert), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationChannel automationChannel = new NotificationChannel(AppConstants.CHANNEL_NODE_AUTOMATION_TRIGGER,
//                    getString(R.string.channel_node_automation_trigger), NotificationManager.IMPORTANCE_HIGH);
//
//            NotificationManager notificationManager = appContext.getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(nodeConnectedChannel);
//            notificationManager.createNotificationChannel(nodeDisconnectedChannel);
//            notificationManager.createNotificationChannel(nodeAddedChannel);
//            notificationManager.createNotificationChannel(nodeRemovedChannel);
//            notificationManager.createNotificationChannel(nodeSharingChannel);
//            notificationManager.createNotificationChannel(alertChannel);
//            notificationManager.createNotificationChannel(automationChannel);
//        }
//    }

    public void removeNodeInformation(String nodeId) {
        nodeMap.remove(nodeId);
        localDeviceMap.remove(nodeId);
    }

    /**
     * Check the device to make sure it has the Google Play Services APK.
     *
     * @return Returns true if Google Api is available.
     */
    private boolean isPlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(appContext);
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            return true;
        }
        return false;
    }
//    public HashMap<String, Schedule> scheduleMap;
//    public HashMap<String, Scene> sceneMap;
//    public HashMap<String, EspLocalDevice> localDeviceMap;
//
//    private static final String TAG = EspApplication.class.getSimpleName();
//
//    private AppState appState = AppState.NO_USER_LOGIN;
//
//    public enum AppState {
//        NO_USER_LOGIN,
//        GETTING_DATA,
//        GET_DATA_SUCCESS,
//        GET_DATA_FAILED,
//        NO_INTERNET,
//        REFRESH_DATA
//    }
//
//    public HashMap<String, EspNode> nodeMap;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.d(TAG, "ESP Application is created");
//        nodeMap = new HashMap<>();
//        scheduleMap = new HashMap<>();
//        sceneMap = new HashMap<>();
////        localDeviceMap = new HashMap<>();
////        groupMap = new HashMap<>();
////        automations = new HashMap<>();
////
////        appPreferences = getSharedPreferences(AppConstants.ESP_PREFERENCES, Context.MODE_PRIVATE);
////        apiManager = ApiManager.getInstance(this);
////        ESPProvisionManager.getInstance(this);
////        if (BuildConfig.isLocalControlSupported) {
////            mdnsManager = mDNSManager.getInstance(getApplicationContext(), AppConstants.MDNS_SERVICE_TYPE, listener);
////        }
////
////        if (isPlayServicesAvailable()) {
////            FirebaseMessaging.getInstance().setAutoInitEnabled(false);
////            setupNotificationChannels();
////        }
//    }
//
//    private void clearData() {
//        EspDatabase.getInstance(this).getNodeDao().deleteAll();
//        EspDatabase.getInstance(this).getGroupDao().deleteAll();
//        EspDatabase.getInstance(this).getNotificationDao().deleteAll();
//        nodeMap.clear();
//        scheduleMap.clear();
//        sceneMap.clear();
////        localDeviceMap.clear();
////        groupMap.clear();
////        automations.clear();
//    }
//
////    public void refreshData() {
////        if (!appState.equals(AppState.GETTING_DATA)) {
////            changeAppState(AppState.REFRESH_DATA, null);
////        }
////    }

}
