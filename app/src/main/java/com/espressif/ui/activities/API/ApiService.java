package com.espressif.ui.activities.API;

import com.espressif.ui.models.ActiveSceneRes;
import com.espressif.ui.models.DeviceInfo;
import com.espressif.ui.models.LoginRequestModel;
import com.espressif.ui.models.LoginResponseModel;
import com.espressif.ui.models.LogoutRequestModel;
import com.espressif.ui.models.LogoutResponseModel;
import com.espressif.ui.models.NodeResponseModel;
import com.espressif.ui.models.ProjectSpaceGroupResModel;
import com.espressif.ui.models.ProjectSpaceLandingResModel;
import com.espressif.ui.models.ProjectSpaceResponseModel;
import com.espressif.ui.models.RequestModel;
import com.espressif.ui.models.ResetResponse;
import com.espressif.ui.models.ResponseModel;
import com.espressif.ui.models.ResponseModelNode;
import com.espressif.ui.models.SpaceSpaceGroupResModel;
import com.espressif.ui.models.arealandingmodel.ProjectAreaLandingResModel;
import com.espressif.ui.models.guestlandingpage.GuestLandingResModel;
import com.espressif.ui.models.lnstallerlandingpage.InstallerLandingResModel;
import com.espressif.ui.models.provisioninglabel.ProvisioningRequest;
import com.espressif.ui.models.provisioninglabel.ProvisioningResponse;
import com.espressif.ui.models.saveScene.SaveSceneRequest;
import com.espressif.ui.models.scene.SceneResModel;
import com.espressif.ui.models.scenelist.SceneListResModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("mqtt/publishmqttmessage")
   // Call<Void> postApiRequest(@Body ApiRequest apiRequest);
    Call<ResponseModel> sendSwitchState(@Body RequestModel requestModel);

    @GET("gladiancedev-gladiance-web-api/mqtt/nodeid/{macId}")
    Call<ResponseModelNode> getData(@Path("macId") String macId);

//localcontrolnodeconfig
    @GET("mqtt/nodeconfig/{NodeId}")
    Call<DeviceInfo> getAllData(@Path("NodeId") String NodeId);

    @GET("mqtt/localcontrolnodeconfig/{NodeId}")
    Call<Object> getAllLocalControlData(@Path("NodeId") String NodeId);

    @GET("mqtt/nodeid/{MacId}")
    Call<NodeResponseModel> getData2(@Path("MacId") String macId);

    @POST("mobileapp/loginuser")
    Call<LoginResponseModel> loginUser(@Body LoginRequestModel request);

    @GET("mobileapp/loginlandingpagedata/{LoginToken}/{LoginDeviceId}")
    Call<ProjectSpaceResponseModel> getLoginData(@Path("LoginToken") String loginToken, @Path("LoginDeviceId") String loginDeviceId);

    @GET("mobileapp/projectlandingpagedata/{GAAProjectRef}/{LoginToken}/{LoginDeviceId}")
    Call<ProjectSpaceGroupResModel> getProjectData(
            @Path("GAAProjectRef") String projectRef,
            @Path("LoginToken") String loginToken,
            @Path("LoginDeviceId") String loginDeviceId);

    @GET("mobileapp/spacegrouplandingpagedata/{GAAProjectSpaceGroupRef}/{LoginToken}/{LoginDeviceId}")
    Call<SpaceSpaceGroupResModel> getSpaceGroupData(@Path("GAAProjectSpaceGroupRef") String gaaProjectSpaceGroupRef,
                                                    @Path("LoginToken") String loginToken,
                                                    @Path("LoginDeviceId") String loginDeviceId);

    @GET("mobileapp/spacegrouplandingpagedata/{GAAProjectSpaceGroupRef}/{LoginToken}/{LoginDeviceId}")
    Call<ProjectSpaceLandingResModel> getSpaceNameData(
            @Path("GAAProjectSpaceGroupRef") String GAAProjectSpaceGroupRef,
            @Path("LoginToken") String LoginToken,
            @Path("LoginDeviceId") String LoginDeviceId
    );

    @GET("mobileapp/spacelandingpagedata/{GAAProjectSpaceRef}/{LoginToken}/{LoginDeviceId}")
    Call<ProjectAreaLandingResModel> getAreaLandingPageData(
            @Path("GAAProjectSpaceRef") String projectSpaceRef,
            @Path("LoginToken") String loginToken,
            @Path("LoginDeviceId") String loginDeviceId
    );

    @GET("mobileapp/arealandingpageinstallercontrols/{GAAProjectSpaceRef}/{GAAProjectSpaceTypeAreaRef}/{LoginToken}/{LoginDeviceId}")
    Call<InstallerLandingResModel> getDevices(
            @Path("GAAProjectSpaceRef") String projectSpaceRef,
            @Path("GAAProjectSpaceTypeAreaRef") Long gAAProjectSpaceTypeAreaRef,
            @Path("LoginToken") String loginToken,
            @Path("LoginDeviceId") String loginDeviceId
    );

    @GET("mobileapp/arealandingpageguestcontrols/{GAAProjectSpaceRef}/{GAAProjectSpaceTypeAreaRef}/{LoginToken}/{LoginDeviceId}")
    Call<GuestLandingResModel> getControlTypeName(
            @Path("GAAProjectSpaceRef") String projectSpaceRef,
            @Path("GAAProjectSpaceTypeAreaRef") Long gAAProjectSpaceTypeAreaRef,
            @Path("LoginToken") String loginToken,
            @Path("LoginDeviceId") String loginDeviceId
    );

    @POST("mobileapp/associatenodetoplanneddevice")
    Call<ProvisioningResponse> postAssociateNodeToPlannedDevice(@Body ProvisioningRequest request);


    //Reset device which is provision
    @POST("mqtt/factoryresetnode/{nodeId}/{loginToken}")
    Call<ResetResponse> factoryResetNode(
            @Path("nodeId") String nodeId,
            @Path("loginToken") String loginToken
           // @Body ResetResponse requestBody
    );

    @POST("mobileapp/logoutuser")
    Call<LogoutResponseModel> logoutUser(@Body LogoutRequestModel request);


    @GET("mobileapp/scenelist2/{gaaProjectSpaceTypeRef}/{loginToken}/{loginDeviceId}")
    Call<SceneListResModel> getSceneList(
            @Path("gaaProjectSpaceTypeRef") String gaaProjectSpaceTypeRef,
            @Path("loginToken") String loginToken,
            @Path("loginDeviceId") String loginDeviceId
    );

    @GET("mobileapp/scene/{gaaProjectSceneRef}/{loginToken}/{loginDeviceId}")
    Call<SceneResModel> getScene(
            @Path("gaaProjectSceneRef") Long gaaProjectSceneRef,
            @Path("loginToken") String loginToken,
            @Path("loginDeviceId") String loginDeviceId
    );

    @POST("savescene")
    Call<SceneResModel> saveScene(@Body SaveSceneRequest saveSceneRequest);

    @POST("mobileapp/activatescene/{gaaProjectSceneRef}/{gaaProjectSpaceRef}/{loginToken}/{loginDeviceId}")
    Call<ActiveSceneRes> activateScene(@Path("gaaProjectSceneRef") String gaaProjectSceneRef,
                                       @Path("gaaProjectSpaceRef") String gaaProjectSpaceRef,
                                       @Path("loginToken") String loginToken,
                                       @Path("loginDeviceId") String loginDeviceId);

}
