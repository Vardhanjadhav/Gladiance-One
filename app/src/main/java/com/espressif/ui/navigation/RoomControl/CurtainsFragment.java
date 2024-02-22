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


public class CurtainsFragment extends Fragment {



    public CurtainsFragment() {
        // Required empty public constructor
    }
    private RecyclerView recycleViewCurtails;
    private List<String> titles;
    private List<Integer> ImagesCurtains;

    private CurtainsAdapter curtainsAdapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_curtains, container, false);

        //Recycle View (cardView)Code
        recycleViewCurtails = view.findViewById(R.id.CurtainsRecycleView);

        curtainsAdapter = new CurtainsAdapter(requireContext(),titles,ImagesCurtains);

        titles = new ArrayList<>();
        ImagesCurtains = new ArrayList<>();

        curtainsAdapter = new CurtainsAdapter(requireContext(),titles,ImagesCurtains);

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

        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);
        ImagesCurtains.add(R.drawable.fan_white);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false);
        recycleViewCurtails.setLayoutManager(gridLayoutManager);
        recycleViewCurtails.setHasFixedSize(true);
        recycleViewCurtails.setAdapter(new CurtainsAdapter(requireContext(),titles,ImagesCurtains));

        return view;
    }
}