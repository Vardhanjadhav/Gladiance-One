package com.espressif.ui.activities.Home;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.ProjectListAdapter;
import com.espressif.ui.adapters.SpaceListAdapter;
import com.espressif.ui.models.Project;
import com.espressif.ui.models.ProjectSpaceRequestModel;
import com.espressif.ui.models.ProjectSpaceResponseModel;
import com.espressif.ui.models.Space;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

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

    SharedPreferences sharedPreferences;

    TextView userName;

    Button btnCamera;
    String loginToken, loginDeviceId;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_space);

        rvProjectList = findViewById(R.id.rVProjectName);

//        rvSpaceList  =findViewById(R.id.rVSpaceName);
//        btnCamera = findViewById(R.id.btn_Camera);

        userName = findViewById(R.id.user_name);

        arrayList1 = new ArrayList<>();

        arrayList = new ArrayList<>();

       // btnCamera.setVisibility(View.GONE);



        // Retrieve GUID ID from SharedPreferences (loginDeviceId)
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();


        SharedPreferences sharedPreferences2 = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "Project Space loginToken: "+savedLoginDeviceId );
        String loginToken = savedLoginDeviceId.trim();

        SharedPreferences sharedPreferences3 = getSharedPreferences("MyPreferencesDN", Context.MODE_PRIVATE);
        String savedUserDeviceName = sharedPreferences3.getString("UserDisplayName", "");
        userName.setText(savedUserDeviceName);
        Log.e(TAG, "User Device Name2: "+savedUserDeviceName );
        String userDeviceName = savedUserDeviceName.trim();


        getProjectName(loginToken,loginDeviceId);
        //Space List Recycle Code
       // getSpaceName(loginToken,loginDeviceId);

//        btnCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), CameraDetailActivity.class);
//                startActivity(i);
//            }
//        });


    }

