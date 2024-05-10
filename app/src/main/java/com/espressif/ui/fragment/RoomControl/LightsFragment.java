package com.espressif.ui.fragment.RoomControl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.ui.adapters.LightAdapter;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class LightsFragment extends Fragment {



    public LightsFragment() {
        // Required empty public constructor
    }
    private RecyclerView recycleViewLight;

    private List<String> titles;
    private List<Integer> ImagesLight;
    private List<Integer> ImageMore;

    private LightAdapter lightAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lights, container, false);

        //Recycle View (cardView)Code
        recycleViewLight = view.findViewById(R.id.lightRecycleView);

        lightAdapter = new LightAdapter(requireContext(),titles,ImagesLight,ImageMore);

        titles = new ArrayList<>();
        ImagesLight = new ArrayList<>();
        ImageMore = new ArrayList<>();

        lightAdapter = new LightAdapter(requireContext(),titles,ImagesLight,ImageMore);

        titles.add("Bed Left");
        titles.add("Bed Right");
        titles.add("Bed Right");
        titles.add("Bed Left");
        titles.add("Bed Right");
        titles.add("Bed Left");
        titles.add("Bed Right");
        titles.add("Bed Left");
        titles.add("Bed Right");
        titles.add("Bed Left");
        titles.add("Bed Right");
        titles.add("Bed Left");

        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);
        ImagesLight.add(R.drawable.lighting);


        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);
        ImageMore.add(R.drawable.more);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false);
        recycleViewLight.setLayoutManager(gridLayoutManager);
        recycleViewLight.setHasFixedSize(true);
        recycleViewLight.setAdapter(new LightAdapter(requireContext(),titles,ImagesLight,ImageMore));
        return view;
    }
}