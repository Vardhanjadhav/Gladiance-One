package com.espressif.ui.navigation.RoomControl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class AirConditioningFragment extends Fragment {


    public AirConditioningFragment() {
        // Required empty public constructor
    }
    private RecyclerView recycleViewAirConditioning;
    private List<String> titles;
    private List<Integer> ImagesAirConditioning;

    private AirConditioningAdapter airConditioningAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_air_conditioning, container, false);

        //Recycle View (cardView)Code
        recycleViewAirConditioning = view.findViewById(R.id.AirConditioningRecycleView);

        airConditioningAdapter = new AirConditioningAdapter(requireContext(),titles,ImagesAirConditioning);

        titles = new ArrayList<>();
        ImagesAirConditioning = new ArrayList<>();

        airConditioningAdapter = new AirConditioningAdapter(requireContext(),titles,ImagesAirConditioning);

        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");
        titles.add("Air Conditioning");

        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);
        ImagesAirConditioning.add(R.drawable.aircondition);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false);
        recycleViewAirConditioning.setLayoutManager(gridLayoutManager);
        recycleViewAirConditioning.setHasFixedSize(true);
        recycleViewAirConditioning.setAdapter(new AirConditioningAdapter(requireContext(),titles,ImagesAirConditioning));

        return view;
    }
}