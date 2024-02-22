package com.espressif.ui.activities;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.espressif.ui.models.NodeResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NodeRepository {

    private ApiService apiService;
    Context context;

//    private SharedPreferences sharedPreferences;
//    public NodeRepository() {
//        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//    }
//
//    public void getNode(String macId, final NodeCallback callback) {
//        Call<NodeResponseModel> call = apiService.getNode(macId);
//        call.enqueue(new Callback<NodeResponseModel>() {
//            @Override
//            public void onResponse(Call<NodeResponseModel> call, Response<NodeResponseModel> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callback.onSuccess(response.body());
//                    Log.d(TAG, "onResponse: "+response.body().getNodeId());
//                    Log.e(TAG, "NodeId: "+response.body().getNodeId() );
//                    String nodeId = response.body().getNodeId();
//                    savenodeid(nodeId);
//                } else {
//                    callback.onError("Failed to fetch node.");
//                }
//            }
//
//            private void savenodeid(String nodeId) {
//                sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("key", "value");
//                editor.putInt("integer_key", 100);
//                editor.putBoolean("boolean_key", true);
//                editor.apply();
//            }
//
//
//            @Override
//            public void onFailure(Call<NodeResponseModel> call, Throwable t) {
//                callback.onError(t.getMessage());
//            }
//        });
//    }
//
//
//    public interface NodeCallback {
//        void onSuccess(NodeResponseModel nodeResponse);
//        void onError(String error);
//    }
}
