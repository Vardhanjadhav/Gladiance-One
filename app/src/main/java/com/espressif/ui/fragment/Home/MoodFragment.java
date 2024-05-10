package com.espressif.ui.fragment.Home;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.scenelist.ObjectTag;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;


public class MoodFragment extends Fragment {



    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    RecyclerView recyclerView;

    private ArrayList<ObjectTag>arrayList;


    public MoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mood, container, false);


        recyclerView = view.findViewById(R.id.recycler_view_sceneList);

        arrayList = new ArrayList<>();

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);
        Log.e(TAG, "SceneList LoginDeviceId: "+ GUID);
        String loginDeviceId = GUID.trim();

        //Login Token
        SharedPreferences  sharedPreferences2 = requireContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedLoginToken = sharedPreferences2.getString("LoginToken", "");
        Log.e(TAG, "SceneList Login Token: "+savedLoginToken );
        String loginToken = savedLoginToken.trim();


        SharedPreferences  sharedPreferences4 = requireContext().getSharedPreferences("MyPrefsPSTR", MODE_PRIVATE);
        String saveProjectSpaceTypeRef = sharedPreferences4.getString("Project_Space_Type_Ref", "");
        Log.e(TAG, "Project Space Type Ref: "+saveProjectSpaceTypeRef );
        String gaaProjectSpaceTypeRef = saveProjectSpaceTypeRef.trim();


        //getSceneList(gaaProjectSpaceTypeRef,loginToken,loginDeviceId);



        return view;
    }


//    private void getSceneList(String gaaProjectSpaceTypeRef,String loginToken,String loginDeviceId) {
//        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//        Call<SceneListResModel> call = apiService.getSceneList(gaaProjectSpaceTypeRef, loginToken, loginDeviceId);
//
//        call.enqueue(new Callback<SceneListResModel>() {
//            @Override
//            public void onResponse(Call<SceneListResModel> call, Response<SceneListResModel> response) {
//                if (response.isSuccessful()) {
//                    SceneListResModel sceneListResModel = response.body();
//                    if (sceneListResModel != null && sceneListResModel.getSuccessful()) {
//                        List<ObjectTag> sceneList = sceneListResModel.getObjectTag();
//
//                        for (ObjectTag objectTag : sceneList) {
//                            Log.e(TAG, "onResponse SceneName: " + objectTag.getName());
//                            arrayList.add(new ObjectTag(objectTag.getRef(),objectTag.getName(),objectTag.getGAAProjectRef(),objectTag.getCode(),objectTag.getIsSystemDefinedScene(),objectTag.getGAAProjectSpaceTypeRef(),objectTag.getGAAProjectSpaceTypeName(),objectTag.getGAAProjectName(),objectTag.getConfigurations()));
//                        }
//
//                        SceneAdapter spaceAdapter = new SceneAdapter(arrayList);
//                        recyclerView.setAdapter(spaceAdapter);
//                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false);
//                        recyclerView.setLayoutManager(gridLayoutManager1);
//                        //If any error change adapter class
//                    } else {
//                        Log.e("MainActivity", "Unsuccessful response: " + sceneListResModel.getMessage());
//                    }
//                } else {
//                    Log.e("MainActivity", "Failed to get response");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SceneListResModel> call, Throwable t) {
//
//            }
//        });
//
//    }

}