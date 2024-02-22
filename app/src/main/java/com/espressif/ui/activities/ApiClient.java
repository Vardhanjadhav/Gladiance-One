package com.espressif.ui.activities;

import com.espressif.ui.models.ResponseModelNode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://your-base-url.com/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }



    ////// Node ID Get Api ///////


    private static final String BASE_URL_NODE = "https://enscloud.in/";

    private static Retrofit retrofit_node = null;

    public static Retrofit getClient() {
        if (retrofit_node == null) {
            retrofit_node = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_node;
    }


    //


//    private static final String BASE_URL2 = "https://enscloud.in/";
//    private static Retrofit retrofit_node;
//
//    public static ApiService getApiServices() {
//        if (retrofit_node == null) {
//            retrofit_node = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(ApiService.class);
//    }
//
//    public static void makeApiCall(String nodeId, final ApiResponseCallback callback) {
//        ApiService apiService = getApiService();
//
//        Call<ResponseModelNode> call = apiService.getData(nodeId);
//        call.enqueue(new Callback<ResponseModelNode>() {
//            @Override
//            public void onResponse(Call<ResponseModelNode> call, Response<ResponseModelNode> response) {
//                if (response.isSuccessful()) {
//                    // Handle the successful response
//                    ResponseModelNode data = response.body();
//                    callback.onSuccess(data);
//                } else {
//                    // Handle the error response
//                    callback.onError(response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModelNode> call, Throwable t) {
//                // Handle the network error
//                callback.onError(t.getMessage());
//            }
//        });
//    }




}
