package com.espressif.ui.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.espressif.ui.activities.ApiService;
import com.espressif.ui.activities.RetrofitClient;
import com.espressif.ui.adapters.ProjectListAdapter;
import com.espressif.ui.adapters.ProjectSpaceGroupListAdapter;
import com.espressif.ui.adapters.SpaceListAdapter;

import com.espressif.ui.models.DeviceInfo;
import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.ProjectSpaceGroupResModel;
import com.espressif.ui.models.ProjectSpaceRequestModel;
import com.espressif.ui.models.ProjectSpaceResponseModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_space_group);


//        rvProjectSpaceGroupList = findViewById(R.id.rVProjectSpaceGroupName);
//
//        List<String> projectList = new ArrayList<>();
//        projectList.add("Space 1");
//        projectList.add("Space 2");
//        projectList.add("Space 3");
//
//        projectSpaceGroupListAdapter = new ProjectSpaceGroupListAdapter(this, projectList);
//        rvProjectSpaceGroupList.setAdapter(projectSpaceGroupListAdapter);
//        rvProjectSpaceGroupList.setLayoutManager(new LinearLayoutManager(this));
            getSpaceGroupName();

    }

    private void  getSpaceGroupName(){

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectSpaceGroupResModel> call = apiService.getProjectData(gAAProjectRef,loginToken,loginDeviceId);

        call.enqueue(new Callback<ProjectSpaceGroupResModel>() {
            @Override
            public void onResponse(Call<ProjectSpaceGroupResModel> call, Response<ProjectSpaceGroupResModel> response) {
                if(response.isSuccessful()){
                    ProjectSpaceGroupResModel projectSpaceGroupResModel = response.body();
                    if (projectSpaceGroupResModel != null && projectSpaceGroupResModel.isSuccessful()){
                       List<ProjectSpaceGroupReqModel.SpaceGroup> spaceGroups = projectSpaceGroupResModel.getData().getGAAProjectSpaceGroups();

                        for(ProjectSpaceGroupReqModel.SpaceGroup spaceGroup1 : spaceGroups){
                            Log.e(TAG, "onResponse SpaceGroupName: " + spaceGroup1.getGAAProjectSpaceGroupName() );
                            // arrayList.add(new Space(space1.getGAAProjectSpaceRef(),space1.getSpaceName(),space1.getDisplayOrder(),space1.getDescription()));
                        }
                        //add arraylist code and create space group class

                        ProjectSpaceGroupListAdapter projectSpaceGroupListAdapter = new ProjectSpaceGroupListAdapter(spaceGroups);
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
}
