package com.espressif.ui.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


import com.espressif.AppConstants;
import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.espressif.wifi_provisioning.R;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EspMainActivityHome extends AppCompatActivity {

    private EspApplication espApp;


    CardView cardView;
    Switch switchButton;
    String power;
    private ApiService apiService;

    //Intent intent = getIntent();

    // Retrieve the string using the key
   // String nodeId = intent.getStringExtra("keyStringData");

    String nodeId = "AAAAAAAAAAAAAAAAAAAAAA";

    String mac =  "";

    // Display the retrieved string in the TextView
    // textView.setText(storedString);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esp_main_home);
        cardView = findViewById(R.id.node_cardView);
        switchButton = findViewById(R.id.switch_btn);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        mac = preferences.getString("mac", "");

        node();



        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://enscloud.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiService
        apiService = retrofit.create(ApiService.class);

        // Find your switch in the layout
        Switch switchButton = findViewById(R.id.switch_btn);

        // Set a listener on the switch button
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle switch state change
                sendSwitchState(isChecked);
            }
        });
    }

    private void sendSwitchState(boolean powerState) {
        // Create a RequestModel with the required data
        RequestModel requestModel = new RequestModel();
        requestModel.setSenderLoginToken(0);
        requestModel.setTopic("node/"+ mac +"/params/remote");
        requestModel.setMessage("{\"Switch\": {\"Power\": " + powerState + "}}");
      //  requestModel.setQosLevel(0);

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
                    Toast.makeText(EspMainActivityHome.this, "Failed to make the API call", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Handle failure
                Toast.makeText(EspMainActivityHome.this, "Network error", Toast.LENGTH_SHORT).show();
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

    private void node() {
        if(nodeId != null){
            cardView.setVisibility(View.VISIBLE);
        }
    }




//    @Override
//    protected void onResume() {
//        super.onResume();
//       // EventBus.getDefault().register(this);
//     //   getNodes();
//    }

//    private void getNodes() {
//        espApp.refreshData();
//    }


}