package com.espressif.ui.activities.DeviceControls;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.espressif.NetworkApiManager;
import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.EspApplication;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.models.ResponseModel;
import com.espressif.wifi_provisioning.R;

public class AirContiningActivity extends AppCompatActivity {

    Switch switchAirConditioning;

    String nodeId;
    private ProgressBar progressBar;
    private Button incrementButton, decrementButton;
     TextView progressTextView;
    private int progress = 64;
    NetworkApiManager networkApiManager;


    TextView textView,textViewMood,textViewUnit;
    SeekBar seekbarAirCond;
    private String[] progressStates = {"Off", "Low", "Mid", "High"};

    Button buttonCool, buttonHeat ,buttonCon,buttonfah;

    Context context = this;
    private EspApplication espApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_contining);

        SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
        nodeId = preferences2.getString("nodeId", "");
        Log.d(TAG, "Fannodeee: "+nodeId);

        espApp = new EspApplication(getApplicationContext());
        networkApiManager = new NetworkApiManager(context.getApplicationContext(), espApp);

        //Set a AirCondition on the switch button
        switchAirConditioning = findViewById(R.id.switchButtonAirCon);
        switchAirConditioning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        //Seek Bar AirCondition

        seekbarAirCond = findViewById(R.id.seekBarAirCond);
        textView = findViewById(R.id.textViewFan);

       // String progress1;

        seekbarAirCond.setMax(progressStates.length - 1);
        seekbarAirCond.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progressStates[progress]);
                if(progress == 0){
                    String progress1 = "Off";
                    airConditioningProgress(progress1);

                } else if(progress == 1){
                    String progress1 = "Low";
                    airConditioningProgress(progress1);

                } else if(progress == 2){
                    String progress1 = "Mid";
                    airConditioningProgress(progress1);

                } else{
                    String progress1 = "High";
                    airConditioningProgress(progress1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for this example
            }
        });

        //Button Cool Heat Code

        buttonCool = findViewById(R.id.buttonCool);
        buttonHeat = findViewById(R.id.buttonHeat);
        textViewMood = findViewById(R.id.textMode);

        buttonCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewMood.setText("Cool");
                String mood = "Cool";
                sendMood(mood);
            }
        });

        buttonHeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewMood.setText("Heat");
                String mood = "Heat";
                sendMood(mood);
            }
        });

        textViewUnit = findViewById(R.id.textUnit);
        buttonfah = findViewById(R.id.buttonFehr);
        buttonCon = findViewById(R.id.buttonCCent);

        buttonCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewUnit.setText("Centigrade");
                String mood = "Centigrade";
                sendUnit(mood);
            }
        });

        buttonfah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewUnit.setText("Fahrenheit");
                String mood = "Fahrenheit";
                sendUnit(mood);
            }
        });
    }

    private void incrementProgress() {
        if (progress < 86) {
            progress++;
            progressBar.setProgress(progress);
            updateProgressText();
            sendTemperature(progress);
            Log.e(TAG, "incrementProgress: "+progress);
        }
    }

    private void decrementProgress() {
        if (progress > 64) {
            progress--;
            progressBar.setProgress(progress);
            updateProgressText();
            sendTemperature(progress);
            Log.e(TAG, "decrementProgress: "+progress);
        }
    }

    private void updateProgressText() {
        progressTextView.setText("" + progress + "");
    }

    //AirCondition Set Mood Method
    private void sendMood(String mood){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);

        String commandBody = "{\""+name+"\": {\"Mode\":\"" + mood + "\"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");

        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        //////////////////////////////////////////
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
//        Log.d(TAG, "sendFanSpeed: "+mood);
//        requestModel.setMessage("{\""+message+"\": {\"Mode\":\"" + mood + "\"}}");
//        Log.e(TAG, "sendMood: "+mood );
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
//                    Toast.makeText(AirContiningActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(AirContiningActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    //AirCondition Unit Method
    private void sendUnit(String unit){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);
        String commandBody = "{\""+name+"\": {\"Unit\":\"" + unit + "\"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");

        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        ////////////////////////////
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
//        Log.d(TAG, "sendFanSpeed: "+unit);
//        requestModel.setMessage("{\""+message+"\": {\"Unit\":\"" + unit + "\"}}");
//        Log.e(TAG, "sendUnit: "+unit );
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
//                    Toast.makeText(AirContiningActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(AirContiningActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    //Air Condition  Progress
    private void airConditioningProgress(String progress1){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);

        String commandBody = "{\""+ name +"\": {\"Speed\":\"" + progress1 + "\"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");

        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        ///////////////////////////////////////////////

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
//        Log.d(TAG, "sendFanSpeed: "+progress1);
//        requestModel.setMessage("{\""+ message +"\": {\"Speed\":\"" + progress1 + "\"}}");
//        Log.e(TAG, "AirCondProgress: "+progress1);
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
//                    Toast.makeText(AirContiningActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(AirContiningActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    //AirCondition Temp Speed Method
    private void sendTemperature(int fanSpeed){

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);

        String commandBody = "{\""+name+"\": {\"Set\": " + fanSpeed + "}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        ////////////////////////////
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
//        Log.d(TAG, "sendFanSpeed: "+fanSpeed);
//        requestModel.setMessage("{\""+message+"\": {\"Set\": " + fanSpeed + "}}");
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
//                    Toast.makeText(AirContiningActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(AirContiningActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void sendSwitchState(boolean powerState) {
        // Create a RequestModel with the required data

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        Log.e(TAG, "Name : "+name);

        String commandBody = "{\""+name+"\": {\"Power\": "+powerState+"}}";

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
        String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

        networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

        //////////////////////////////
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
//
//
//        requestModel.setMessage("{\""+message+"\": {\"Power\": "+powerState+"}}");
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
//                    Toast.makeText(AirContiningActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(AirContiningActivity.this, "Network error", Toast.LENGTH_SHORT).show();
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

    public static class RGBLightActivity extends AppCompatActivity {

        Switch rgbLightSwitch;
        String nodeId;
        NetworkApiManager networkApiManager;
        private EspApplication espApp;
        Context context = this;
        SeekBar seekBar1,seekBar2,seekBar3,seekBar4,seekBar5;
        TextView textView1,textView2,textView3,textView4,textView5;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rgblight);
            espApp = new EspApplication(getApplicationContext());
            networkApiManager = new NetworkApiManager(context.getApplicationContext(), espApp);
            SharedPreferences preferences2 = getSharedPreferences("MyPrefse", MODE_PRIVATE);
            nodeId = preferences2.getString("nodeId", "");
            Log.d(TAG, "Fannodeee: " + nodeId);

            rgbLightSwitch = findViewById(R.id.switchButtonFan);
            seekBar1 = findViewById(R.id.seekBarDimmer);
            seekBar2 = findViewById(R.id.seekBarHue);
            seekBar3 = findViewById(R.id.seekBarSaturation);
            seekBar4 = findViewById(R.id.seekBarCCT);
            seekBar5 = findViewById(R.id.seekBarWhiteBrightness);

            textView1 = findViewById(R.id.tv_brightness);
            textView2= findViewById(R.id.tv_hue);
            textView3 = findViewById(R.id.tv_saturation);
            textView4 = findViewById(R.id.tv_CCT);
            textView5 = findViewById(R.id.tv_Whitebrightness);

            //Dimmer ON/OFF Code
            rgbLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Handle switch state change
                    Log.d(TAG, "onCheckedChanged: "+isChecked);
                    rgbLightState(isChecked);
                }
            });


            //Seek Bar Brightness
            seekBar1.setMax(100);
            seekBar1.setProgress(0);
            textView1.setText("0");

            seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Display current progress value
                    textView1.setText(String.valueOf(progress + 1));

                    // Send value to server
                    rgbBrightness(progress + 1);
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

            //Seek Bar Hue
            seekBar2.setMax(360);
            seekBar2.setProgress(0);
            textView2.setText("0");

            seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Display current progress value
                    textView2.setText(String.valueOf(progress + 1));
                    int hue = progress; // Hue ranges from 0 to 360
                    int color = Color.HSVToColor(new float[]{hue, 1, 1}); // Convert to RGB color
                    // Set the thumb color
                    seekBar.getThumb().mutate().setTint(color);
                    // Set the progress bar background color
                    seekBar.getProgressDrawable().mutate().setTint(color);
                    // Send value to server
                    rgbHue(progress + 1);
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


            //Seek Bar Saturation
            seekBar3.setMax(100);
            seekBar3.setProgress(0);
            textView3.setText("0");

            seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Display current progress value
                    textView3.setText(String.valueOf(progress + 1));

                    // Send value to server
                    rgbSaturation(progress + 1);
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

            //Seek Bar CCT
            seekBar4.setMax(6500);
            seekBar4.setProgress(2700);
            textView4.setText("0");

            seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Display current progress value
                    textView4.setText(String.valueOf(progress + 1));

                    // Send value to server
                    rgbCCT(progress + 1);
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

            //Seek White Brightness
            seekBar5.setMax(100);
            seekBar5.setProgress(0);
            textView5.setText("1");

            seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Display current progress value
                    textView5.setText(String.valueOf(progress + 1));

                    // Send value to server
                    rgbWhiteBrightness(progress + 1);
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

        //RGB ON/OFF
        private void rgbLightState(boolean powerState) {
            // Create a RequestModel with the required data
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("Name", "");
            Log.e(TAG, "Name : "+name);
            String commandBody = "{\""+ name +"\": {\"Power\": "+powerState+"}}";

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

            SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");

            String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

            networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);


    //        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    //
    //        Intent intent = getIntent();
    //        String message = intent.getStringExtra("MESSAGE_KEY");
    //        Log.e(TAG, "curtainAction: "+message );
    //        SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
    //        String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
    //
    //        RequestModel requestModel = new RequestModel();
    //        requestModel.setSenderLoginToken(0);
    //        requestModel.setTopic("node/"+ nodeId2 +"/params/remote");
    //
    //        requestModel.setMessage("{\""+ message +"\": {\"Power\": "+powerState+"}}");
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
    //                    Toast.makeText(RGBLightActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<ResponseModel> call, Throwable t) {
    //                // Handle failure
    //                Toast.makeText(RGBLightActivity.this, "Network error", Toast.LENGTH_SHORT).show();
    //            }
    //        });
        }

        private void rgbBrightness(int progress){

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("Name", "");
            Log.e(TAG, "Name : "+name);

            String commandBody = "{\""+ name +"\": {\"Brightness\": " + progress + "}}";

            SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");

            String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

            networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

           ////////////////////////////

    //        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    //
    //        Intent intent = getIntent();
    //        String message = intent.getStringExtra("MESSAGE_KEY");
    //        Log.e(TAG, "curtainAction: "+message );
    //
    //
    //        RequestModel requestModel = new RequestModel();
    //        requestModel.setSenderLoginToken(0);
    //        requestModel.setTopic("node/"+ nodeId +"/params/remote");
    //        Log.d(TAG, "sendBrightness: "+progress);
    //        requestModel.setMessage("{\""+ message +"\": {\"Brightness\": " + progress + "}}");
    //        Log.e(TAG, "sendBrightness: "+progress );
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
    //                    Toast.makeText(RGBLightActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
    //                }
    //            }
    //            @Override
    //            public void onFailure(Call<ResponseModel> call, Throwable t) {
    //                // Handle failure
    //                Toast.makeText(RGBLightActivity.this, "Network error", Toast.LENGTH_SHORT).show();
    //            }
    //        });
        }

        private void rgbHue(int progress){

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("Name", "");
            Log.e(TAG, "Name : "+name);
            String commandBody = "{\""+ name +"\": {\"Hue\": " + progress + "}}";

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
            String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

            networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);
            //////////////////////////////


    //        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    //
    //        Intent intent = getIntent();
    //        String message = intent.getStringExtra("MESSAGE_KEY");
    //        Log.e(TAG, "curtainAction: "+message );
    //
    //        RequestModel requestModel = new RequestModel();
    //        requestModel.setSenderLoginToken(0);
    //        requestModel.setTopic("node/"+ nodeId +"/params/remote");
    //        Log.d(TAG, "sendFanSpeed: "+progress);
    //        requestModel.setMessage("{\""+ message +"\": {\"Hue\": " + progress + "}}");
    //        Log.e(TAG, "dimmerProgress: "+progress );
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
    //                    Toast.makeText(RGBLightActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
    //                }
    //            }
    //            @Override
    //            public void onFailure(Call<ResponseModel> call, Throwable t) {
    //                // Handle failure
    //                Toast.makeText(RGBLightActivity.this, "Network error", Toast.LENGTH_SHORT).show();
    //            }
    //        });
        }

        private void rgbSaturation(int progress){

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("Name", "");
            Log.e(TAG, "Name : "+name);
            String commandBody = "{\""+ name +"\": {\"Saturation\": " + progress + "}}";

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
            String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

            networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

            /////////////////////////////
    //        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    //
    //        Intent intent = getIntent();
    //        String message = intent.getStringExtra("MESSAGE_KEY");
    //        Log.e(TAG, "curtainAction: "+message );
    //
    //        RequestModel requestModel = new RequestModel();
    //        requestModel.setSenderLoginToken(0);
    //        requestModel.setTopic("node/"+ nodeId +"/params/remote");
    //        Log.d(TAG, "sendFanSpeed: "+progress);
    //        requestModel.setMessage("{\""+ message +"\": {\"Saturation\": " + progress + "}}");
    //        Log.e(TAG, "dimmerProgress: "+progress );
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
    //                    Toast.makeText(RGBLightActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
    //                }
    //            }
    //            @Override
    //            public void onFailure(Call<ResponseModel> call, Throwable t) {
    //                // Handle failure
    //                Toast.makeText(RGBLightActivity.this, "Network error", Toast.LENGTH_SHORT).show();
    //            }
    //        });
        }

        private void rgbCCT(int progress){
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("Name", "");
            Log.e(TAG, "Name : "+name);

            String commandBody = "{\""+ name +"\": {\"CCT\": " + progress + "}}";

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
            String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

            networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

            ///////////////////////////////////////
    //        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    //
    //        Intent intent = getIntent();
    //        String message = intent.getStringExtra("MESSAGE_KEY");
    //        Log.e(TAG, "curtainAction: "+message );
    //
    //
    //        RequestModel requestModel = new RequestModel();
    //        requestModel.setSenderLoginToken(0);
    //        requestModel.setTopic("node/"+ nodeId +"/params/remote");
    //        Log.d(TAG, "sendFanSpeed: "+progress);
    //        requestModel.setMessage("{\""+ message +"\": {\"CCT\": " + progress + "}}");
    //        Log.e(TAG, "dimmerProgress: "+progress );
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
    //                    Toast.makeText(RGBLightActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
    //                }
    //            }
    //            @Override
    //            public void onFailure(Call<ResponseModel> call, Throwable t) {
    //                // Handle failure
    //                Toast.makeText(RGBLightActivity.this, "Network error", Toast.LENGTH_SHORT).show();
    //            }
    //        });
        }

        private void rgbWhiteBrightness(int progress){
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("Name", "");
            Log.e(TAG, "Name : "+name);

            String commandBody = "{\""+ name +"\": {\"White Brightness\": " + progress + "}}";

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            SharedPreferences preferences9 = getSharedPreferences("my_shared_prefe", MODE_PRIVATE);
            String nodeId2 = preferences9.getString("KEY_USERNAMEs", "");
            String remoteCommandTopic = "node/"+ nodeId2 +"/params/remote";

            networkApiManager.updateParamValue(nodeId2, commandBody, apiService, remoteCommandTopic);

            ////////////////////////////////////
    //        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    //
    //        Intent intent = getIntent();
    //        String message = intent.getStringExtra("MESSAGE_KEY");
    //        Log.e(TAG, "curtainAction: "+message );
    //
    //
    //        RequestModel requestModel = new RequestModel();
    //        requestModel.setSenderLoginToken(0);
    //        requestModel.setTopic("node/"+ nodeId +"/params/remote");
    //        Log.d(TAG, "sendFanSpeed: "+progress);
    //        requestModel.setMessage("{\""+ message +"\": {\"White Brightness\": " + progress + "}}");
    //        Log.e(TAG, "dimmerProgress: "+progress );
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
    //                    Toast.makeText(RGBLightActivity.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
    //                }
    //            }
    //            @Override
    //            public void onFailure(Call<ResponseModel> call, Throwable t) {
    //                // Handle failure
    //                Toast.makeText(RGBLightActivity.this, "Network error", Toast.LENGTH_SHORT).show();
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
}