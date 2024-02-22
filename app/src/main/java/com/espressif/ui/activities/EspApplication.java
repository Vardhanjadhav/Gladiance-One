package com.espressif.ui.activities;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.espressif.AppConstants;
import com.espressif.db.EspDatabase;
import com.espressif.provisioning.ESPProvisionManager;
import com.espressif.ui.models.Scene;
import com.espressif.ui.models.Schedule;

import java.util.HashMap;

public class EspApplication extends Application {
    public HashMap<String, Schedule> scheduleMap;
    public HashMap<String, Scene> sceneMap;

    private static final String TAG = EspApplication.class.getSimpleName();

    private AppState appState = AppState.NO_USER_LOGIN;

    public enum AppState {
        NO_USER_LOGIN,
        GETTING_DATA,
        GET_DATA_SUCCESS,
        GET_DATA_FAILED,
        NO_INTERNET,
        REFRESH_DATA
    }

    public HashMap<String, EspNode> nodeMap;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ESP Application is created");
        nodeMap = new HashMap<>();
        scheduleMap = new HashMap<>();
        sceneMap = new HashMap<>();
//        localDeviceMap = new HashMap<>();
//        groupMap = new HashMap<>();
//        automations = new HashMap<>();
//
//        appPreferences = getSharedPreferences(AppConstants.ESP_PREFERENCES, Context.MODE_PRIVATE);
//        apiManager = ApiManager.getInstance(this);
//        ESPProvisionManager.getInstance(this);
//        if (BuildConfig.isLocalControlSupported) {
//            mdnsManager = mDNSManager.getInstance(getApplicationContext(), AppConstants.MDNS_SERVICE_TYPE, listener);
//        }
//
//        if (isPlayServicesAvailable()) {
//            FirebaseMessaging.getInstance().setAutoInitEnabled(false);
//            setupNotificationChannels();
//        }
    }

    private void clearData() {
        EspDatabase.getInstance(this).getNodeDao().deleteAll();
        EspDatabase.getInstance(this).getGroupDao().deleteAll();
        EspDatabase.getInstance(this).getNotificationDao().deleteAll();
        nodeMap.clear();
        scheduleMap.clear();
        sceneMap.clear();
//        localDeviceMap.clear();
//        groupMap.clear();
//        automations.clear();
    }

//    public void refreshData() {
//        if (!appState.equals(AppState.GETTING_DATA)) {
//            changeAppState(AppState.REFRESH_DATA, null);
//        }
//    }

}
