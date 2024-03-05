package com.espressif.ui.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
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
import com.espressif.ui.adapters.ControlAdapter;
import com.espressif.ui.models.lnstallerlandingpage.Data;
import com.espressif.ui.models.lnstallerlandingpage.InstallerControl;
import com.espressif.ui.models.lnstallerlandingpage.InstallerLandingResModel;
import com.espressif.ui.navigation.ControlBouquet.TelephoneAdapter;
import com.espressif.wifi_provisioning.R;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaLandingActivity extends AppCompatActivity {

    TextView DeviceName;
    RecyclerView recyclerView;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_landing);

        DeviceName = findViewById(R.id.device);
        recyclerView = findViewById(R.id.controlTypeRecyclerView);



        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();


        SharedPreferences  sharedPreferences2 = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "Project Space loginToken: "+savedLoginDeviceId );
        String loginToken = savedLoginDeviceId.trim();

//        Intent intent = getIntent();
//        String ProjectSpaceGroupRef = intent.getStringExtra("PROJECT_SPACE_REF_AREA").trim();
//        Log.e(TAG, "get Project Ref: "+ProjectSpaceGroupRef);

        SharedPreferences sharedPreferences5 = getSharedPreferences("MyPrefsPSR", Context.MODE_PRIVATE);
        String username = sharedPreferences5.getString("PROJECT", "");
        String ProjectSpaceGroupRef = username.trim();

        Intent intent1 = getIntent();
        Long GAAProjectSpaceAreaRef = intent1.getLongExtra("SPACE_TYPE_AREA_REF", 0l);
        Log.e(TAG, "Space Type Area Ref: " + GAAProjectSpaceAreaRef);

        fetchInstallerControls(ProjectSpaceGroupRef,GAAProjectSpaceAreaRef,loginToken,loginDeviceId);

    }
    private void fetchInstallerControls(String GAAProjectSpaceRef,Long AreaRef,String LoginToken, String LoginDeviceId) {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Call<InstallerLandingResModel> call = apiService.getDevices(GAAProjectSpaceRef,AreaRef,LoginToken,LoginDeviceId);

        //Call<Object> call = apiService.getDevices(GAAProjectSpaceRef,AreaRef,LoginToken,LoginDeviceId);

//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                if (response.isSuccessful()) {
////                    InstallerLandingResModel installerLandingResModel = response.body();
////                    if (installerLandingResModel != null && installerLandingResModel.getSuccessful()) {
////                        Data data = installerLandingResModel.getData();
////                        List<InstallerControl> installerControls = data.getInstallerControls();
////                        if (!installerControls.isEmpty()) {
////                            // Display controlTypeName in TextView
////                            DeviceName.setText(installerControls.get(0).getControlTypeName());
////
////                            // Display controls in RecyclerView
////                            recyclerView.setLayoutManager(new LinearLayoutManager(AreaLandingActivity.this));
////                            ControlAdapter adapter = new ControlAdapter(installerControls);
////                            recyclerView.setAdapter(adapter);
////
////
////
////                        }
////                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                DeviceName.setText("Network error: " + t.getMessage());
//            }
//        });

        call.enqueue(new Callback<InstallerLandingResModel>() {
            @Override
            public void onResponse(Call<InstallerLandingResModel> call, Response<InstallerLandingResModel> response) {
                if (response.isSuccessful()) {
                    InstallerLandingResModel installerLandingResModel = response.body();
                    if (installerLandingResModel != null && installerLandingResModel.getSuccessful()) {
                        Data data = installerLandingResModel.getData();
//                        List<InstallerControl> installerControls = data.getInstallerControls();
//                        if (!installerControls.isEmpty()) {
//                            // Display controlTypeName in TextView
//                            //DeviceName.setText(installerControls.get(0).getControlTypeName());
//
//                           // Display controls in RecyclerView
                            recyclerView.setLayoutManager(new LinearLayoutManager(AreaLandingActivity.this));
                            ControlAdapter adapter = new ControlAdapter(data.getInstallerControls().get(0).getControls());
                            recyclerView.setAdapter(adapter);

                        }
                    }
                }



            @Override
            public void onFailure(Call<InstallerLandingResModel> call, Throwable t) {
                DeviceName.setText("Network error: " + t.getMessage());
            }
        });
    }

}
