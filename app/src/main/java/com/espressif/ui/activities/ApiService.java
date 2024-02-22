package com.espressif.ui.activities;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.espressif.AppConstants;
import com.espressif.ui.models.DeviceInfo;
import com.espressif.ui.models.Devices;
import com.espressif.ui.models.NodeResponseModel;
import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.espressif.ui.models.ResponseModelNode;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("mqtt/publishmqttmessage")
   // Call<Void> postApiRequest(@Body ApiRequest apiRequest);
    Call<ResponseModel> sendSwitchState(@Body RequestModel requestModel);

    @GET("gladiancedev-gladiance-web-api/mqtt/nodeid/{macId}")
    Call<ResponseModelNode> getData(@Path("macId") String macId);


    @GET("mqtt/nodeconfig/{NodeId}")
    Call<DeviceInfo> getAllData(@Path("NodeId") String NodeId);

    @GET("mqtt/nodeid/{MacId}")
    Call<NodeResponseModel> getData2(@Path("MacId") String macId);

//    @POST("login")
//    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
