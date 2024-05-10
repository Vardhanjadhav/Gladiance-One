package com.espressif.ui.fragment.RoomControl;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.ControlAdapter;
import com.espressif.ui.adapters.DeviceControlAdapter;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.guestlandingpage.GuestLandingResModel;
import com.espressif.ui.models.guestlandingpage.GuestControls;
import com.espressif.ui.models.guestlandingpage.Controls;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceLandingFragment extends Fragment implements ControlAdapter.OnControlTypeClickListener {

    private static final String ARG_SELECTED_AREA_REF = "selectedAreaRef";

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    private static final String PREF_SELECTED_AREA_REF = "selectedAreaRef";


    RecyclerView recyclerView, guestRecyclerView;
    //ControlAdapter.OnControlTypeClickListener OnControlTypeClickListener ;

    Context context;
    TextView DeviceName;

    public DeviceLandingFragment() {
        // Required empty public constructor
    }

    public static DeviceLandingFragment newInstance(Context context, Long selectedAreaRef) {
        DeviceLandingFragment fragment = new DeviceLandingFragment();

        // Store the selectedAreaRef in SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(ARG_SELECTED_AREA_REF, selectedAreaRef);
        editor.apply(); // Or use editor.commit() if you want it to be synchronous

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_device_landing, container, false);


        recyclerView = view.findViewById(R.id.controlTypeRecyclerView);
        guestRecyclerView = view.findViewById(R.id.guestControlTypeRecyclerView);

        DeviceName = view.findViewById(R.id.device);

        DeviceName.setVisibility(View.GONE);
        List<GuestControls> controlsList = new ArrayList<>();

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space GUID/LoginDeviceId: " + GUID);
        String loginDeviceId = GUID.trim();


        SharedPreferences sharedPreferences2 = requireContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "Project Space loginToken: " + savedLoginDeviceId);
        String loginToken = savedLoginDeviceId.trim();


        SharedPreferences sharedPreferences5 = requireContext().getSharedPreferences("MyPrefsPSR", MODE_PRIVATE);
        String saveProjectSpaceRef = sharedPreferences5.getString("Project_Space_Ref", "");
        Log.e(TAG, "Project Space Ref: " + savedLoginDeviceId);
        String projectSpaceRef = saveProjectSpaceRef.trim();

        long gAAProjectSpaceTypeAreaRef = getSelectedAreaRefFromPreferences();

        fetchInstallerControlsType(projectSpaceRef, gAAProjectSpaceTypeAreaRef, loginToken, loginDeviceId);
        //fetchControls(projectSpaceRef, gAAProjectSpaceTypeAreaRef, loginToken, loginDeviceId);
        //fetchInstallerControls(projectSpaceRef,gAAProjectSpaceTypeAreaRef,loginToken,loginDeviceId);


        return view;
    }

//    private void fetchInstallerControlsType(String GAAProjectSpaceRef,Long AreaRef,String LoginToken, String LoginDeviceId) {
//
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//
//        Call<GuestLandingResModel> call = apiService.getControlTypeName(GAAProjectSpaceRef,AreaRef,LoginToken,LoginDeviceId);
//
//        call.enqueue(new Callback<GuestLandingResModel>() {
//            @Override
//            public void onResponse(Call<GuestLandingResModel> call, Response<GuestLandingResModel> response) {
//                if (response.isSuccessful()) {
//                    GuestLandingResModel guestLandingResModel = response.body();
//                    if (guestLandingResModel != null && guestLandingResModel.getSuccessful()) {
//                        Data data = guestLandingResModel.getData();
//                        List<GuestControls> guestControls = data.getGuestControls();
//                        if (!guestControls.isEmpty()) {
//
//                           // DeviceName.setText(guestControls.get(0).getControlTypeName())
//                            GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false);
//                            recyclerView.setLayoutManager(gridLayoutManager);
//                            ControlAdapter adapter = new ControlAdapter(data.getGuestControls(),context);
//                            recyclerView.setAdapter(adapter);
//
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<GuestLandingResModel> call, Throwable t) {
//               // DeviceName.setText("Network error: " + t.getMessage());
//            }
//        });
//
//    }
//

    private void fetchInstallerControlsType(String GAAProjectSpaceRef,Long AreaRef,String LoginToken, String LoginDeviceId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<GuestLandingResModel> call = apiService.getControlTypeName(GAAProjectSpaceRef,AreaRef,LoginToken,LoginDeviceId);
        call.enqueue(new Callback<GuestLandingResModel>() {
            @Override
            public void onResponse(Call<GuestLandingResModel> call, Response<GuestLandingResModel> response) {
                if (response.isSuccessful()) {
                    GuestLandingResModel responseModel = response.body();
                    if (responseModel != null && responseModel.getData() != null) {
                        List<GuestControls> controlsList = responseModel.getData().getGuestControls();
                        if (controlsList != null && !controlsList.isEmpty()) {
                            List<Controls> allControls = new ArrayList<>();
                            for (GuestControls guestControls : controlsList) {
                                allControls.addAll(guestControls.getControls());
                            }
                            // Set up ControlTypeName RecyclerView
//
                            guestRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2,GridLayoutManager.VERTICAL, false));
                            DeviceControlAdapter deviceControlAdapter = new DeviceControlAdapter(allControls, requireContext());
                            guestRecyclerView.setAdapter(deviceControlAdapter);


                            GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            ControlAdapter controlAdapter = new ControlAdapter(controlsList, requireContext(), new ControlAdapter.OnControlTypeClickListener() {
                                @Override
                                public void onControlTypeClicked(GuestControls control) {
                                    Log.d("ControlAdapter", "Control clicked: " + control.getControlTypeName());
                                    List<Controls> filteredControls = control.getControls();


                                    guestRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2,GridLayoutManager.VERTICAL, false));
                                    DeviceControlAdapter deviceControlAdapter = new DeviceControlAdapter(filteredControls, requireContext());
                                    guestRecyclerView.setAdapter(deviceControlAdapter);
                                }
                            });
                            recyclerView.setAdapter(controlAdapter);

                        }

                    }
                } else {
                    Log.e("API Response", "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GuestLandingResModel> call, Throwable t) {
                Log.e("API Error", "Error fetching controls: " + t.getMessage());
            }
        });
    }

    @Override
    public void onControlTypeClicked(GuestControls control) {
        // Filter Controls based on the selected ControlType
        /*List<Controls> filteredControls = control.getControls();


        guestRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2,GridLayoutManager.VERTICAL, false));
        DeviceControlAdapter deviceControlAdapter = new DeviceControlAdapter(filteredControls, requireContext());
        guestRecyclerView.setAdapter(deviceControlAdapter);*/
    }


