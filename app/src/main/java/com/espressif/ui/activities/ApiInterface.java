package com.espressif.ui.activities;

import com.espressif.AppConstants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    // Get Nodes
//    @GET
//    Call<ResponseBody> getNodes(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_START_ID) String startId);

    // Get Node Details
    @GET
    Call<ResponseBody> getNode(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_NODE_ID) String nodeId);

//    // Get Node Status
//    @GET
//    Call<ResponseBody> getNodeStatus(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
//                                     @Query(AppConstants.KEY_NODE_ID) String nodeId);
//
//    // Add Node
//    @PUT
//    Call<ResponseBody> addNode(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body DeviceOperationRequest rawJsonString);
//
//    // Get Add Node request status
//    @GET
//    Call<ResponseBody> getAddNodeRequestStatus(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
//                                               @Query(AppConstants.KEY_REQ_ID) String requestId, @Query(AppConstants.KEY_USER_REQUEST) boolean userReq);
}
