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
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.espressif.wifi_provisioning.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DimmerActivity extends AppCompatActivity {

    Switch dimmerswitch;
    String nodeId;
    TextView textView;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimmer);


        SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
        nodeId = preferences2.getString("nodeId", "");
        Log.d(TAG, "Fannodeee: " + nodeId);



        dimmerswitch = findViewById(R.id.switchButtonDimmer);
        seekBar = findViewById(R.id.seekBarDimmer);
        textView = findViewById(R.id.textView);

        //Dimmer ON/OFF Code
        dimmerswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch state change
                Log.d(TAG, "onCheckedChanged: "+isChecked);
                dimmerState(isChecked);
            }
        });

        seekBar.setMax(99);

        // Setting initial progress
        seekBar.setProgress(0);
        textView.setText("0");

        // SeekBar change listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Display current progress value
                textView.setText(String.valueOf(progress + 1));

                // Send value to server
                dimmerProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed
            }
        });

    }

    //Dimmer Progress Method 1 to 100
    private void dimmerProgress(int progress){
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id: " +nodeId2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        Log.e(TAG, "curtainAction: "+message );


        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
        Log.d(TAG, "sendFanSpeed: "+progress);
        requestModel.setMessage("{\""+ message +"\": {\"Intensity\": " + progress + "}}");
        Log.e(TAG, "dimmerProgress: "+progress );

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
                    Toast.makeText(DimmerActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(DimmerActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dimmerState(boolean powerState) {
        // Create a RequestModel with the required data
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE_KEY");
        Log.e(TAG, "curtainAction: "+message );

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        Log.d(TAG, "node id: " +nodeId2);

        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");

        requestModel.setMessage("{\""+ message +"\": {\"Power\": "+powerState+"}}");
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
                    Toast.makeText(DimmerActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(DimmerActivity.this, "Network error", Toast.LENGTH_SHORT).show();
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