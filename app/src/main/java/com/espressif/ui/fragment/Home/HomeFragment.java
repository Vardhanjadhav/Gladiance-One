package com.espressif.ui.fragment.Home;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.adapters.SceneAdapter;
import com.espressif.ui.adapters.SpaceGroupSpinnerAdapter;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.ProjectSpaceGroupResModel;
import com.espressif.ui.models.SpaceGroup;
import com.espressif.ui.models.scenelist.ObjectTag;
import com.espressif.ui.models.scenelist.SceneListResModel;
import com.espressif.wifi_provisioning.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements SpaceGroupSpinnerAdapter.OnAreaSelectedListener {

    TextView textViewProjectName,textViewUserName;
    Button buttonSpaceGroup;
    Spinner Spinner;
    TextView mood;

    RecyclerView recyclerView;
    private ArrayList<SpaceGroup> arrayList;

    private ArrayList<ObjectTag> arrayList1;
    Context context;

    LinearLayout linearLayout;

    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        textViewProjectName = view.findViewById(R.id.tv_project_name);
        textViewUserName = view.findViewById(R.id.tvUserName);
        Spinner = view.findViewById(R.id.SpaceGroupSpinner);
        mood = view.findViewById(R.id.mood);
        recyclerView = view.findViewById(R.id.recycler_view_sceneList_home);
        //linearLayout = view.findViewById(R.id.favoriteLinearLayout);
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();

        SharedPreferences sharedPreferencesProName = requireActivity().getSharedPreferences("MyPrefsPN", Context.MODE_PRIVATE);
        String ProjectName = sharedPreferencesProName.getString("ProjectName", "");
        textViewProjectName.setText(ProjectName);
        Log.e(TAG, "Home Fragment Project Name : "+ ProjectName );

        SharedPreferences sharedPreferences3 = requireActivity().getSharedPreferences("MyPreferencesDN", Context.MODE_PRIVATE);
        String savedUserDeviceName = sharedPreferences3.getString("UserDisplayName", "");
        textViewUserName.setText("Hello "+savedUserDeviceName);
        Log.e(TAG, "Home Fragment User Name: "+savedUserDeviceName );


        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "Project Space GUID/LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();


        SharedPreferences  sharedPreferences2 = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "Project Space loginToken: "+savedLoginDeviceId );
        String loginToken = savedLoginDeviceId.trim();

        SharedPreferences sharedPreferencesProRef = requireActivity().getSharedPreferences("MyPrefsPR", Context.MODE_PRIVATE);
        String ProjectRef = sharedPreferencesProRef.getString("ProjectRef", "");
        Log.e(TAG, "ProjectSpaceGroupActivity Project Ref : "+ ProjectRef );
        String gAAProjectRef = ProjectRef.trim();

        SharedPreferences  sharedPreferences4 = requireContext().getSharedPreferences("MyPrefsPSTR", MODE_PRIVATE);
        String saveProjectSpaceTypeRef = sharedPreferences4.getString("Project_Space_Type_Ref", "");
        Log.e(TAG, "Project Space Type Ref: "+saveProjectSpaceTypeRef );
        String gaaProjectSpaceTypeRef = saveProjectSpaceTypeRef.trim();

        //For Single Project
        SharedPreferences  sharedPreferencesProjectRef = requireContext().getSharedPreferences("MyPrefsProjectRefOne", MODE_PRIVATE);
        String projectRefOne = sharedPreferencesProjectRef.getString("projectRefOne", "");
        Log.e(TAG, "Project Ref One: "+projectRefOne );

        SharedPreferences  sharedPreferencesProjectName = requireContext().getSharedPreferences("MyPrefsProjectNameOne", MODE_PRIVATE);
        String projectNameOne = sharedPreferencesProjectName.getString("projectNameOne", "");
       // textViewProjectName.setText(projectNameOne);
        Log.e(TAG, "Project Ref Name: "+projectNameOne );


      /* This Method Use For Single Project
      getSpaceGroupName(projectRefOne,loginToken,loginDeviceId);
       */

        getSpaceGroupName(gAAProjectRef,loginToken,loginDeviceId);

        getSceneList(gaaProjectSpaceTypeRef,loginToken,loginDeviceId);

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

                        /*for(ProjectSpaceGroupReqModel.SpaceGroup spaceGroup1 : spaceGroups){
                            Log.e(TAG, "onResponse SpaceGroupName: " + spaceGroup1.getGAAProjectSpaceGroupName());
                            arrayList.add(new SpaceGroup(spaceGroup1.getGAAProjectSpaceGroupRef(),spaceGroup1.getGAAProjectSpaceGroupName(),spaceGroup1.getDisplayOrder()));

                        }*/
                        if(spaceGroups.isEmpty() == false){
                            Log.e(TAG, "SpaceGroup ");
                            if (spaceGroups.size() == 1) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                       // the current activity will get finished.
                                        // Retrieve the selected area reference
                                        String selectedAreaRef = String.valueOf(spaceGroups.get(0).getGAAProjectSpaceGroupRef());
                                        // Pass the value to the click listener interface method

                                        Fragment newFragment = ProjectSpaceLandingFragment.newInstance(getContext(),(selectedAreaRef));
                                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.spaceList, newFragment);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();

                                        Log.e(TAG, "SpaceGroup 11");
                                        dismissSpinnerDropDown(Spinner);
                                    }
                                }, 700);

                            }
                            // Create custom spinner adapter
                            SpaceGroupSpinnerAdapter adapter = new SpaceGroupSpinnerAdapter(requireContext(),Spinner,spaceGroups);

                            // Set the listener for the adapter
                            adapter.setOnAreaSelectedListener(HomeFragment.this); // Replace YourFragmentClassName with the name of your fragment class

                            Spinner.setAdapter(adapter);

                            // Handle item selection
                            Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {

                                }
                            });

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectSpaceGroupResModel> call, Throwable t) {

            }
        });

    }

    private void getSceneList(String gaaProjectSpaceTypeRef,String loginToken,String loginDeviceId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<SceneListResModel> call = apiService.getSceneList(gaaProjectSpaceTypeRef, loginToken, loginDeviceId);

        call.enqueue(new Callback<SceneListResModel>() {
            @Override
            public void onResponse(Call<SceneListResModel> call, Response<SceneListResModel> response) {
                if (response.isSuccessful()) {
                    SceneListResModel sceneListResModel = response.body();
                    if (sceneListResModel != null && sceneListResModel.getSuccessful()) {
                        List<ObjectTag> sceneList = sceneListResModel.getObjectTag();

                        for (ObjectTag objectTag : sceneList) {
                            Log.e(TAG, "onResponse SceneName: " + objectTag.getName());
                            arrayList1.add(new ObjectTag(objectTag.getRef(),objectTag.getName(),objectTag.getgAAProjectRef(),objectTag.getCode(),objectTag.getIsSystemDefinedScene(),objectTag.getgAAProjectSpaceTypeRef(),objectTag.getgAAProjectSpaceTypeName(),objectTag.getgAAProjectName(),objectTag.getConfigurations()));
                        }

                        SceneAdapter spaceAdapter = new SceneAdapter(arrayList1,getContext());
                        recyclerView.setAdapter(spaceAdapter);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(gridLayoutManager1);
                        //If any error change adapter class
                    } else {
                        Log.e("MainActivity", "Unsuccessful response: " + sceneListResModel.getMessage());
                    }
                } else {
                    Log.e("MainActivity", "Failed to get response");
                }
            }

            @Override
            public void onFailure(Call<SceneListResModel> call, Throwable t) {

            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                // Handle back button press here
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
                return true; // Consumes the back button press event
            }
            return false; // Otherwise, let the system handle it
        });
    }


    @Override
    public void onAreaSelected(String selectedAreaRef) {
        Fragment newFragment = ProjectSpaceLandingFragment.newInstance(getContext(),(selectedAreaRef));
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.spaceList, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        dismissSpinnerDropDown(Spinner);
    }

    private void dismissSpinnerDropDown(Spinner spinner) {
        try {
            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
            method.setAccessible(true);
            method.invoke(spinner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
