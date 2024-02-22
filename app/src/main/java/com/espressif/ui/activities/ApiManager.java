package com.espressif.ui.activities;

import android.text.TextUtils;
import android.util.Log;

import com.espressif.AppConstants;
import com.espressif.CloudException;
import com.espressif.JsonDataParser;
import com.espressif.db.EspDatabase;
import com.espressif.ui.models.UpdateEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ApiManager {

    private static final String TAG = ApiManager.class.getSimpleName();
    private ApiInterface apiInterface;
    private static String accessToken = "";

    private EspApplication espApp;
    private EspDatabase espDatabase;


    public void getNodeDetails(String nodeId, final ApiResponseListener listener) {

        Log.d(TAG, "Get Node Details for id : " + nodeId);

        apiInterface.getNode(AppConstants.URL_USER_NODES, accessToken, nodeId).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.d(TAG, "Get Node Details, Response code : " + response.code());

                try {
                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            String jsonResponse = response.body().string();
                            Log.e(TAG, "onResponse Success : " + jsonResponse);
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            JSONArray nodeJsonArray = jsonObject.optJSONArray(AppConstants.KEY_NODE_DETAILS);

                            if (nodeJsonArray != null) {

                                for (int nodeIndex = 0; nodeIndex < nodeJsonArray.length(); nodeIndex++) {

                                    JSONObject nodeJson = nodeJsonArray.optJSONObject(nodeIndex);

                                    if (nodeJson != null) {

                                        // Node ID
                                        String nodeId = nodeJson.optString(AppConstants.KEY_ID);
                                        Log.d(TAG, "Node id : " + nodeId);
                                        EspNode espNode;

                                        if (espApp.nodeMap.get(nodeId) != null) {
                                            espNode = espApp.nodeMap.get(nodeId);
                                        } else {
                                            espNode = new EspNode(nodeId);
                                        }

                                        // User role
                                        String role = nodeJson.optString(AppConstants.KEY_ROLE);
                                        espNode.setUserRole(role);

                                        // Node Config
                                        JSONObject configJson = nodeJson.optJSONObject(AppConstants.KEY_CONFIG);
                                        if (configJson != null) {

                                            espNode = JsonDataParser.setNodeConfig(espNode, configJson);
                                            espNode.setConfigData(configJson.toString());
                                            espApp.nodeMap.put(nodeId, espNode);
                                        }

                                        // Node Params
                                        JSONObject paramsJson = nodeJson.optJSONObject(AppConstants.KEY_PARAMS);
                                        if (paramsJson != null) {
                                            JsonDataParser.setAllParams(espApp, espNode, paramsJson);
                                            espNode.setParamData(paramsJson.toString());
                                            espDatabase.getNodeDao().insertOrUpdate(espNode);
                                        }

                                        // Node Status
                                        JSONObject statusJson = nodeJson.optJSONObject(AppConstants.KEY_STATUS);

                                        if (statusJson != null) {

                                            JSONObject connectivityObject = statusJson.optJSONObject(AppConstants.KEY_CONNECTIVITY);

                                            if (connectivityObject != null) {

                                                boolean nodeStatus = connectivityObject.optBoolean(AppConstants.KEY_CONNECTED);
                                                long timestamp = connectivityObject.optLong(AppConstants.KEY_TIMESTAMP);
                                                espNode.setTimeStampOfStatus(timestamp);

                                                if (espNode.isOnline() != nodeStatus) {
                                                    espNode.setOnline(nodeStatus);
                                                    EventBus.getDefault().post(new UpdateEvent(AppConstants.UpdateEventType.EVENT_DEVICE_STATUS_UPDATE));
                                                }
                                            } else {
                                                Log.e(TAG, "Connectivity object is null");
                                            }
                                        }
                                    }
                                }
                            }

                            listener.onSuccess(null);

                        } else {
                            Log.e(TAG, "Response received : null");
                            listener.onResponseFailure(new RuntimeException("Failed to get Node Details"));
                        }
                    } else {
                        String jsonErrResponse = response.errorBody().string();
                        processError(jsonErrResponse, listener, "Failed to get Node Details");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onResponseFailure(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onResponseFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                listener.onNetworkFailure(new Exception(t));
            }
        });
    }

    private void processError(String jsonErrResponse, ApiResponseListener listener, String errMsg) {

        Log.e(TAG, "Error Response : " + jsonErrResponse);
        try {
            if (jsonErrResponse.contains(AppConstants.KEY_FAILURE_RESPONSE)) {

                JSONObject jsonObject = new JSONObject(jsonErrResponse);
                String err = jsonObject.optString(AppConstants.KEY_DESCRIPTION);

                if (!TextUtils.isEmpty(err)) {
                    listener.onResponseFailure(new CloudException(err));
                } else {
                    listener.onResponseFailure(new RuntimeException(errMsg));
                }
            } else {
                listener.onResponseFailure(new RuntimeException(errMsg));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onResponseFailure(new RuntimeException(errMsg));
        }
    }
}
