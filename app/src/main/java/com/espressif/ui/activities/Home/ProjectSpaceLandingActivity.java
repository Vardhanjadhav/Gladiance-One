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
import android.widget.TextView;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.ProjectSpaceNameAdapter;
import com.espressif.ui.models.ProjectSpaceLandingReqModel;
import com.espressif.ui.models.ProjectSpaceLandingResModel;
import com.espressif.ui.models.SpaceLanding;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSpaceLandingActivity extends AppCompatActivity  {

    TextView userName, projectName, spaceGroupName;
    RecyclerView rVSpaceName;

    private ArrayList<SpaceLanding> arrayList;

    SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_space_landing);

        userName = findViewById(R.id.userName);
        projectName = findViewById(R.id.projectName);
        spaceGroupName = findViewById(R.id.spaceGroupName);

        rVSpaceName = findViewById(R.id.rVProjectSpaceName);

        arrayList = new ArrayList<>();

        // Retrieve GUID ID from SharedPreferences (loginDeviceId)
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space Landing LoginDeviceId: " + GUID);
        String loginDeviceId = GUID.trim();


        //Login Token
        SharedPreferences sharedPreferences2 = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String savedLoginToken = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "ProjectSpaceLandingActivity Login Token: " + savedLoginToken);
        String loginToken = savedLoginToken.trim();

        SharedPreferences sharedPreferences3 = getSharedPreferences("MyPreferencesDN", Context.MODE_PRIVATE);
        String savedUserDeviceName = sharedPreferences3.getString("UserDisplayName", "");
        userName.setText(savedUserDeviceName);
        Log.e(TAG, "ProjectSpaceGroupActivity User Device Name: "+savedUserDeviceName );
        String userDeviceName = savedUserDeviceName.trim();

        SharedPreferences sharedPreferences4 = getSharedPreferences("MyPreferencesPN", Context.MODE_PRIVATE);
        String saveProjectName = sharedPreferences4.getString("ProjectName", "");
        projectName.setText(saveProjectName);
        Log.e(TAG, "ProjectSpaceLandingActivity Project Name : "+savedUserDeviceName );

        SharedPreferences sharedPreferences5 = getSharedPreferences("MyPrefsPSGR", Context.MODE_PRIVATE);
        String ProjectSpaceGroupRef = sharedPreferences5.getString("SPACE_GROUP_REF", "");
        Log.e(TAG, "get Project Space Group Ref: "+ProjectSpaceGroupRef);

        SharedPreferences sharedPreferences6 = getSharedPreferences("MyPrefsPSGN", Context.MODE_PRIVATE);
        String ProjectSpaceGroupName = sharedPreferences6.getString("SPACE_GROUP_NAME", "");
        Log.e(TAG, "get Project Space Group Name: "+ProjectSpaceGroupName);
        spaceGroupName.setText(ProjectSpaceGroupName);

        getSpaceName(ProjectSpaceGroupRef,loginToken,loginDeviceId);

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
                            Log.e(TAG, "onResponse SpaceGroupNameSpaceGroupName: " + space1.getGAAProjectSpaceName());
                            Log.e(TAG, "onResponse getGAAProjectSpaceRef: "+space1.getGAAProjectSpaceRef());
                            Log.e(TAG, "onResponse getGAAProjectSpaceTypeRef: "+space1.getGAAProjectSpaceTypeRef());

                            arrayList.add(new SpaceLanding(space1.getGAAProjectSpaceRef(), space1.getGAAProjectSpaceName(),space1.getGAAProjectSpaceTypeRef(),space1.getGAAProjectSpaceTypeName(), space1.getDisplayOrder(), space1.getDescription()));

                        }

                        //add arraylist code and create space group class

                        ProjectSpaceNameAdapter projectSpaceNameAdapter = new ProjectSpaceNameAdapter(arrayList);
                        rVSpaceName.setAdapter(projectSpaceNameAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ProjectSpaceLandingActivity.this, 1, GridLayoutManager.VERTICAL, false);
                        rVSpaceName.setLayoutManager(gridLayoutManager1);
                    }
                }
            }
            @Override
            public void onFailure(Call<ProjectSpaceLandingResModel> call, Throwable t) {
            }
        });
    }


    @Override
    public void onBackPressed() {
        // Handle back button press
        Intent intent = new Intent(this, ProjectSpaceGroupActivity.class);
        startActivity(intent);
        // Optionally, finish() the current activity to remove it from the back stack
        // finish();
    }

}