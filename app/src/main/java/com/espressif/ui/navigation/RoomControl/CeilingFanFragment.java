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


public class CeilingFanFragment extends Fragment {



    public CeilingFanFragment() {
        // Required empty public constructor
    }
    private RecyclerView recycleViewCeiling;
    private List<String> titles;
    private List<Integer> ImagesCeilingFan;

    private CeilingFanAdapter ceilingFanAdapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ceiling_fan, container, false);

        //Recycle View (cardView)Code
        recycleViewCeiling = view.findViewById(R.id.CeilingFanRecycleView);

        ceilingFanAdapter = new CeilingFanAdapter(requireContext(),titles,ImagesCeilingFan);

        titles = new ArrayList<>();
        ImagesCeilingFan = new ArrayList<>();

        ceilingFanAdapter = new CeilingFanAdapter(requireContext(),titles,ImagesCeilingFan);

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

        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);
        ImagesCeilingFan.add(R.drawable.fan_white);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false);
        recycleViewCeiling.setLayoutManager(gridLayoutManager);
        recycleViewCeiling.setHasFixedSize(true);
        recycleViewCeiling.setAdapter(new CeilingFanAdapter(requireContext(),titles,ImagesCeilingFan));

        return view;
    }
}