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

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.AppConstants;
import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.ESPProvisionManager;
import com.espressif.ui.adapters.CardAdapter;
import com.espressif.ui.login.AreaLandingActivity;
import com.espressif.ui.login.LoginActivity;
import com.espressif.ui.models.DeviceInfo;
import com.espressif.ui.models.Devices;
import com.espressif.ui.models.NodeResponseModel;
import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.espressif.ui.models.provisioninglabel.ProvisioningRequest;
import com.espressif.ui.models.provisioninglabel.ProvisioningResponse;
import com.espressif.wifi_provisioning.BuildConfig;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EspMainActivity extends AppCompatActivity {

    private static final String TAG = EspMainActivity.class.getSimpleName();

    NodeViewModel nodeViewModel;
    // Request codes
    private static final int REQUEST_LOCATION = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private ESPProvisionManager provisionManager;
    private CardView btnAddDevice;
    private ImageView ivEsp;
    private SharedPreferences sharedPreferences;
    private String deviceType;


    /////////////////
    private EspApplication espApp;
    CardView cardView;
    Switch switchButton;
    String power;
    private ApiService apiService;
    EspMainActivity espMainActivity;

    private ArrayList<Devices> arrayList;
    private RecyclerView recyclerView;
    //Intent intent = getIntent();

    // Retrieve the string using the key
    // String nodeId = intent.getStringExtra("keyStringData");

    String nodeId;
    String nodeId2;
    String mac;
    Long gaaProjectSpaceTypePlannedDeviceRef;

    Context context = this;

    private static final String PREFS_NAME = "MyPrefsFile";

    ////////////////////

   // private ApiService apiService2 = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    private static EspMainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esp_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleViewCard);

        instance = this;


        arrayList = new ArrayList<>();

        setSupportActionBar(toolbar);
        initViews();
        Log.d(TAG, "onCreate: " +mac);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        mac = preferences.getString("mac", "");
        Log.d(TAG, "onCreate Mac: " +mac);

        SharedPreferences preferences7 = getSharedPreferences("my_shared_pref", MODE_PRIVATE);
        gaaProjectSpaceTypePlannedDeviceRef = preferences7.getLong("KEY_USERNAME", 0L);
        Log.d(TAG, "esp GaaProjectSpaceTypeRef2: " +gaaProjectSpaceTypePlannedDeviceRef);

///////////////////////////////////
        SharedPreferences preferences16 = getSharedPreferences("my_shared_prefty", MODE_PRIVATE);
        boolean provision = preferences16.getBoolean("KEY_USERNAMEw", false);
        Log.d(TAG, "esp GaaProjectSpaceTypeRef2: " +gaaProjectSpaceTypePlannedDeviceRef);

        ///////////////////////////


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
        String username = sharedPreferences5.getString("PROJECT", "");
        String gaaProjectSpaceRef = username.trim();

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id: " +nodeId2);




        if(provision == false) {

            GetNodeID(userId, loginDeviceId, mac, gaaProjectSpaceRef, gaaProjectSpaceTypePlannedDeviceRef);

        }else {
            getDevice();

        }
        // getNodeID();
       // node();

        //getNodeID2();
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this,2);

        SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
        String nodeId2 = preferences2.getString("nodeId", "");
        Log.d(TAG, "SharedPre node Id: " +nodeId);
     //   getDevice();




