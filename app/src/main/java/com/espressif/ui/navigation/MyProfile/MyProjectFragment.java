package com.espressif.ui.navigation.MyProfile;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class MyProjectFragment extends Fragment {



    public MyProjectFragment() {
        // Required empty public constructor
    }

    RecyclerView RecycleViewMyProject;

    private List<String> tvProject;
    private List<String> tvProjectName;
    private List<String> tvSpace;
    private List<String> tvSpaceName;
    private List<Integer> imgView;
    private List<Integer> imgDelete;

    private MyProjectAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_project, container, false);


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
                Log.d(TAG, "handleOnBackPressed: ");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        //Recycle view code
        RecycleViewMyProject = view.findViewById(R.id.recycleViewMyProject);

        tvProject = new ArrayList<>();
        tvProjectName = new ArrayList<>();
        tvSpace = new ArrayList<>();
        tvSpaceName = new ArrayList<>();
        imgView = new ArrayList<>();
        imgDelete = new ArrayList<>();

        adapter = new MyProjectAdapter(requireContext(),tvProject,tvProjectName,tvSpace,tvSpaceName,imgView,imgDelete);


        tvProject.add("Project :");
        tvProject.add("Project :");
        tvProject.add("Project :");
        tvProject.add("Project :");

        tvProjectName.add("ABC");
        tvProjectName.add("XYZ");
        tvProjectName.add("ABC");
        tvProjectName.add("XYZ");

        tvSpace.add("Space :");
        tvSpace.add("Space :");
        tvSpace.add("Space :");
        tvSpace.add("Space :");

        tvSpaceName.add("Home");
        tvSpaceName.add("Home");
        tvSpaceName.add("Home");
        tvSpaceName.add("Home");

        imgView.add(R.drawable.project);
        imgView.add(R.drawable.project);
        imgView.add(R.drawable.project);
        imgView.add(R.drawable.project);

        imgDelete.add(R.drawable.delete);
        imgDelete.add(R.drawable.delete);
        imgDelete.add(R.drawable.delete);
        imgDelete.add(R.drawable.delete);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        RecycleViewMyProject.setLayoutManager(gridLayoutManager);
        RecycleViewMyProject.setHasFixedSize(true);
        RecycleViewMyProject.setAdapter(new MyProjectAdapter(requireContext(),tvProject,tvProjectName,tvSpace,tvSpaceName,imgView,imgDelete));



        return view;
    }

}