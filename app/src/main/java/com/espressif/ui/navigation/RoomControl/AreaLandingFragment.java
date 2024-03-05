package com.espressif.ui.navigation.RoomControl;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.espressif.ui.activities.ApiService;
import com.espressif.ui.activities.RetrofitClient;
import com.espressif.ui.adapters.ProjectSpaceGroupListAdapter;
import com.espressif.ui.login.AreaAdapter;
import com.espressif.ui.login.LoginActivity;
import com.espressif.ui.login.ProjectSpaceGroupActivity;
import com.espressif.ui.models.SpaceGroup;
import com.espressif.ui.models.arealandingmodel.Area;
import com.espressif.ui.models.arealandingmodel.ProjectAreaLandingResModel;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AreaLandingFragment extends Fragment {

    private Spinner spinnerAreas;
    private ApiService apiService;

    private TextView devise;

    private ArrayList<Area> arrayList;

    RecyclerView recyclerView,areaNameRecycleView;

    SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    public AreaLandingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_area_landing, container, false);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();


        SharedPreferences  sharedPreferences2 = requireContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "Project Space loginToken: "+savedLoginDeviceId );
        String loginToken = savedLoginDeviceId.trim();

//        SharedPreferences  sharedPreferences3 = requireContext().getSharedPreferences("MyPreferencesSN", MODE_PRIVATE);
//        String savedSpaceName = sharedPreferences3.getString("SpaceName", "");
//        Log.e(TAG, "Project Space Name: "+savedSpaceName );
//        String GAAProjectSpaceRef = savedSpaceName.trim();

        //spinnerAreas = view.findViewById(R.id.areaSpinner);

        //devise = view.findViewById(R.id.device);

        arrayList = new ArrayList<>();

        areaNameRecycleView = view.findViewById(R.id.AreaNameRecyclerView);
        recyclerView = view.findViewById(R.id.controlTypeRecyclerView);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            String projectSpaceRef = intent.getStringExtra("PROJECT_SPACE_REF");
            if (projectSpaceRef != null) {
                projectSpaceRef = projectSpaceRef.trim();
                Log.e(TAG, "Project Ref: " + projectSpaceRef);

                saveProjectSpaceRef(projectSpaceRef);

                fetchAreas(projectSpaceRef,loginToken,loginDeviceId);
            }
        }
        return view;
    }

    private void saveProjectSpaceRef(String projectSpaceRef) {

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefsPSR", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PROJECT", projectSpaceRef);
        editor.apply();

    }

    private void fetchAreas(String GAAProjectSpaceRef,String LoginToken, String LoginDeviceId) {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectAreaLandingResModel> call = apiService.getAreaLandingPageData(GAAProjectSpaceRef,LoginToken,LoginDeviceId);
        call.enqueue(new Callback<ProjectAreaLandingResModel>() {
            @Override
            public void onResponse(Call<ProjectAreaLandingResModel> call, Response<ProjectAreaLandingResModel> response) {
                    if (response.isSuccessful()) {
                        ProjectAreaLandingResModel projectAreaLandingResModel = response.body();
                        if (projectAreaLandingResModel != null && projectAreaLandingResModel.getSuccessful()){
                            List<Area> areas = projectAreaLandingResModel.getData().getAreas();

                            for(Area area : areas){
                                Log.e(TAG, "onResponse AreaName: " + area.getGAAProjectSpaceTypeAreaName());
                                Log.e(TAG, "onResponse Area Ref: " + area.getGAAProjectSpaceTypeAreaRef());
                                arrayList.add(new Area(area.getGAAProjectSpaceTypeAreaRef(),area.getGAAProjectSpaceTypeAreaName(),area.getWifiSSID(),area.getWifiPassword(),area.getGuestControls(),area.getInstallerControls()));

                            }

                            //add arraylist code and create space group class
                            AreaAdapter adapter = new AreaAdapter(arrayList);
                            areaNameRecycleView.setAdapter(adapter);
                            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
                            areaNameRecycleView.setLayoutManager(gridLayoutManager1);

                        }
                    }
                    else {
                        // Handle error
                    }
                }
                @Override
                public void onFailure(Call<ProjectAreaLandingResModel> call, Throwable t) {
                    // Handle failure
                }
        });
    }
}