//        cardView = findViewById(R.id.node_cardView);
//        if(mac == null || mac == ""){
//            cardView.setVisibility(View.INVISIBLE);
//        } else {
//            cardView.setVisibility(View.VISIBLE);
//        }

      //  recyclerView = findViewById(R.id.recycleViewCard);
        if(mac == null || mac == ""){
            recyclerView.setVisibility(View.INVISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
        }

      //  switchButton = findViewById(R.id.switch_btn);
       // switchButton = findViewById(R.id.switch_btn);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://enscloud.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService
        apiService = retrofit.create(ApiService.class);

        // Find your switch in the layout
//        Switch switchButton = findViewById(R.id.switch_btn);
//
//     //    Set a listener on the switch button
//        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Handle switch state change
//                sendSwitchState(isChecked);
//            }
//        });
        //////

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
//

//                        SharedPreferences preferences16 = getSharedPreferences("my_shared_prefty", MODE_PRIVATE);
//                        boolean provision = preferences16.getBoolean("KEY_USERNAMEw", false);
//                        Log.d(TAG, "esp GaaProjectSpaceTypeRef2: " +gaaProjectSpaceTypePlannedDeviceRef);

                     //   preferences16.updateBoolean(getApplicationContext(), true);
                        Intent intent = new Intent(getApplicationContext(), AreaLandingActivity.class);
                        startActivity(intent);
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


//    private void getNodeID() {
//        Log.d(TAG, "getNodeID: "+mac);
//        // Initialize nodeViewModel
//        nodeViewModel = new ViewModelProvider(this).get(NodeViewModel.class);
//        nodeViewModel.getNode(mac);
//        Log.e(TAG, "getNodeID: "+nodeId);
//        nodeViewModel.getNodeLiveData().observe(this, nodeResponse -> {
//            // Handle successful response
//        });
//
//        nodeViewModel.getErrorLiveData().observe(this, error -> {
//            // Handle error
//        });
//    }

    //Get Device
        private void getDevice() {
          //  String NodeId = "WI84xt861kS39p2b5sXeGQ";
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<DeviceInfo> call = apiService.getAllData(nodeId2);
            Log.e(TAG, "getDevice: "+nodeId2 );

            call.enqueue(new Callback<DeviceInfo>() {
                @Override
                public void onResponse(Call<DeviceInfo> call, Response<DeviceInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<DeviceInfo.Device> devices = response.body().getDevices();
                        for (DeviceInfo.Device device : devices) {
                            List<DeviceInfo.Param> params = device.getParams();
                            Log.e(TAG, "Device Type: "+device.getName());
                            for (DeviceInfo.Param param : params) {
                                String name = param.getUi_type();
                                Log.e(TAG, "onResponse: "+param.getName());
                                String uiType = param.getUi_type();
                                Log.e(TAG, "onResponse22222: "+param.getUi_type());

                                //arrayList.add(new Devices(Devices.getName(),Devices.getType(),Devices.getData_type(),param.getUi_type()));
                                // Use the name and uiType as needed
                            }
                            arrayList.add(new Devices(device.getName(),device.getType(),device.getPrimary()));
                        }
                    } else {
                        // Handle unsuccessful response
                    }
                    CardAdapter cardAdapter = new CardAdapter(arrayList);
                    recyclerView.setAdapter(cardAdapter);
                    GridLayoutManager gridLayoutManager1 = new GridLayoutManager(EspMainActivity.this,2, GridLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(gridLayoutManager1);
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
    }

    //
    public void sendSwitchState(boolean powerState,String name,String power) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Create a RequestModel with the required data
        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");

          //Change
//        nodeId = "WI84xt861kS39p2b5sXeGQ";
 //       requestModel.setTopic("node/"+ nodeId +"/params/remote");

       requestModel.setMessage("{\""+name+"\": {\""+power+"\": " + powerState + "}}");
       // requestModel.setMessage("{\"Fan 1\": {\"Power\": " + powerState + "}}");
        Log.e(TAG, "sendSwitchState: "+name );

         // requestModel.setQosLevel(0);
        // Make the API call
        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();
                    handleApiResponse(responseModel);

                } else {
                    // Handle unsuccessful response
                    Toast.makeText(EspMainActivity.this, "Failed to make the API call2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(EspMainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

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




//        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Check if the switch button is checked
//                if (isChecked) {
//                    // Make the API call
//                    power = "true";
//                    Log.d(TAG, "onCheckedChanged: " +power);
//                    makeApiCall();
//                }
//                // Check if the switch button is unchecked
//
//                else {
//                    power = "false";
//                    Log.d(TAG, "onCheckedChanged: " +power);
//                    makeApiCall();
//                }
//            }
//        });
//
//    }
//
//    private void makeApiCall() {
//        // Create an instance of ApiRequest
//        ApiRequest apiRequest = new ApiRequest(0, "node/<<"+node_id+">>/params/remote",
//                "{\"Switch\": {\"Power\": "+power+"}}", 0);
//
//        // Get the ApiService instance
//        ApiService apiService = ApiClient.getApiService();
//
//        // Make the POST request
//        Call<Void> call = apiService.postApiRequest(apiRequest);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    // Request was successful
//                    Log.d(TAG, "onResponse: succesfull");
//                } else {
//                    // Handle unsuccessful request
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                // Handle network error
//            }
//        });
//    }

//    private void node() {
//        if(nodeId != null){
//            cardView.setVisibility(View.VISIBLE);
//        }
//    }

    /////////////////////

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
