package com.espressif.ui.fragment.Home;

import static android.content.ContentValues.TAG;

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
import com.espressif.ui.adapters.ProjectSpaceNameAdapter;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.ProjectSpaceLandingReqModel;
import com.espressif.ui.models.ProjectSpaceLandingResModel;
import com.espressif.ui.models.SpaceLanding;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProjectSpaceLandingFragment extends Fragment {

    TextView userName, projectName, spaceGroupName;
    RecyclerView rVSpaceName;

    private ArrayList<SpaceLanding> arrayList;


    private static final String ARG_SELECTED_AREA_REF = "selectedAreaRef";


    private static final String PREF_SELECTED_AREA_REF = "selectedAreaRef";



    public ProjectSpaceLandingFragment() {
        // Required empty public constructor
    }

    public static ProjectSpaceLandingFragment newInstance(Context context, String selectedAreaRef) {
        ProjectSpaceLandingFragment fragment = new ProjectSpaceLandingFragment();

        // Store the selectedAreaRef in SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ARG_SELECTED_AREA_REF, selectedAreaRef);
        editor.apply(); // Or use editor.commit() if you want it to be synchronous

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_space_landing, container, false);

        rVSpaceName = view.findViewById(R.id.rVProjectSpaceNameV);

        arrayList = new ArrayList<>();

        // Retrieve GUID ID from SharedPreferences (loginDeviceId)
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space Landing LoginDeviceId: " + GUID);
        String loginDeviceId = GUID.trim();


        //Login Token
        SharedPreferences sharedPreferences2 = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String savedLoginToken = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "ProjectSpaceLandingActivity Login Token: " + savedLoginToken);
        String loginToken = savedLoginToken.trim();

        String ProjectSpaceGroupRef = getSelectedAreaRefFromPreferences();



        getSpaceName(ProjectSpaceGroupRef,loginToken,loginDeviceId);


        return view;
    }

    private void  getSpaceName(String ProjectSpaceGroupRef, String loginToken,String loginDeviceId) {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectSpaceLandingResModel> call = apiService.getSpaceNameData(ProjectSpaceGroupRef, loginToken, loginDeviceId);

        call.enqueue(new Callback<ProjectSpaceLandingResModel>() {
            @Override
            public void onResponse(Call<ProjectSpaceLandingResModel> call, Response<ProjectSpaceLandingResModel> response) {
                if (response.isSuccessful()) {
                    ProjectSpaceLandingResModel projectSpaceLandingResModel = response.body();
                    if (projectSpaceLandingResModel != null && projectSpaceLandingResModel.isSuccessful()) {
                        List<ProjectSpaceLandingReqModel.Space> space = projectSpaceLandingResModel.getData().getSpaces();

                        for (ProjectSpaceLandingReqModel.Space space1 : space) {
                            Log.e(TAG, "onResponse SpaceGroupName: " + space1.getGAAProjectSpaceName());
                            Log.e(TAG, "onResponse getGAAProjectSpaceRef: "+space1.getGAAProjectSpaceRef());
                            Log.e(TAG, "onResponse getGAAProjectSpaceTypeRef: "+space1.getGAAProjectSpaceTypeRef());

                            arrayList.add(new SpaceLanding(space1.getGAAProjectSpaceRef(), space1.getGAAProjectSpaceName(),space1.getGAAProjectSpaceTypeRef(),space1.getGAAProjectSpaceTypeName(), space1.getDisplayOrder(), space1.getDescription()));

                        }

                        //add arraylist code and create space group class

                        ProjectSpaceNameAdapter projectSpaceNameAdapter = new ProjectSpaceNameAdapter(arrayList);
                        rVSpaceName.setAdapter(projectSpaceNameAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireActivity(), 1, GridLayoutManager.HORIZONTAL, false);
                        rVSpaceName.setLayoutManager(gridLayoutManager1);
                    }
                }
            }
            @Override
            public void onFailure(Call<ProjectSpaceLandingResModel> call, Throwable t) {
            }
        });
    }

    private String getSelectedAreaRefFromPreferences() {
        // Get an instance of SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREF_SELECTED_AREA_REF, "");
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