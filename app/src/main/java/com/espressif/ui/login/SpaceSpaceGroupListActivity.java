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
import com.espressif.ui.adapters.ProjectSpaceGroupListAdapter;
import com.espressif.ui.adapters.SpaceSpaceGroupListAdapter;
import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.ProjectSpaceGroupResModel;
import com.espressif.ui.models.SpaceSpaceGroupReqModel;
import com.espressif.ui.models.SpaceSpaceGroupResModel;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpaceSpaceGroupListActivity extends AppCompatActivity {

    RecyclerView rvSpaceSpaceGroupList;
    SpaceSpaceGroupListAdapter spaceSpaceGroupListAdapter;

    String gAAProjectSpaceGroupRef,loginToken,loginDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_space_group_list);

//        rvSpaceSpaceGroupList = findViewById(R.id.rVSpaceSpaceGroupName);
//
//        List<String> SpaceGroupList = new ArrayList<>();
//        SpaceGroupList.add("Space 1");
//        SpaceGroupList.add("Space 2");
//        SpaceGroupList.add("Space 3");
//
//        spaceSpaceGroupListAdapter = new SpaceSpaceGroupListAdapter(this, SpaceGroupList);
//        rvSpaceSpaceGroupList.setAdapter(spaceSpaceGroupListAdapter);
//        rvSpaceSpaceGroupList.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getSpaceGroupName(){

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<SpaceSpaceGroupResModel> call = apiService.getSpaceGroupData(gAAProjectSpaceGroupRef,loginToken,loginDeviceId);

        call.enqueue(new Callback<SpaceSpaceGroupResModel>() {
            @Override
            public void onResponse(Call<SpaceSpaceGroupResModel> call, Response<SpaceSpaceGroupResModel> response) {

                if (response.isSuccessful()) {
                    SpaceSpaceGroupResModel spaceSpaceGroupResModel = response.body();
                    if (spaceSpaceGroupResModel != null && spaceSpaceGroupResModel.isSuccessful()) {
                        List<SpaceSpaceGroupReqModel.Space> spaceGroups = spaceSpaceGroupResModel.getDate().getSpaces();

                        for(SpaceSpaceGroupReqModel.Space space1 : spaceGroups){
                            Log.e(TAG, "onResponse SpaceGroupName: " + space1.getSpaceName());
                            // arrayList.add(new Space(space1.getGAAProjectSpaceRef(),space1.getSpaceName(),space1.getDisplayOrder(),space1.getDescription()));
                        }
                        //add arraylist code and create space group class
                        //Change the name this class spacenamelistactivity
                        spaceSpaceGroupListAdapter = new SpaceSpaceGroupListAdapter(spaceGroups);
                        rvSpaceSpaceGroupList.setAdapter(spaceSpaceGroupListAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(SpaceSpaceGroupListActivity.this, 1, GridLayoutManager.VERTICAL, false);
                        rvSpaceSpaceGroupList.setLayoutManager(gridLayoutManager1);

                    }
                }
            }
            @Override
            public void onFailure(Call<SpaceSpaceGroupResModel> call, Throwable t) {

            }
        });

    }
}