//    private void fetchControls(String GAAProjectSpaceRef,Long AreaRef,String LoginToken, String LoginDeviceId) {
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        Call<GuestLandingResModel> call = apiService.getControlTypeName(GAAProjectSpaceRef,AreaRef,LoginToken,LoginDeviceId);
//        call.enqueue(new Callback<GuestLandingResModel>() {
//            @Override
//            public void onResponse(Call<GuestLandingResModel> call, Response<GuestLandingResModel> response) {
//                if (response.isSuccessful()) {
//                    GuestLandingResModel responseModel = response.body();
//                    if (responseModel != null && responseModel.getData() != null) {
//                        List<GuestControls> controlsList = responseModel.getData().getGuestControls();
//                        if (controlsList != null && !controlsList.isEmpty()) {
//                            List<Controls> allControls = new ArrayList<>();
//                            for (GuestControls guestControls : controlsList) {
//                                allControls.addAll(guestControls.getControls());
//                            }
//                            // Set up InternalDeviceNameRecyclerView with all InternalDeviceNames
//                            guestRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2,GridLayoutManager.VERTICAL, false));
//                            DeviceControlAdapter deviceControlAdapter = new DeviceControlAdapter(allControls, requireContext());
//                            guestRecyclerView.setAdapter(deviceControlAdapter);
//                        }
//                    }
//                } else {
//                    Log.e("API Response", "Unsuccessful response: " + response.code());
//                }
//            }
//            @Override
//            public void onFailure(Call<GuestLandingResModel> call, Throwable t) {
//                Log.e("API Error", "Error fetching controls: " + t.getMessage());
//            }
//        });
//    }



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
//                        com.espressif.ui.models.lnstallerlandingpage.Data data = installerLandingResModel.getData();
//                        List<InstallerControl> installerControls = data.getInstallerControls();
//                        if (!installerControls.isEmpty()) {
////                            // Display controlTypeName in TextView
//                         // DeviceName.setText(installerControls.get(0).getControlTypeName());
//
////                           // Display controls in RecyclerView
//                            GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false);
//                            guestRecyclerView.setLayoutManager(gridLayoutManager);
//                            DeviceControlAdapter adapter = new DeviceControlAdapter(data.getInstallerControls().get(0).getControls(),context);
//                            guestRecyclerView.setAdapter(adapter);
//
//                        }
//                    }
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<InstallerLandingResModel> call, Throwable t) {
//                DeviceName.setText("Network error: " + t.getMessage());
//            }
//        });
//
//    }

    private long getSelectedAreaRefFromPreferences() {
        // Get an instance of SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getLong(PREF_SELECTED_AREA_REF, 0L);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                // Handle back button press here
//                Intent intent = new Intent(requireActivity(), ProjectSpaceLandingActivity.class);
//                startActivity(intent);
                requireActivity().onBackPressed();
                return true; // Consumes the back button press event
            }
            return false; // Otherwise, let the system handle it
        });
    }
}