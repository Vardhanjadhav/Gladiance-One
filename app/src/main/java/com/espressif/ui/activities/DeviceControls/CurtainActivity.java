package com.espressif.ui.activities.DeviceControls;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.espressif.NetworkApiManager;
import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.EspApplication;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.models.ResponseModel;
import com.espressif.wifi_provisioning.R;

public class CurtainActivity extends AppCompatActivity {

    CardView curtainOpen,curtainClose,curtainStop;
    String nodeId;
    String open;
    NetworkApiManager networkApiManager;

    SeekBar seekBar;
    TextView textView;
    Button setTimeBtn;

    Context context = this;
    private EspApplication espApp;

    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtain);

        curtainOpen = findViewById(R.id.curtainOpen);
        curtainClose = findViewById(R.id.curtainClose);
        curtainStop = findViewById(R.id.curtainStop);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        setTimeBtn = findViewById(R.id.setTimeBtn);

        espApp = new EspApplication(getApplicationContext());
        networkApiManager = new NetworkApiManager(context.getApplicationContext(), espApp);

        //SharedPreferences
        SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
        nodeId = preferences2.getString("nodeId", "");
        Log.d(TAG, "Fannodeee: "+nodeId);


        curtainOpen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                open = "Open";
                curtainAction(open);
            }
        });

        curtainClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                open = "Close";
                curtainAction(open);
            }
        });

        curtainStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                open = "Stop";
                curtainAction(open);
            }
        });

        /**
         * Seek bar code
         */
        // Setting max value to 299 because the range is from 1 to 300
        seekBar.setMax(300);

        // Setting initial progress
        seekBar.setProgress(1);
        textView.setText("1");

        // SeekBar change listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Display current progress value
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Button click listener
        setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valueToSend = seekBar.getProgress() + 1; // Increment progress by 1 to match the range 1-300
                // Send value to server (replace this with your server communication logic)
                 curtainCount(valueToSend);
                Log.e(TAG, "onClick: " + seekBar.getProgress() );
            }
        });
    }

    private void curtainAction(String open){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);

        String commandBody = "{\""+name+"\": {\"Action\": \""+ open +"\"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        ////////////////////////////////////////////
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        //
//        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
//        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
//        Log.d(TAG, "node id: " +nodeId2);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra("MESSAGE_KEY");
//
//        Log.e(TAG, "curtainAction: "+message );
//
//        RequestModel requestModel = new RequestModel();
//        requestModel.setSenderLoginToken(0);
//        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
//        Log.d(TAG, "sendFanSpeed: "+open);
//        requestModel.setMessage("{\""+message+"\": {\"Action\": \""+ open +"\"}}");
//        //requestModel.setQosLevel(0);
//        Log.d(TAG, "sendFanSpeed: "+requestModel.getMessage());
//        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.isSuccessful()) {
//                    ResponseModel responseModel = response.body();
//                    handleApiResponse(responseModel);
//                } else {
//                    // Handle unsuccessful response
//                    Toast.makeText(CurtainActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(CurtainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     *
     * seekbar
     */

    private void curtainCount(int count){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);

        String commandBody = "{\""+name+"\": {\"Transition\": " + count + "}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        //////////////////////////////////
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra("MESSAGE_KEY");
//        Log.e(TAG, "curtainAction: "+message );
//
//        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
//        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
//        Log.d(TAG, "node id: " +nodeId2);
//
//        RequestModel requestModel = new RequestModel();
//        requestModel.setSenderLoginToken(0);
//        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
//        Log.d(TAG, "sendFanSpeed: "+count);
//        requestModel.setMessage("{\""+message+"\": {\"Transition\": " + count + "}}");
//
//
//        //requestModel.setQosLevel(0);
//        Log.d(TAG, "sendFanSpeed: "+requestModel.getMessage());
//        Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                if (response.isSuccessful()) {
//                    ResponseModel responseModel = response.body();
//                    handleApiResponse(responseModel);
//                } else {
//                    // Handle unsuccessful response
//                    Toast.makeText(CurtainActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(CurtainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
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