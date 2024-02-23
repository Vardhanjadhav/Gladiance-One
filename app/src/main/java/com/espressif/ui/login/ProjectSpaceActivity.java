package com.espressif.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.espressif.ui.adapters.ProjectListAdapter;
import com.espressif.ui.adapters.SpaceListAdapter;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectSpaceActivity extends AppCompatActivity {

    RecyclerView rvProjectList;
    RecyclerView rvSpaceList;
    ProjectListAdapter projectListAdapter;
    SpaceListAdapter spaceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_space);

        rvProjectList = findViewById(R.id.rVProjectName);

        List<String> projectList = new ArrayList<>();
        projectList.add("Hello, RecyclerView");
        projectList.add("Hello, RecyclerView");
        projectList.add("Hello, RecyclerView");

        projectListAdapter = new ProjectListAdapter(this, projectList);
        rvProjectList.setAdapter(projectListAdapter);
        rvProjectList.setLayoutManager(new LinearLayoutManager(this));

        //Space List Recycle Code
        rvSpaceList = findViewById(R.id.rVSpaceName);

        List<String> SpaceList = new ArrayList<>();
        SpaceList.add("Hello, RecyclerView");
        SpaceList.add("Hello, RecyclerView");
        SpaceList.add("Hello, RecyclerView");

        spaceListAdapter = new SpaceListAdapter(this, SpaceList);
        rvSpaceList.setAdapter(spaceListAdapter);
        rvSpaceList.setLayoutManager(new LinearLayoutManager(this));

    }
}