//    private void getProjectName(String loginToken,String loginDeviceId) {
//
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        Call<ProjectSpaceResponseModel> call = apiService.getLoginData(loginToken, loginDeviceId);
//
//        call.enqueue(new Callback<ProjectSpaceResponseModel>() {
//            @Override
//            public void onResponse(Call<ProjectSpaceResponseModel> call, Response<ProjectSpaceResponseModel> response) {
//                if (response.isSuccessful()) {
//                    ProjectSpaceResponseModel projectSpaceResponseModel = response.body();
//                    if (projectSpaceResponseModel != null && projectSpaceResponseModel.isSuccessful()) {
//                        List<ProjectSpaceRequestModel.Project> projects = projectSpaceResponseModel.getData().getProjects();
//
//                        for(ProjectSpaceRequestModel.Project project1 : projects){
//                            Log.e(TAG, "onResponse Project Name: " + project1.getGAAProjectName());
//                            Log.e(TAG, "onResponse Project Ref : " + project1.getGAAProjectRef());
//                            arrayList1.add(new Project(project1.getGAAProjectRef(),project1.getGAAProjectName()));
//
////                              String retrievedLoginDeviceId = project1.getGAAProjectName();
////                              saveProjectName(retrievedLoginDeviceId);
//                        }
//
//                        ProjectListAdapter projectListAdapter = new ProjectListAdapter(arrayList1);
//                        rvProjectList.setAdapter(projectListAdapter);
//                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceActivity.this,1, GridLayoutManager.VERTICAL,false);
//                        rvProjectList.setLayoutManager(gridLayoutManager1);
//                        //If any error change adapter class
//                    } else {
//                        Log.e("MainActivity", "Unsuccessful response: " + projectSpaceResponseModel.getMessage());
//                    }
//                } else {
//                    Log.e("MainActivity", "Failed to get response");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProjectSpaceResponseModel> call, Throwable t) {
//
//            }
//        });
//    }


    private void getProjectName(String loginToken, String loginDeviceId) {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ProjectSpaceResponseModel> call = apiService.getLoginData(loginToken, loginDeviceId);

        call.enqueue(new Callback<ProjectSpaceResponseModel>() {
            @Override
            public void onResponse(Call<ProjectSpaceResponseModel> call, Response<ProjectSpaceResponseModel> response) {
                if (response.isSuccessful()) {
                    ProjectSpaceResponseModel projectSpaceResponseModel = response.body();
                    if (projectSpaceResponseModel != null && projectSpaceResponseModel.isSuccessful()) {
                        List<ProjectSpaceRequestModel.Project> projects = projectSpaceResponseModel.getData().getProjects();

                        if (projects.size() == 1) {
                            // If there is only one project, navigate to the next activity directly
                            navigateToNextActivity(projects.get(0).getGAAProjectRef(),projects.get(0).getGAAProjectName());
                        } else {
                            // If there are multiple projects, display them in the list
                            for (ProjectSpaceRequestModel.Project project1 : projects) {
                                Log.e(TAG, "onResponse Project Name: " + project1.getGAAProjectName());
                                Log.e(TAG, "onResponse Project Ref : " + project1.getGAAProjectRef());
                                arrayList1.add(new Project(project1.getGAAProjectRef(), project1.getGAAProjectName()));
                            }

                            ProjectListAdapter projectListAdapter = new ProjectListAdapter(arrayList1);
                            rvProjectList.setAdapter(projectListAdapter);
                            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceActivity.this, 1, GridLayoutManager.VERTICAL, false);
                            rvProjectList.setLayoutManager(gridLayoutManager1);
                            // If any error change adapter class
                        }
                    } else {
                        Log.e("MainActivity", "Unsuccessful response: " + projectSpaceResponseModel.getMessage());
                    }
                } else {
                    Log.e("MainActivity", "Failed to get response");
                }
            }

            @Override
            public void onFailure(Call<ProjectSpaceResponseModel> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void navigateToNextActivity(String projectRef,String projectName) {
        storeProjectRef(projectRef);
        storeProjectName(projectName);
        Intent intent = new Intent(ProjectSpaceActivity.this, NavBarActivity.class);
        startActivity(intent);
       // finish();

    }

    private void storeProjectRef(String projectRef) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsProjectRefOne", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("projectRefOne", projectRef);
        editor.apply();
    }

    private void storeProjectName(String projectName) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsProjectNameOne", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("projectNameOne", projectName);
        editor.apply();
    }

//    private void getSpaceName(String loginToken,String loginDeviceId){
//
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        Call<ProjectSpaceResponseModel> call = apiService.getLoginData(loginToken, loginDeviceId);
//
//        call.enqueue(new Callback<ProjectSpaceResponseModel>() {
//            @Override
//            public void onResponse(Call<ProjectSpaceResponseModel> call, Response<ProjectSpaceResponseModel> response) {
//                {
//                    if (response.isSuccessful()) {
//                        ProjectSpaceResponseModel projectSpaceResponseModel = response.body();
//                        if (projectSpaceResponseModel != null && projectSpaceResponseModel.isSuccessful()) {
//                            List<ProjectSpaceRequestModel.Space> space = projectSpaceResponseModel.getData().getSpaces();
//
//                            for(ProjectSpaceRequestModel.Space space1 : space){
//                                Log.e(TAG, "onResponse Project Name: " + space1.getSpaceName() );
//                                Log.e(TAG, "onResponse Display Order : " + space1.getDisplayOrder() );
//                               arrayList.add(new Space(space1.getGAAProjectSpaceRef(),space1.getSpaceName(),space1.getDisplayOrder(),space1.getDescription()));
//
//                            }
//
//                            //If any error change adapter class
//                            SpaceListAdapter spaceListAdapter = new SpaceListAdapter(arrayList);
//                            rvSpaceList.setAdapter(spaceListAdapter);
//                            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceActivity.this,1, GridLayoutManager.VERTICAL,false);
//                            rvSpaceList.setLayoutManager(gridLayoutManager1);
//
//                        } else {
//                            Log.e("ProjectSpaceActivity", "Unsuccessful response: " + projectSpaceResponseModel.getMessage());
//                        }
//                    } else {
//                        Log.e("ProjectSpaceActivity", "Failed to get response");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProjectSpaceResponseModel> call, Throwable t) {
//
//            }
//        });
//
//    }



    @Override
    public void onBackPressed() {
        // Close the app when back button is pressed
        finishAffinity();
    }
}