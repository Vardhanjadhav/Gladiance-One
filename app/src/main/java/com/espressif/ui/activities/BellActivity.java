package com.espressif.ui.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.espressif.NetworkApiManager;
import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.espressif.wifi_provisioning.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BellActivity extends AppCompatActivity {

    Switch bellswitch, serviceswitch;
    Button button;
    String nodeId;
    NetworkApiManager networkApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bell);

        button = findViewById(R.id.btn_true);
        bellswitch = findViewById(R.id.btn_switch);
        serviceswitch = findViewById(R.id.btn_switch2);


        SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
        nodeId = preferences2.getString("nodeId", "");
        Log.d(TAG, "Fannodeee: "+nodeId);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean powerState = true;

                sendSwitchStateTrue(powerState);

            }
        });


        //Set a listener on the switch button
        bellswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch state change
                Log.d(TAG, "onCheckedChanged: "+isChecked);
                sendSwitchState(isChecked);
            }
        });

        serviceswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch state change
                Log.d(TAG, "onCheckedChanged: "+isChecked);
                sendServiceState(isChecked);
            }
        });


    }


    private void sendSwitchStateTrue(boolean powerState) {
        // Create a RequestModel with the required data
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id: " +nodeId2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        String primary = intent.getStringExtra("PRIMARY_KEY");
        Log.e(TAG, "curtainAction: "+message );
        Log.e(TAG, "curtainAction: "+primary );

        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");

        requestModel.setMessage("{\""+message+"\": {\""+primary+"\": "+powerState+"}}");
        Log.d(TAG, "sendSwitchState: "+powerState);
        //  requestModel.setQosLevel(0);
        // Make the API call
        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();
                    Log.d(TAG, "onResponse: "+responseModel);
                    handleApiResponse(responseModel);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(BellActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(BellActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void sendSwitchState(boolean powerState) {
        // Create a RequestModel with the required data
        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        String primary = intent.getStringExtra("PRIMARY_KEY");
        String commandBody = "{\""+message+"\": {\""+primary+"\": "+powerState+"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);
        /////////////////////////////////////////

//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
//        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
//        Log.d(TAG, "node id: " +nodeId2);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra("MESSAGE_KEY");
//        String primary = intent.getStringExtra("PRIMARY_KEY");
//        Log.e(TAG, "curtainAction: "+message );
//        Log.e(TAG, "curtainAction: "+primary );
//
//
//        RequestModel requestModel = new RequestModel();
//        requestModel.setSenderLoginToken(0);
//        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
//
//        requestModel.setMessage("{\""+message+"\": {\""+primary+"\": "+powerState+"}}");
//        Log.d(TAG, "sendSwitchState: "+powerState);
//        //  requestModel.setQosLevel(0);
//        // Make the API call
//        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.isSuccessful()) {
//                    ResponseModel responseModel = response.body();
//                    Log.d(TAG, "onResponse: "+responseModel);
//                    handleApiResponse(responseModel);
//                } else {
//                    // Handle unsuccessful response
//                    Toast.makeText(BellActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(BellActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    //service

    private void sendServiceState(boolean powerState) {
        // Create a RequestModel with the required data
        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        String primary = intent.getStringExtra("PRIMARY_KEY");
        String commandBody = "{\""+message+"\": {\""+primary+"\": "+powerState+"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        ///////////////////////////////
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra("MESSAGE_KEY");
//        String primary = intent.getStringExtra("PRIMARY_KEY");
//        Log.e(TAG, "curtainAction: "+message );
//        Log.e(TAG, "curtainAction: "+primary );
//
//        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
//        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
//        Log.d(TAG, "node id: " +nodeId2);
//
//        RequestModel requestModel = new RequestModel();
//        requestModel.setSenderLoginToken(0);
//        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
//
//        requestModel.setMessage("{\""+message+"\": {\""+primary+"\": "+powerState+"}}");
//        Log.d(TAG, "sendSwitchState: "+powerState);
//        //  requestModel.setQosLevel(0);
//        // Make the API call
//        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.isSuccessful()) {
//                    ResponseModel responseModel = response.body();
//                    Log.d(TAG, "onResponse: "+responseModel);
//                    handleApiResponse(responseModel);
//                } else {
//                    // Handle unsuccessful response
//                    Toast.makeText(BellActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(BellActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private void handleApiResponse(ResponseModel responseModel) {
        // Handle the response as needed
        if (responseModel != null) {
            // API call was successful
            // Access other fields from responseModel if needed
            Log.d(TAG, "handleApiResponse: " +responseModel.getSuccessful());
            Log.d(TAG, "handleApiResponse: " +responseModel.getTag());

            Toast.makeText(this, "Switch state updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Handle unsuccessful response
            Toast.makeText(this, "Failed to update switch state", Toast.LENGTH_SHORT).show();
        }
    }



}