package com.espressif.ui.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.espressif.ui.activities.ApiService;
import com.espressif.ui.activities.EspMainActivity;
import com.espressif.ui.activities.RetrofitClient;
import com.espressif.ui.adapters.CardAdapter;
import com.espressif.ui.adapters.ProjectListAdapter;
import com.espressif.ui.adapters.SpaceListAdapter;
import com.espressif.ui.models.Devices;
import com.espressif.ui.models.Project;
import com.espressif.ui.models.ProjectSpaceRequestModel;
import com.espressif.ui.models.ProjectSpaceResponseModel;
import com.espressif.ui.models.Space;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSpaceActivity extends AppCompatActivity {

    RecyclerView rvProjectList;
    RecyclerView rvSpaceList;
    ProjectListAdapter projectListAdapter;
    SpaceListAdapter spaceListAdapter;
    private ArrayList<Space> arrayList;
    private ArrayList<Project> arrayList1;
    String loginToken, loginDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_space);

//        rvProjectList = findViewById(R.id.rVProjectName);
//
//        List<String> projectList = new ArrayList<>();
//        projectList.add("Hello, RecyclerView");
//        projectList.add("Hello, RecyclerView");
//        projectList.add("Hello, RecyclerView");
//
//        projectListAdapter = new ProjectListAdapter(this, projectList);
//        rvProjectList.setAdapter(projectListAdapter);
//        rvProjectList.setLayoutManager(new LinearLayoutManager(this));

        getProjectName();
        //Space List Recycle Code
//        rvSpaceList = findViewById(R.id.rVSpaceName);
//
//        List<String> SpaceList = new ArrayList<>();
//        SpaceList.add("Hello, RecyclerView");
//        SpaceList.add("Hello, RecyclerView");
//        SpaceList.add("Hello, RecyclerView");
//
//        spaceListAdapter = new SpaceListAdapter(this, SpaceList);
//        rvSpaceList.setAdapter(spaceListAdapter);
//        rvSpaceList.setLayoutManager(new LinearLayoutManager(this));
        getSpaceName();
    }

    private void getProjectName() {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectSpaceResponseModel> call = apiService.getLoginData(loginToken, loginDeviceId);

        call.enqueue(new Callback<ProjectSpaceResponseModel>() {
            @Override
            public void onResponse(Call<ProjectSpaceResponseModel> call, Response<ProjectSpaceResponseModel> response) {
                if (response.isSuccessful()) {
                    ProjectSpaceResponseModel projectSpaceResponseModel = response.body();
                    if (projectSpaceResponseModel != null && projectSpaceResponseModel.isSuccessful()) {
                        List<ProjectSpaceRequestModel.Project> projects = projectSpaceResponseModel.getData().getProjects();

                        for(ProjectSpaceRequestModel.Project project1 : projects){
                            Log.e(TAG, "onResponse SpaceName: " + project1.getGAAProjectName());
                            Log.e(TAG, "onResponse Display Order : " + project1.getGAAProjectRef());
                             //arrayList1.add(new Project(project1.getGAAProjectRef(),project1.getGAAProjectName()));
                        }

                        ProjectListAdapter projectListAdapter = new ProjectListAdapter(projects);
                        rvProjectList.setAdapter(projectListAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceActivity.this,1, GridLayoutManager.VERTICAL,false);
                        rvProjectList.setLayoutManager(gridLayoutManager1);
                        //If any error change adapter class
                    } else {
                        Log.e("MainActivity", "Unsuccessful response: " + projectSpaceResponseModel.getMessage());
                    }
                } else {
                    Log.e("MainActivity", "Failed to get response");
                }
            }

            @Override
            public void onFailure(Call<ProjectSpaceResponseModel> call, Throwable t) {

            }
        });
    }

    private void getSpaceName(){

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectSpaceResponseModel> call = apiService.getLoginData(loginToken, loginDeviceId);

        call.enqueue(new Callback<ProjectSpaceResponseModel>() {
            @Override
            public void onResponse(Call<ProjectSpaceResponseModel> call, Response<ProjectSpaceResponseModel> response) {
                {
                    if (response.isSuccessful()) {
                        ProjectSpaceResponseModel projectSpaceResponseModel = response.body();
                        if (projectSpaceResponseModel != null && projectSpaceResponseModel.isSuccessful()) {
                            List<ProjectSpaceRequestModel.Space> space = projectSpaceResponseModel.getData().getSpaces();

                            for(ProjectSpaceRequestModel.Space space1 : space){
                                Log.e(TAG, "onResponse SpaceName: " + space1.getSpaceName() );
                                Log.e(TAG, "onResponse Display Order : " + space1.getDisplayOrder() );
                               // arrayList.add(new Space(space1.getGAAProjectSpaceRef(),space1.getSpaceName(),space1.getDisplayOrder(),space1.getDescription()));
                            }

                            //If any error change adapter class
                            SpaceListAdapter spaceListAdapter = new SpaceListAdapter(space);
                            rvSpaceList.setAdapter(spaceListAdapter);
                            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceActivity.this,1, GridLayoutManager.VERTICAL,false);
                            rvSpaceList.setLayoutManager(gridLayoutManager1);

                        } else {
                            Log.e("ProjectSpaceActivity", "Unsuccessful response: " + projectSpaceResponseModel.getMessage());
                        }
                    } else {
                        Log.e("ProjectSpaceActivity", "Failed to get response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectSpaceResponseModel> call, Throwable t) {

            }
        });

    }
}