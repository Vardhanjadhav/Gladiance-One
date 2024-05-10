package com.espressif.ui.fragment.MyProfile;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.AreaSpinnerAdapter;
import com.espressif.ui.adapters.SceneCheckAdapter;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.arealandingmodel.Area;
import com.espressif.ui.models.arealandingmodel.ProjectAreaLandingResModel;
import com.espressif.ui.models.lnstallerlandingpage.Controls;
import com.espressif.ui.models.saveScene.SaveSceneRequest;
import com.espressif.ui.models.saveScene.SceneConfig;
import com.espressif.ui.models.scene.Configuration;
import com.espressif.ui.models.scene.ObjectTag;
import com.espressif.ui.models.scene.SceneResModel;


import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditSceneFragment extends Fragment implements AreaSpinnerAdapter.OnAreaSelectedListener  {

    private static final String ARG_SELECTED_AREA_REF = "selectedAreaRef";

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    private ArrayList<Configuration> ConfigArrayList;

    private ArrayList<Controls> ConArrayList;

    private ArrayList<Area> arrayList2;
    private static final String PREF_SELECTED_AREA_REF = "selectedAreaRef";

    EditText editTextProjectName, editTextSpaceName, editTextSceneName;

    RecyclerView recyclerView;

    Spinner spinner;

    Button buttonSave;


    public EditSceneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_scene, container, false);

        ConfigArrayList = new ArrayList<>();
        ConArrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        spinner = view.findViewById(R.id.sceneAreaSpinner);

        editTextProjectName = view.findViewById(R.id.SMProjectName);
        editTextSpaceName = view.findViewById(R.id.SMSpaceName);
        editTextSceneName = view.findViewById(R.id.SMSceneName);
        recyclerView = view.findViewById(R.id.recycleViewDeviceName);

        buttonSave = view.findViewById(R.id.buttonSave);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "SceneList LoginDeviceId: " + GUID);
        String loginDeviceId = GUID.trim();

        //Login Token
        SharedPreferences sharedPreferences2 = requireContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedLoginToken = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "SceneList Login Token: " + savedLoginToken);
        String loginToken = savedLoginToken.trim();

        SharedPreferences sharedPreferences5 = requireContext().getSharedPreferences("MyPrefsPSR", MODE_PRIVATE);
        String saveProjectSpaceRef = sharedPreferences5.getString("Project_Space_Ref", "");
        Log.e(TAG, "Project Space Ref: "+saveProjectSpaceRef );
        String projectSpaceRef = saveProjectSpaceRef.trim();

