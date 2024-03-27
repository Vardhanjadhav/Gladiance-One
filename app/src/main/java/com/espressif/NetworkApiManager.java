package com.espressif;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.espressif.cloudapi.ApiManager;
import com.espressif.cloudapi.ApiResponseListener;
import com.espressif.local_control.LocalControlApiManager;
import com.espressif.ui.activities.ApiService;
import com.espressif.ui.activities.EspApplication;
import com.espressif.ui.activities.EspMainActivity;
import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResponseModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkApiManager {
    private final String TAG = NetworkApiManager.class.getSimpleName();

    private Context context;
    private EspApplication espApp;
    private ApiManager apiManager;
    private LocalControlApiManager localControlApiManager;

    public NetworkApiManager(Context context, EspApplication eApp) {
        this.context = context;
        espApp = eApp; // new EspApplication(context.getApplicationContext());
        apiManager = ApiManager.getInstance(context, espApp);
        localControlApiManager = new LocalControlApiManager(context, espApp);
    }

    /**
     * This method is used to update param values of a device.
     *
     * @param nodeId   Node id.
//     * @param body     Json data to be sent in request. It contains new value of a param.
//     * @param listener Listener to send success or failure.
     */
//    public void updateParamValue(final String nodeId, final JsonObject body, final ApiResponseListener listener) {
    public void updateParamValue(final String nodeId, final String commandString, // final ApiResponseListener listener,
                                 final ApiService apiService,
                                 final String remoteCommandTopic) {

        try {
            if (espApp.localDeviceMap.containsKey(nodeId)) {

//            localControlApiManager.updateParamValue(nodeId, body, new ApiResponseListener() {
                Log.d(TAG, "Local Control devices:");
                localControlApiManager.updateParamValue(nodeId, commandString, new ApiResponseListener() {

                    @Override
                    public void onSuccess(Bundle data) {
//                    listener.onSuccess(data);
                    }

                    @Override
                    public void onResponseFailure(Exception exception) {
                        Log.e(TAG, "Error : " + exception.getMessage());
                        Log.e(TAG, "Removing Node id : " + nodeId);
                        espApp.localDeviceMap.remove(nodeId);
//                    updateParamValue(nodeId, body, listener);
                        updateParamValue(nodeId, commandString, apiService, remoteCommandTopic);
                    }

                    @Override
                    public void onNetworkFailure(Exception exception) {
                        Log.e(TAG, "Error : " + exception.getMessage());
                        Log.e(TAG, "Removing Node id : " + nodeId);
                        espApp.localDeviceMap.remove(nodeId);
//                    updateParamValue(nodeId, body, listener);
                        updateParamValue(nodeId, commandString, apiService, remoteCommandTopic);
                    }
                });

            } else {
//            apiManager.updateParamValue(nodeId, body, listener);
                // Create a RequestModel with the required data
                Log.d(TAG, "Remotely:");
                RequestModel requestModel = new RequestModel();
                requestModel.setSenderLoginToken(0);
                requestModel.setTopic(remoteCommandTopic);

                //Change
//        nodeId = "WI84xt861kS39p2b5sXeGQ";
                //       requestModel.setTopic("node/"+ nodeId +"/params/remote");

                requestModel.setMessage(commandString);


                // requestModel.setMessage("{\"Fan 1\": {\"Power\": " + powerState + "}}");
//            Log.e(TAG, "sendSwitchState: "+name );

                // requestModel.setQosLevel(0);
                // Make the API call
                Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful()) {
                            ResponseModel responseModel = response.body();
//                        handleApiResponse(responseModel);

                        } else {
                            // Handle unsuccessful response
//                        Toast.makeText(EspMainActivity.this, "Failed to make the API call2", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        // Handle failure
//                    Toast.makeText(EspMainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        catch (Exception e){
            Log.d(TAG, "Exception: "+e);
            Log.d(TAG, "Remotely:");
            RequestModel requestModel = new RequestModel();
            requestModel.setSenderLoginToken(0);
            requestModel.setTopic(remoteCommandTopic);

            //Change
//        nodeId = "WI84xt861kS39p2b5sXeGQ";
            //       requestModel.setTopic("node/"+ nodeId +"/params/remote");

            requestModel.setMessage(commandString);


            // requestModel.setMessage("{\"Fan 1\": {\"Power\": " + powerState + "}}");
//            Log.e(TAG, "sendSwitchState: "+name );

            // requestModel.setQosLevel(0);
            // Make the API call
            Call<ResponseModel> call = apiService.sendSwitchState(requestModel);
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if (response.isSuccessful()) {
                        ResponseModel responseModel = response.body();
//                        handleApiResponse(responseModel);

                    } else {
                        // Handle unsuccessful response
//                        Toast.makeText(EspMainActivity.this, "Failed to make the API call2", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    // Handle failure
//                    Toast.makeText(EspMainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        ///
    }

    /**
     * This method is used to get param values for a given node id.
     *
     * @param nodeId   Node id.
     * @param listener Listener to send success or failure.
     */
    public void getParamsValues(final String nodeId, final ApiResponseListener listener) {

        if (espApp.localDeviceMap.containsKey(nodeId)) {

            localControlApiManager.getParamsValues(nodeId, new ApiResponseListener() {

                @Override
                public void onSuccess(Bundle data) {
                    listener.onSuccess(data);
                }

                @Override
                public void onResponseFailure(Exception exception) {
                    Log.e(TAG, "Error : " + exception.getMessage());
                    Log.e(TAG, "Removing Node id : " + nodeId);
                    espApp.localDeviceMap.remove(nodeId);
                    getParamsValues(nodeId, listener);
                }

                @Override
                public void onNetworkFailure(Exception exception) {
                    Log.e(TAG, "Error : " + exception.getMessage());
                    Log.e(TAG, "Removing Node id : " + nodeId);
                    espApp.localDeviceMap.remove(nodeId);
                    getParamsValues(nodeId, listener);
                }
            });

        } else {
            apiManager.getParamsValues(nodeId, listener);
        }
    }

    /**
     * This method is used to get node details for a given node id.
     *
     * @param nodeId   Node id.
     * @param listener Listener to send success or failure.
     */
    public void getNodeDetails(final String nodeId, final ApiResponseListener listener) {

        if (espApp.localDeviceMap.containsKey(nodeId)) {

            localControlApiManager.getNodeDetails(nodeId, new ApiResponseListener() {

                @Override
                public void onSuccess(Bundle data) {
                    listener.onSuccess(data);
                }

                @Override
                public void onResponseFailure(Exception exception) {
                    Log.e(TAG, "Error : " + exception.getMessage());
                    Log.e(TAG, "Removing Node id : " + nodeId);
                    espApp.localDeviceMap.remove(nodeId);
                    getParamsValues(nodeId, listener);
                }

                @Override
                public void onNetworkFailure(Exception exception) {
                    Log.e(TAG, "Error : " + exception.getMessage());
                    Log.e(TAG, "Removing Node id : " + nodeId);
                    espApp.localDeviceMap.remove(nodeId);
                    getParamsValues(nodeId, listener);
                }
            });

        } else {
            apiManager.getNodeDetails(nodeId, listener);
        }
    }
}
