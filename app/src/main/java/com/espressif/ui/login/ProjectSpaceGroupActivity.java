package com.espressif.ui.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.espressif.ui.activities.ApiService;
import com.espressif.ui.activities.RetrofitClient;
import com.espressif.ui.adapters.ProjectListAdapter;
import com.espressif.ui.adapters.ProjectSpaceGroupListAdapter;
import com.espressif.ui.adapters.SpaceListAdapter;

import com.espressif.ui.models.DeviceInfo;
import com.espressif.ui.models.Project;
import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.ProjectSpaceGroupResModel;
import com.espressif.ui.models.ProjectSpaceRequestModel;
import com.espressif.ui.models.ProjectSpaceResponseModel;
import com.espressif.ui.models.Space;
import com.espressif.ui.models.SpaceGroup;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSpaceGroupActivity extends AppCompatActivity {

    RecyclerView rvProjectSpaceGroupList;
    ProjectSpaceGroupListAdapter projectSpaceGroupListAdapter;
    String gAAProjectRef,loginToken,loginDeviceId;
    TextView userName,projectName;
    private ArrayList<SpaceGroup>arrayList;
    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_space_group);

        userName = findViewById(R.id.user_name);
        projectName = findViewById(R.id.projectName);
        rvProjectSpaceGroupList = findViewById(R.id.rVProjectSpaceGroupName);

        arrayList = new ArrayList<>();

        // Retrieve GUID ID from SharedPreferences (loginDeviceId)
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();

        //Login Token
        SharedPreferences  sharedPreferences2 = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String savedLoginToken = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "ProjectSpaceGroupActivity Login Token: "+savedLoginToken );
        String loginToken = savedLoginToken.trim();

        SharedPreferences sharedPreferences3 = getSharedPreferences("MyPreferencesDN", Context.MODE_PRIVATE);
        String savedUserDeviceName = sharedPreferences3.getString("UserDisplayName", "");
        userName.setText(savedUserDeviceName);
        Log.e(TAG, "ProjectSpaceGroupActivity User Device Name2: "+savedUserDeviceName );
        String userDeviceName = savedUserDeviceName.trim();

        SharedPreferences sharedPreferences4 = getSharedPreferences("MyPreferencesPN", Context.MODE_PRIVATE);
        String saveProjectName = sharedPreferences4.getString("ProjectName", "");
        projectName.setText(saveProjectName);
        Log.e(TAG, "ProjectSpaceGroupActivity Project Name : "+savedUserDeviceName );


        Intent intent = getIntent();
        String gAAProjectRef2 = intent.getStringExtra("MESSAGE_KEY").trim();
        Log.e(TAG, "get Project Ref: "+gAAProjectRef2);

        getSpaceGroupName(gAAProjectRef2,loginToken,loginDeviceId);

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

                            String retrievedSpaceGroupName = spaceGroup1.getGAAProjectSpaceGroupName();
                            saveSpaceGroupName(retrievedSpaceGroupName);

                        }
                        //add arraylist code and create space group class

                        ProjectSpaceGroupListAdapter projectSpaceGroupListAdapter = new ProjectSpaceGroupListAdapter(arrayList);
                        rvProjectSpaceGroupList.setAdapter(projectSpaceGroupListAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceGroupActivity.this,1, GridLayoutManager.VERTICAL,false);
                        rvProjectSpaceGroupList.setLayoutManager(gridLayoutManager1);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectSpaceGroupResModel> call, Throwable t) {

            }
        });

    }

    private void saveSpaceGroupName(String spaceGroupName) {
        sharedPreferences = getSharedPreferences("MyPreferencesSGN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SpaceGroupName", spaceGroupName);
        Log.e(TAG, "SpaceGroupName: "+spaceGroupName );
        editor.apply();
    }
}