////        SharedPreferences sharedPreferencesFanSpeed = requireContext().getSharedPreferences("MyPreferencesFS", Context.MODE_PRIVATE);
////        int FanSpeed = sharedPreferencesFanSpeed.getInt("FanSpeed", 0);
////        Log.e(TAG, "EditSceneFragment FanSpeed : "+FanSpeed);


        long gAAProjectSpaceTypeAreaRef = getSelectedAreaRefFromPreferences();



        Bundle bundle = getArguments();
        if (bundle != null) {
            String sceneRefString = bundle.getString("SCENE_REF");
            if (sceneRefString != null) {
                Long sceneRef = Long.parseLong(sceneRefString);
                Log.e(TAG, "SceneRef: " + sceneRef);
                getScene(sceneRef, loginToken, loginDeviceId);
            } else {
                Log.e(TAG, "SceneRef is null");
                // Handle null sceneRefString
            }
        } else {
            Log.e(TAG, "Bundle is null");
            // Handle null bundle
        }


        //fetchInstallerControls(projectSpaceRef,gAAProjectSpaceTypeAreaRef,loginToken,loginDeviceId);


        fetchAreas(projectSpaceRef,loginToken,loginDeviceId);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferencesPowerState = requireContext().getSharedPreferences("MyPreferencesPS", Context.MODE_PRIVATE);
        boolean PowerState = sharedPreferencesPowerState.getBoolean("PowerState", false);
        Log.e(TAG, "EditSceneFragment Power onResume : "+PowerState);

        SharedPreferences sharedPreferencesFanSpeed = requireContext().getSharedPreferences("MyPreferencesFS", Context.MODE_PRIVATE);
        int FanSpeed = sharedPreferencesFanSpeed.getInt("FanSpeed", 0);
        Log.e(TAG, "EditSceneFragment FanSpeed onResume : "+FanSpeed);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

                SaveSceneRequest saveScene = new SaveSceneRequest();
               // saveScene.setRef(5000010000004181); // Set your values accordingly
                saveScene.setName("Conference Room Scene");
              //  saveScene.setgAAProjectSpaceTypeRef(5000000000001663); // Set GAAProjectSpaceTypeRef

                List<SceneConfig> configurations = new ArrayList<>();

            // Add configurations using a loop
                int numberOfConfigurations = 1;
                for (int i = 0; i < numberOfConfigurations; i++) {
                    Log.e(TAG, " For Loop Done " );
                    SceneConfig sceneConfig = new SceneConfig();
                  //  sceneConfig.setgAAProjectSceneRef(5000010000004181); // Set values accordingly
                 //   sceneConfig.setgAAProjectSpaceTypePlannedDeviceRef(5000000000002780);
                    sceneConfig.setNodeConfigDeviceName("Node Config" + i);
                    sceneConfig.setNodeConfigParamName("" + i); // Example of dynamic naming
                    sceneConfig.setValue("" + i); // Example of dynamic values

                    configurations.add(sceneConfig);
                }

                saveScene.setConfigurations(configurations);

                Call<SceneResModel> call = apiService.saveScene(saveScene);
                call.enqueue(new Callback<SceneResModel>() {
                    @Override
                    public void onResponse(Call<SceneResModel> call, Response<SceneResModel> response) {
                        if (response.isSuccessful()) {
                            // Handle successful response
                            Log.e(TAG, "Done " );
                        } else {
                            // Handle unsuccessful response
                        }
                    }

                    @Override
                    public void onFailure(Call<SceneResModel> call, Throwable t) {
                        // Handle API call failure
                        Log.e(TAG, "Failure" );
                    }
                });

            }
        });

    }

    private void getScene(Long gaaProjectSceneRef, String loginToken, String loginDeviceId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<SceneResModel> call = apiService.getScene(gaaProjectSceneRef, loginToken, loginDeviceId);
        call.enqueue(new Callback<SceneResModel>() {
            @Override
            public void onResponse(Call<SceneResModel> call, Response<SceneResModel> response) {
                if (response.isSuccessful()) {
                    SceneResModel apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getSuccessful()) {
                        ObjectTag scene = apiResponse.getObjectTag();
                        // Update EditText fields with ObjectTag data
                        editTextProjectName.setText(scene.getgAAProjectName());
                        //  editTextSpaceName.setText(scene.getGAAProjectSpaceTypeName());
                        editTextSceneName.setText(scene.getName());

                        List<Configuration> configurations = scene.getConfigurations();
                        for (Configuration configuration : configurations) {
                            Log.e(TAG, "Scene Planed Device Name: " + configuration.getgAAProjectSpaceTypePlannedDeviceName());

                            ConfigArrayList.add(new Configuration(configuration.getgAAProjectSceneRef(),configuration.getgAAProjectSpaceTypePlannedDeviceConnectionRef(),configuration.getNodeConfigParamName(),configuration.getgAAProjectSceneCode(),configuration.getgAAProjectSceneName(),configuration.getgAAProjectSceneCode(),configuration.getgAAProjectSpaceTypeRef(),configuration.getgAAProjectSpaceTypeName(),configuration.getgAAProjectSpaceTypeAreaRef(),configuration.getgAAProjectSpaceTypeAreaName(),configuration.getgAAProjectSpaceTypePlannedDeviceRef(),configuration.getgAAProjectSpaceTypePlannedDeviceName(),configuration.getLabel(),configuration.getOutputDriverChannelRef(),configuration.getOutputDriverChannelName(),configuration.getgAAProjectRef(),configuration.getgAAProjectName()));
                        }
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(gridLayoutManager1);
                        SceneCheckAdapter sceneCheckAdapter = new SceneCheckAdapter(ConArrayList,ConfigArrayList);
                        recyclerView.setAdapter(sceneCheckAdapter);

                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<SceneResModel> call, Throwable t) {
                Log.e("MainActivity", "Failed to get response");
            }
        });
    }

//    private void fetchInstallerControls(String GAAProjectSpaceRef,Long AreaRef,String LoginToken, String LoginDeviceId) {
//
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//
//        Call<InstallerLandingResModel> call = apiService.getDevices(GAAProjectSpaceRef,AreaRef,LoginToken,LoginDeviceId);
//
//        call.enqueue(new Callback<InstallerLandingResModel>() {
//            @Override
//            public void onResponse(Call<InstallerLandingResModel> call, Response<InstallerLandingResModel> response) {
//                if (response.isSuccessful()) {
//                    InstallerLandingResModel installerLandingResModel = response.body();
//                    if (installerLandingResModel != null && installerLandingResModel.getSuccessful()) {
//                        Data data = installerLandingResModel.getData();
//                        List<InstallerControl> installerControls = data.getInstallerControls();
//                        editTextSpaceName.setText(data.getGaaProjectSpaceName());
//
//                        List<Controls> controlsDevice = data.getInstallerControls().get(0).getControls();
//                        for (Controls controls : controlsDevice) {
//                            Log.e(TAG, "onResponse Plan Device Name: " + controls.getGaaProjectSpaceTypePlannedDeviceName());
//                            Log.e(TAG, "onResponse Plan Device Ref: " + controls.getGaaProjectSpaceTypePlannedDeviceRef());
//                            ConArrayList.add(new Controls(controls.getNodeId(),controls.getDisplayOrder(),controls.getGaaProjectSpaceTypePlannedDeviceRef(),controls.getGaaProjectSpaceTypePlannedDeviceName(),controls.isProvisioned()));
//
//                            String PlanDeviceRef = String.valueOf(controls.getGaaProjectSpaceTypePlannedDeviceRef());
//                            saveSpaceTypePlannedDeviceRef(PlanDeviceRef);
//
//                        }
//
//                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false);
//                        recyclerView.setLayoutManager(gridLayoutManager1);
//                        SceneCheckAdapter sceneCheckAdapter = new SceneCheckAdapter(ConArrayList,ConfigArrayList);
//                        recyclerView.setAdapter(sceneCheckAdapter);
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<InstallerLandingResModel> call, Throwable t) {
//
//            }
//        });
//
//    }
    private void saveSpaceTypePlannedDeviceRef(String plannedDeviceRef) {
       SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPreferencesPDR", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SpaceTypePlannedDeviceRef", plannedDeviceRef);
        Log.e(TAG, "Space Type Planned DeviceRef: "+plannedDeviceRef );
        editor.apply();
    }

    //for spinner
    private void fetchAreas(String GAAProjectSpaceRef, String LoginToken, String LoginDeviceId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectAreaLandingResModel> call = apiService.getAreaLandingPageData(GAAProjectSpaceRef, LoginToken, LoginDeviceId);
        call.enqueue(new Callback<ProjectAreaLandingResModel>() {
            @Override
            public void onResponse(Call<ProjectAreaLandingResModel> call, Response<ProjectAreaLandingResModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProjectAreaLandingResModel dataResponse = response.body();
                    List<Area> areas = dataResponse.getData().getAreas();

                    for(Area area : areas) {
                        Log.e(TAG, "onResponse AreaName: " + area.getGAAProjectSpaceTypeAreaName());
                        Log.e(TAG, "onResponse Area Ref: " + area.getGAAProjectSpaceTypeAreaRef());
                        arrayList2.add(new Area(area.getGAAProjectSpaceTypeAreaRef(), area.getGAAProjectSpaceTypeAreaName(), area.getWifiSSID(), area.getWifiPassword(), area.getGuestControls(), area.getInstallerControls()));

                        if (area.getGAAProjectSpaceTypeAreaRef() == 0) {

                        }
                    }
                    // Create custom spinner adapter
                    AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(requireContext(),spinner,areas);

                    // Set the listener for the adapter
                    adapter.setOnAreaSelectedListener(EditSceneFragment.this); // Replace YourFragmentClassName with the name of your fragment class

                    spinner.setAdapter(adapter);

                    // Handle item selection
////                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                        @Override
////                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
////
////                        }
////                        @Override
////                        public void onNothingSelected(AdapterView<?> parentView) {
////
////                        }
////                    });

                }
            }

            @Override
            public void onFailure(Call<ProjectAreaLandingResModel> call, Throwable t) {
                // Handle API call failure
            }
        });
    }

    private long getSelectedAreaRefFromPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getLong(PREF_SELECTED_AREA_REF, 0L);
    }



    private long getSelectedAreaRefFromPreferences2(Long selectedAreaRef) {
        // Get an instance of SharedPreferences
        SharedPreferences sharedPreferences =  requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //  Long selectedAreaRef = null;
        editor.putLong(ARG_SELECTED_AREA_REF, selectedAreaRef);
        editor.apply();

        return sharedPreferences.getLong(PREF_SELECTED_AREA_REF, 0L);
    }

    @Override
    public void onAreaSelected(String selectedAreaRef) {

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();

        SharedPreferences  sharedPreferences2 = requireContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "Project Space loginToken: "+savedLoginDeviceId );
        String loginToken = savedLoginDeviceId.trim();

        SharedPreferences sharedPreferences5 = requireContext().getSharedPreferences("MyPrefsPSR", MODE_PRIVATE);
        String saveProjectSpaceRef = sharedPreferences5.getString("Project_Space_Ref", "");
        Log.e(TAG, "Project Space Ref: "+savedLoginDeviceId );
        String projectSpaceRef = saveProjectSpaceRef.trim();

        long gAAProjectSpaceTypeAreaRef = getSelectedAreaRefFromPreferences2(Long.valueOf(selectedAreaRef));
        //fetchInstallerControls(projectSpaceRef,gAAProjectSpaceTypeAreaRef,loginToken,loginDeviceId);
    }
}