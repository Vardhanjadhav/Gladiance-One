package com.espressif.ui.fragment.Home;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.ProjectSpaceGroupListAdapter;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.ProjectSpaceGroupResModel;
import com.espressif.ui.models.SpaceGroup;
import com.espressif.wifi_provisioning.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SpaceGroupFragment extends BottomSheetDialogFragment {
    RecyclerView rvProjectSpaceGroupList;
    ProjectSpaceGroupListAdapter projectSpaceGroupListAdapter;

    TextView projectName;
    private ArrayList<SpaceGroup> arrayList;
    SharedPreferences sharedPreferences;

    public SpaceGroupFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_space_group, container, false);

        projectName = view.findViewById(R.id.projectName);
        rvProjectSpaceGroupList = view.findViewById(R.id.rVProjectSpaceGroupName);
        arrayList = new ArrayList<>();

        // Retrieve GUID ID from SharedPreferences (loginDeviceId)
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();

        //Login Token
        SharedPreferences  sharedPreferences2 = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String savedLoginToken = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "ProjectSpaceGroupActivity Login Token: "+savedLoginToken );
        String loginToken = savedLoginToken.trim();


        SharedPreferences sharedPreferencesProName = requireActivity().getSharedPreferences("MyPrefsPN", Context.MODE_PRIVATE);
        String ProjectName = sharedPreferencesProName.getString("ProjectName", "");
        projectName.setText(ProjectName);
        Log.e(TAG, "Home Fragment Project Name : "+ ProjectName );

        SharedPreferences sharedPreferences5 = requireContext().getSharedPreferences("MyPrefsPR", Context.MODE_PRIVATE);
        String ProjectRef = sharedPreferences5.getString("ProjectRef", "");
        String gAAProjectRef = ProjectRef.trim();
        Log.e(TAG, "ProjectSpaceGroupActivity Project Name : "+ProjectRef );


        getSpaceGroupName(gAAProjectRef,loginToken,loginDeviceId);

        return view;
    }

    private void  getSpaceGroupName(String gAAProjectRef2, String loginToken,String loginDeviceId){


        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectSpaceGroupResModel> call = apiService.getProjectData(gAAProjectRef2,loginToken,loginDeviceId);

        call.enqueue(new Callback<ProjectSpaceGroupResModel>() {
            @Override
            public void onResponse(Call<ProjectSpaceGroupResModel> call, Response<ProjectSpaceGroupResModel> response) {
                if(response.isSuccessful()){
                    ProjectSpaceGroupResModel projectSpaceGroupResModel = response.body();
                    if (projectSpaceGroupResModel != null && projectSpaceGroupResModel.isSuccessful()){
                        List<ProjectSpaceGroupReqModel.SpaceGroup> spaceGroups = projectSpaceGroupResModel.getData().getSpaceGroups();

                        for(ProjectSpaceGroupReqModel.SpaceGroup spaceGroup1 : spaceGroups){
                            Log.e(TAG, "onResponse SpaceGroupName: " + spaceGroup1.getGAAProjectSpaceGroupName());
                            arrayList.add(new SpaceGroup(spaceGroup1.getGAAProjectSpaceGroupRef(),spaceGroup1.getGAAProjectSpaceGroupName(),spaceGroup1.getDisplayOrder()));


                        }
                        //add arraylist code and create space group class

                        ProjectSpaceGroupListAdapter projectSpaceGroupListAdapter = new ProjectSpaceGroupListAdapter(arrayList);
                        rvProjectSpaceGroupList.setAdapter(projectSpaceGroupListAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
                        rvProjectSpaceGroupList.setLayoutManager(gridLayoutManager1);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectSpaceGroupResModel> call, Throwable t) {

            }
        });

    }
}