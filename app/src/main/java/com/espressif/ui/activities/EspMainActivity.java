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

package com.espressif.ui.activities;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.AppConstants;
import com.espressif.NetworkApiManager;
import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.ESPProvisionManager;
import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.CardAdapter;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.activities.Home.ProjectSpaceLandingActivity;
import com.espressif.ui.models.DeviceInfo;
import com.espressif.ui.models.Devices;
import com.espressif.ui.models.NodeResponseModel;
import com.espressif.ui.models.ResetResponse;
import com.espressif.ui.models.ResponseModel;
import com.espressif.ui.models.provisioninglabel.ProvisioningRequest;
import com.espressif.ui.models.provisioninglabel.ProvisioningResponse;
import com.espressif.wifi_provisioning.BuildConfig;
import com.espressif.wifi_provisioning.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspMainActivity extends AppCompatActivity {

    private static final String TAG = EspMainActivity.class.getSimpleName();

    //NodeViewModel nodeViewModel;
    // Request codes
    private static final int REQUEST_LOCATION = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private ESPProvisionManager provisionManager;
    private CardView btnAddDevice;
    private ImageView ivEsp;
    private SharedPreferences sharedPreferences;
    private String deviceType;

    NetworkApiManager networkApiManager;

    /////////////////
    private EspApplication espApp;
    CardView cardView;
    Switch switchButton;
    String power;
    private ApiService apiService;
    EspMainActivity espMainActivity;
    Button resetButton;

    private ArrayList<Devices> arrayList;
    private RecyclerView recyclerView;

    String nodeId;
    String nodeId2;
    String mac;
    Long gaaProjectSpaceTypePlannedDeviceRef;

    Context context = this;

    TextView tvMessage;

    Button btnYes,btnNo;

    private static final String PREFS_NAME = "MyPrefsFile";

    private static EspMainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esp_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleViewCard);

        resetButton = findViewById(R.id.btn_reset);
        resetButton.setVisibility(View.GONE);
        instance = this;

        espApp = new EspApplication(getApplicationContext());
        networkApiManager = new NetworkApiManager(context.getApplicationContext(), espApp);
        arrayList = new ArrayList<>();

        setSupportActionBar(toolbar);
        initViews();
   //     Log.d(TAG, "onCreate2: " +mac);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        mac = preferences.getString("mac", "");
        Log.d(TAG, "onCreate Mac: " +mac);

        SharedPreferences preferences7 = getSharedPreferences("my_shared_pref", MODE_PRIVATE);
        gaaProjectSpaceTypePlannedDeviceRef = preferences7.getLong("KEY_USERNAME", 0L);
        Log.d(TAG, "esp GaaProjectSpaceTypeRef2: " +gaaProjectSpaceTypePlannedDeviceRef);

        SharedPreferences preferences16 = getSharedPreferences("my_shared_prefty", MODE_PRIVATE);
        boolean provision = preferences16.getBoolean("KEY_USERNAMEw", false);
        Log.d(TAG, "esp Provision: " +provision);



        SharedPreferences sharedPreferences8 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences8);
        Log.e(TAG, "esp Project Space GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();


        SharedPreferences  sharedPreferences9 = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String userId2 = sharedPreferences9.getString("LoginToken", "");
        Log.e(TAG, "esp Project Space loginToken: "+userId2 );
        String userId = userId2.trim();

        SharedPreferences  sharedPreferences10 = getSharedPreferences("MyPreferencesDN", Context.MODE_PRIVATE);
        String savedUserDeviceName = sharedPreferences10.getString("UserDisplayName", "");
        Log.e(TAG, "esp User Device Name2: "+savedUserDeviceName );
        String userDeviceName = savedUserDeviceName.trim();


        SharedPreferences sharedPreferences5 = getSharedPreferences("MyPrefsPSR", Context.MODE_PRIVATE);
        String username = sharedPreferences5.getString("Project_Space_Ref", "");
        String gaaProjectSpaceRef = username.trim();

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id: " +nodeId2);

        SharedPreferences sharedPreferencesDeviceRef = getSharedPreferences("MyPreferencesPDR", Context.MODE_PRIVATE);
        String DeviceRef = sharedPreferencesDeviceRef.getString("SpaceTypePlannedDeviceRef", "");
        Log.e(TAG, "Space Type Planned DeviceRef : "+DeviceRef);


        // BeastLogic

//        if(provision == false) {
//            GetNodeID(userId, loginDeviceId, mac, gaaProjectSpaceRef, gaaProjectSpaceTypePlannedDeviceRef);
//        }else {
            getDevice();
     //   }

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Are you sure you want to Reset";
                showCustomDialogBox(message);
            }

            private void showCustomDialogBox(String message) {

                final Dialog dialog = new Dialog(EspMainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layout_custom_dailog);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                tvMessage = dialog.findViewById(R.id.tvMessage);
                btnYes = dialog.findViewById(R.id.btn_Yes);
                btnNo = dialog.findViewById(R.id.btn_No);

                tvMessage.setText(message);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
                        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
                        String nodeId3 = preferences9.getString("KEY_USERNAMEs", "");
                        Log.d(TAG, "node id2: " +nodeId3);
                        // Make API call
                        Call<ResetResponse> call = apiService.factoryResetNode(nodeId3, userId2);
                        call.enqueue(new Callback<ResetResponse>() {
                            @Override
                            public void onResponse(Call<ResetResponse> call, Response<ResetResponse> response) {
                                if (response.isSuccessful()) {
                                    ResetResponse responseModel = response.body();
                                    if (responseModel != null) {
                                        boolean success = responseModel.getSuccessful();
                                        String message = responseModel.getMessage();
                                        Log.d(TAG, "Success: " + success + ", Message: " + message);

                                        Intent intent = new Intent(EspMainActivity.this, ProjectSpaceLandingActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(getApplicationContext(), "Device Remove Successfully...", Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    Log.e(TAG, "API call failed with code: " + response.code());
                                }
                            }
                            @Override
                            public void onFailure(Call<ResetResponse> call, Throwable t) {
                                Log.e(TAG, "API call failed: " + t.getMessage());
                            }
                        });

                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

//        if(mac == null || mac == ""){
//            recyclerView.setVisibility(View.INVISIBLE);
//        }else{
//            recyclerView.setVisibility(View.VISIBLE);
//        }

        sharedPreferences = getSharedPreferences(AppConstants.ESP_PREFERENCES, Context.MODE_PRIVATE);
        provisionManager = ESPProvisionManager.getInstance(getApplicationContext());

    }

    public static EspMainActivity getInstance() {
        return instance;
    }

    private void getNodeID2() {
        ApiService apiService2 = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        // Make the API call
        Call<NodeResponseModel> call = apiService2.getData2(mac);
        call.enqueue(new Callback<NodeResponseModel>() {
            @Override
            public void onResponse(Call<NodeResponseModel> call, Response<NodeResponseModel> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    NodeResponseModel data = response.body();
                    String nodeId = response.body().getNodeId();
                    Log.d(TAG, "NodeId1: "+nodeId);

                    storeNodeId(nodeId);

                    // Do something with the data
                } else {
                    // Handle error
                    // Response is not successful (may be server error, etc.)
                }
            }

            @Override
            public void onFailure(Call<NodeResponseModel> call, Throwable t) {
                // Handle failure
                // Failed to make API call (network error, timeout, etc.)
            }
        });
    }

    public void storeNodeId(String nodeId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefse", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nodeId", nodeId);
        Log.d(TAG, "storeNodeId: "+nodeId);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefse", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }



    ////////// get node id new post api ///
    public void GetNodeID(String userId, String loginDeviceId, String macId, String gaaProjectSpaceRef, Long gaaProjectSpaceTypePlannedDeviceRef) {
        // Create an instance of ApiService
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        // Prepare login request

        ProvisioningRequest loginRequest = new ProvisioningRequest(userId,loginDeviceId,macId,gaaProjectSpaceRef,gaaProjectSpaceTypePlannedDeviceRef);

        // Make API call
        Call<ProvisioningResponse> call = apiService.postAssociateNodeToPlannedDevice(loginRequest);
        call.enqueue(new Callback<ProvisioningResponse>() {
            @Override
            public void onResponse(Call<ProvisioningResponse> call, Response<ProvisioningResponse> response) {
                if (response.isSuccessful()) {
                    ProvisioningResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.isSuccessful()) {
                        // Handle successful response
                        Log.e("espmain", "Successful: " + loginResponse.isSuccessful());
                        Log.e("espmain", "Message: " + loginResponse.getMessage());
                        Toast.makeText(EspMainActivity.this, ""+loginResponse.isSuccessful(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("espmain", "Unsuccessful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProvisioningResponse> call, Throwable t) {
                // Handle failure
                Log.e("LoginResponse", "Failure: " + t.getMessage());
            }
        });
    }


    //Get Device
    private void getDevice() {
        //  String NodeId = "WI84xt861kS39p2b5sXeGQ";
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId4 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id4: " +nodeId4);

        Call<DeviceInfo> call = apiService.getAllData(nodeId4);
        Log.e(TAG, "getDevice: "+nodeId4 );

        call.enqueue(new Callback<DeviceInfo>() {
            @Override
            public void onResponse(Call<DeviceInfo> call, Response<DeviceInfo> response) {
                if (response.isSuccessful() && response.body() != null) {

//                        String n = String.valueOf(response.body());
                    Log.d(TAG, "onResponse: "+response.body());
                    Gson gson = new Gson();
                    String n = gson.toJson(response.body());

                    SharedPreferences sharedPreferences10 = context.getSharedPreferences("my_shared_prefet", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences10.edit();
                    //  Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: "+nodeId );
                    editor.putString("KEY_USERNAMEst", n);
                    editor.apply();

                    List<DeviceInfo.Device> devices = response.body().getDevices();
                    for (DeviceInfo.Device device : devices) {
                        List<DeviceInfo.Param> params = device.getParams();
                        Log.e(TAG, "Device Type: "+device.getName());
                        Log.e(TAG, "Device Type: "+device.getType());
                        for (DeviceInfo.Param param : params) {
                            String name = param.getUi_type();
                            Log.e(TAG, "onResponse: "+param.getName());
                            String uiType = param.getUi_type();
                            Log.e(TAG, "onResponse22222: "+param.getUi_type());
                            Log.e(TAG, "onResponse22222: "+uiType);



                            //arrayList.add(new Devices(Devices.getName(),Devices.getType(),Devices.getData_type(),param.getUi_type()));
                            // Use the name and uiType as needed
                        }
                        arrayList.add(new Devices(device.getName(),device.getType(),device.getPrimary()));
                    }
                    CardAdapter cardAdapter = new CardAdapter(arrayList);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(cardAdapter);
                    GridLayoutManager gridLayoutManager1 = new GridLayoutManager(EspMainActivity.this,2, GridLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(gridLayoutManager1);
                } else {
                    // Handle unsuccessful response
                }

            }

            @Override
            public void onFailure(Call<DeviceInfo> call, Throwable t) {
                // Handle failure
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        deviceType = sharedPreferences.getString(AppConstants.KEY_DEVICE_TYPES, AppConstants.DEVICE_TYPE_DEFAULT);
        if (deviceType.equals("wifi")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(AppConstants.KEY_DEVICE_TYPES, AppConstants.DEVICE_TYPE_DEFAULT);
            editor.apply();
        }

        deviceType = sharedPreferences.getString(AppConstants.KEY_DEVICE_TYPES, AppConstants.DEVICE_TYPE_DEFAULT);
        if (deviceType.equals(AppConstants.DEVICE_TYPE_BLE)) {
            ivEsp.setImageResource(R.drawable.ic_esp_ble);
        } else if (deviceType.equals(AppConstants.DEVICE_TYPE_SOFTAP)) {
            ivEsp.setImageResource(R.drawable.ic_esp_softap);
        } else {
            ivEsp.setImageResource(R.drawable.ic_esp);
        }
        espApp.refreshData();
    }

    //
    public void sendSwitchState(boolean powerState,String name,String power) {

        String commandBody = "{\""+name+"\": {\""+power+"\": " + powerState + "}}";
        String message = powerState ? "on" : "off";
        Toast.makeText(this, "Switch is "+message, Toast.LENGTH_SHORT).show();
        boolean shPowerState = powerState;
        SharedPreferences sharedPreferencesPowerState = getSharedPreferences("MyPreferencesPS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesPowerState.edit();
        editor.putBoolean("PowerState", shPowerState);
        editor.apply();
        Log.e(TAG, "EspMain Activity PowerState:"+shPowerState );

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        // Create a RequestModel with the required data
//        RequestModel requestModel = new RequestModel();
//        requestModel.setSenderLoginToken(0);
//        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
//
//          //Change
////        nodeId = "WI84xt861kS39p2b5sXeGQ";
// //       requestModel.setTopic("node/"+ nodeId +"/params/remote");
//
//       requestModel.setMessage(commandBody);
//
//
//
//       // requestModel.setMessage("{\"Fan 1\": {\"Power\": " + powerState + "}}");
//        Log.e(TAG, "sendSwitchState: "+name );
//
//         // requestModel.setQosLevel(0);
//        // Make the API call
//        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.isSuccessful()) {
//                    ResponseModel responseModel = response.body();
//                    handleApiResponse(responseModel);
//
//                } else {
//                    // Handle unsuccessful response
//                    Toast.makeText(EspMainActivity.this, "Failed to make the API call2", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(EspMainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void handleApiResponse(ResponseModel responseModel) {
        // Handle the response as needed
        if (responseModel != null) {
            // API call was successful
            // Access other fields from responseModel if needed
            Log.d(TAG, "handleApiResponse: " +responseModel.getSuccessful());
            Log.d(TAG, "handleApiResponse: " +responseModel.getMessage());

            Toast.makeText(this, "Switch state updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Handle unsuccessful response
            Toast.makeText(this, "Failed to update switch state", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (BuildConfig.isSettingsAllowed) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_settings, menu);
            return true;
        } else {
            menu.clear();
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOCATION) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                if (isLocationEnabled()) {
                    addDeviceClick();
                }
            }
        }

        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            Toast.makeText(this, "Bluetooth is turned ON, you can provision device now.", Toast.LENGTH_LONG).show();
        }
    }


    private void initViews() {

        ivEsp = findViewById(R.id.iv_esp);
        btnAddDevice = findViewById(R.id.btn_provision_device);
        btnAddDevice.setVisibility(View.GONE);
        btnAddDevice.findViewById(R.id.iv_arrow).setVisibility(View.GONE);
        btnAddDevice.setOnClickListener(addDeviceBtnClickListener);

        TextView tvAppVersion = findViewById(R.id.tv_app_version);

        String version = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String appVersion = getString(R.string.app_version) + " - v" + version;
        tvAppVersion.setText(appVersion);
    }

    View.OnClickListener addDeviceBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                if (!isLocationEnabled()) {
                    askForLocation();
                    return;
                }
            }
            addDeviceClick();
        }
    };

    private void addDeviceClick() {

        if (BuildConfig.isQrCodeSupported) {

            gotoQrCodeActivity();

        } else {

            if (deviceType.equals(AppConstants.DEVICE_TYPE_BLE) || deviceType.equals(AppConstants.DEVICE_TYPE_BOTH)) {

                final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                BluetoothAdapter bleAdapter = bluetoothManager.getAdapter();

                if (!bleAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                } else {
                    startProvisioningFlow();
                }
            } else {
                startProvisioningFlow();
            }
        }
    }

    private void startProvisioningFlow() {

        deviceType = sharedPreferences.getString(AppConstants.KEY_DEVICE_TYPES, AppConstants.DEVICE_TYPE_DEFAULT);
        final boolean isSec1 = sharedPreferences.getBoolean(AppConstants.KEY_SECURITY_TYPE, true);
        Log.d(TAG, "Device Types : " + deviceType);
        Log.d(TAG, "isSec1 : " + isSec1);
        int securityType = 0;
        if (isSec1) {
            securityType = 1;
        }

        if (deviceType.equals(AppConstants.DEVICE_TYPE_BLE)) {

            if (isSec1) {
                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_BLE, ESPConstants.SecurityType.SECURITY_1);
            } else {
                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_BLE, ESPConstants.SecurityType.SECURITY_0);
            }
            goToBLEProvisionLandingActivity(securityType);

        } else if (deviceType.equals(AppConstants.DEVICE_TYPE_SOFTAP)) {

            if (isSec1) {
                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_SOFTAP, ESPConstants.SecurityType.SECURITY_1);
            } else {
                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_SOFTAP, ESPConstants.SecurityType.SECURITY_0);
            }
            goToWiFiProvisionLandingActivity(securityType);

        } else {

            final String[] deviceTypes = {"BLE", "SoftAP"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(R.string.dialog_msg_device_selection);
            final int finalSecurityType = securityType;
            builder.setItems(deviceTypes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int position) {

                    switch (position) {
                        case 0:

                            if (isSec1) {
                                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_BLE, ESPConstants.SecurityType.SECURITY_1);
                            } else {
                                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_BLE, ESPConstants.SecurityType.SECURITY_0);
                            }
                            goToBLEProvisionLandingActivity(finalSecurityType);
                            break;

                        case 1:

                            if (isSec1) {
                                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_SOFTAP, ESPConstants.SecurityType.SECURITY_1);
                            } else {
                                provisionManager.createESPDevice(ESPConstants.TransportType.TRANSPORT_SOFTAP, ESPConstants.SecurityType.SECURITY_0);
                            }
                            goToWiFiProvisionLandingActivity(finalSecurityType);
                            break;
                    }
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }

    private void askForLocation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(R.string.dialog_msg_gps);

        // Set up the buttons
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_LOCATION);
            }
        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private boolean isLocationEnabled() {

        boolean gps_enabled = false;
        boolean network_enabled = false;
        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Activity.LOCATION_SERVICE);

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        Log.d(TAG, "GPS Enabled : " + gps_enabled + " , Network Enabled : " + network_enabled);

        boolean result = gps_enabled || network_enabled;
        return result;
    }

    private void gotoQrCodeActivity() {
        Intent intent = new Intent(EspMainActivity.this, AddDeviceActivity.class);
        startActivity(intent);
    }

    private void goToBLEProvisionLandingActivity(int securityType) {

        Intent intent = new Intent(EspMainActivity.this, BLEProvisionLanding.class);
        intent.putExtra(AppConstants.KEY_SECURITY_TYPE, securityType);
        startActivity(intent);
    }

    private void goToWiFiProvisionLandingActivity(int securityType) {

        Intent intent = new Intent(EspMainActivity.this, ProvisionLanding.class);
        intent.putExtra(AppConstants.KEY_SECURITY_TYPE, securityType);
        startActivity(intent);
    }
}