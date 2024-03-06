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
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.espressif.wifi_provisioning.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FanActivity extends AppCompatActivity {

    EspMainActivity espMainActivity;
    private SharedPreferences sharedPreferences;

    Switch fanswitch;
   // String nodeId;
    private ProgressBar progressBar;
    private Button incrementButton, decrementButton;
    private TextView progressTextView;
    //String nodeId2;
    private int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);

//        SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
//        nodeId = preferences2.getString("nodeId", "");
//        Log.d(TAG, "Fannodeee: "+nodeId);

//        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
//        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
//        Log.d(TAG, "node id: " +nodeId2);

        fanswitch = findViewById(R.id.switchButtonFan);

        //Set a listener on the switch button
        fanswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch state change
                Log.d(TAG, "onCheckedChanged: "+isChecked);
                sendSwitchState(isChecked);
            }
        });

        //Progress Bar Code
        progressBar = findViewById(R.id.progressBar);
        incrementButton = findViewById(R.id.incrementButton);
        decrementButton = findViewById(R.id.decrementButton);
        progressTextView = findViewById(R.id.progressTextView);

        updateProgressText();

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementProgress();

            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementProgress();

            }
        });
    }

    private void incrementProgress() {
        if (progress < 5) {
            progress++;
            progressBar.setProgress(progress);
            updateProgressText();
            sendFanSpeed(progress);
            Log.e(TAG, "incrementProgress: "+progress);
        }
    }

    private void decrementProgress() {
        if (progress > 0) {
            progress--;
            progressBar.setProgress(progress);
            updateProgressText();
            sendFanSpeed(progress);
            Log.e(TAG, "decrementProgress: "+progress);
        }
    }

    private void updateProgressText() {
        progressTextView.setText("" + progress + "");
    }

    //Fan Speed Method
    private void sendFanSpeed(int fanSpeed){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");


        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        Log.e(TAG, "curtainAction: "+message );

        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
        Log.d(TAG, "sendFanSpeed: "+fanSpeed);
        requestModel.setMessage("{\""+message+"\": {\"Speed\": " + fanSpeed + "}}");


        //requestModel.setQosLevel(0);
        Log.d(TAG, "sendFanSpeed: "+requestModel.getMessage());
        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();
                    handleApiResponse(responseModel);
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(FanActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(FanActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendSwitchState(boolean powerState) {
        // Create a RequestModel with the required data
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id: " +nodeId2);
        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        String primary = intent.getStringExtra("PRIMARY_KEY");
        Log.e(TAG, "curtainAction: "+message );
       // Log.e(TAG, "curtainAction: "+primary );

        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");

        //Change
//        nodeId = "WI84xt861kS39p2b5sXeGQ";
//        requestModel.setTopic("node/"+ nodeId +"/params/remote");

        requestModel.setMessage("{\""+message+"\": {\"Power\": "+powerState+"}}");
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
                    Toast.makeText(FanActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(FanActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
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