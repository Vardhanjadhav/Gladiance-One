package com.espressif.ui.activities.API;

import com.espressif.AppConstants;
import com.espressif.cloudapi.DeviceOperationRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    // Get Nodes
//    @GET
//    Call<ResponseBody> getNodes(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_START_ID) String startId);

    // Get Node Details
    @GET
    Call<ResponseBody> getNode(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_NODE_ID) String nodeId);

    @POST
    Call<ResponseBody> login(@Url String url, @Body JsonObject body);

    // Get Nodes
    @GET
    Call<ResponseBody> getNodes(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_START_ID) String startId);

    @GET
    Call<ResponseBody> getNodeStatus(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                     @Query(AppConstants.KEY_NODE_ID) String nodeId);


    // Add Node
    @PUT
    Call<ResponseBody> addNode(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body DeviceOperationRequest rawJsonString);

    // Remove Node
    @PUT
    Call<ResponseBody> removeNode(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body DeviceOperationRequest rawJsonString);

    // Get param values
    @GET
    Call<ResponseBody> getParamValue(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_NODE_ID) String nodeId);


    // Update param value
    @PUT
    Call<ResponseBody> updateParamValue(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Query(AppConstants.KEY_NODE_ID) String nodeId, @Body JsonObject body);


    // Update schedules / scenes
    @PUT
    Call<ResponseBody> updateParamsForMultiNode(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body JsonArray body);

    // Claiming initiate
    @POST
    Call<ResponseBody> initiateClaiming(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body JsonObject body);

    // Claiming verify
    @POST
    Call<ResponseBody> verifyClaiming(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body JsonObject body);

    // Get Add Node request status
    @GET
    Call<ResponseBody> getAddNodeRequestStatus(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                               @Query(AppConstants.KEY_REQ_ID) String requestId, @Query(AppConstants.KEY_USER_REQUEST) boolean userReq);

    // Create group
    @POST
    Call<ResponseBody> createGroup(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                   @Body JsonObject body);
    // Update sharing request
    @PUT
    Call<ResponseBody> updateSharingRequest(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                            @Body JsonObject body);

    // Remove sharing request
    @DELETE
    Call<ResponseBody> removeSharingRequest(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                            @Query(AppConstants.KEY_REQ_ID) String requestId);

    @GET
    Call<ResponseBody> getSharingRequests(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                          @Query(AppConstants.KEY_PRIMARY_USER) boolean isPrimaryUser,
                                          @Query(AppConstants.KEY_START_REQ_ID) String startRequestId,
                                          @Query(AppConstants.KEY_START_USER_NAME) String startUserName);

    // Share node with the user
    @PUT
    Call<ResponseBody> shareNodeWithUser(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token, @Body JsonObject body);

    // Get node sharing information
    @GET
    Call<ResponseBody> getNodeSharing(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                      @Query(AppConstants.KEY_NODE_ID) String nodeId);

    // Remove the sharing of node
    @DELETE
    Call<ResponseBody> removeSharing(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                     @Query(AppConstants.KEY_NODES) String nodes, @Query(AppConstants.KEY_USER_NAME) String userName);

    @POST
    Call<ResponseBody> registerDeviceToken(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                           @Body JsonObject body);

    @DELETE
    Call<ResponseBody> unregisterDeviceToken(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                             @Query(AppConstants.KEY_MOBILE_DEVICE_TOKEN) String deviceToken);

    // Get time series data
    @GET
    Call<ResponseBody> getTimeSeriesData(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                         @Query(AppConstants.KEY_NODE_ID) String nodeId,
                                         @Query(AppConstants.KEY_PARAM_NAME) String paramName,
                                         @Query(AppConstants.KEY_TYPE) String dataType,
                                         @Query(AppConstants.KEY_AGGREGATE) String aggregate,
                                         @Query(AppConstants.KEY_AGGREGATION_INTERVAL) String timeInterval,
                                         @Query(AppConstants.KEY_START_TIME) long startTime,
                                         @Query(AppConstants.KEY_END_TIME) long endTime,
                                         @Query(AppConstants.KEY_WEEK_START) String weekStart,
                                         @Query(AppConstants.KEY_TIMEZONE) String timezone,
                                         @Query(AppConstants.KEY_START_ID) String startId);

    // GET
    @GET
    Call<ResponseBody> getAutomations(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                      @Query(AppConstants.KEY_START_ID) String startId);

    // Update automation
    @PUT
    Call<ResponseBody> updateAutomation(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                        @Query(AppConstants.KEY_AUTOMATION_ID) String automationId,
                                        @Body JsonObject body);


    // DELETE
    @DELETE
    Call<ResponseBody> deleteAutomation(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                        @Query(AppConstants.KEY_AUTOMATION_ID) String automationId);

    // OTA Update
    @GET
    Call<ResponseBody> checkFwUpdate(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                     @Query(AppConstants.KEY_NODE_ID) String nodeId);

    @GET
    Call<ResponseBody> getFwUpdateStatus(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                         @Query(AppConstants.KEY_NODE_ID) String nodeId,
                                         @Query(AppConstants.KEY_OTA_JOB_ID) String otaJobId);
    // Update group
    @PUT
    Call<ResponseBody> updateGroup(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                   @Query(AppConstants.KEY_GROUP_ID) String groupId,
                                   @Body JsonObject body);


    @POST
    Call<ResponseBody> pushFwUpdate(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                    @Body JsonObject body);


    // Removes group
    @DELETE
    Call<ResponseBody> removeGroup(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                   @Query(AppConstants.KEY_GROUP_ID) String groupId);

    // Get user group
    @GET
    Call<ResponseBody> getUserGroups(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token,
                                     @Query(AppConstants.KEY_START_ID) String startId,
                                     @Query(AppConstants.KEY_GROUP_ID) String groupId,
                                     @Query(AppConstants.KEY_NODE_LIST) boolean shouldGetNodeList);

    @POST
    Call<ResponseBody> logout(@Url String url, @Header(AppConstants.HEADER_AUTHORIZATION) String token);


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
