package com.espressif.ui.navigation.ControlBouquet;

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


public class EmergencyFragment extends Fragment {



    public EmergencyFragment() {
        // Required empty public constructor
    }
    RecyclerView RecycleViewEmergency;

    private List<String> tvName;
    private List<Integer> imagecall;
    private List<String> tvnumber;
    private List<Integer> imagemap;
    private List<Integer> imagelogo;

    private EmergencyAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);

        RecycleViewEmergency = view.findViewById(R.id.rv_emergency);


        tvName = new ArrayList<>();
        imagecall = new ArrayList<>();
        tvnumber = new ArrayList<>();
        imagemap = new ArrayList<>();
        imagelogo = new ArrayList<>();

        adapter = new EmergencyAdapter(requireContext(),tvName,imagecall,tvnumber,imagemap,imagelogo);

        tvName.add("Police");
        tvName.add("Fire Extinguisher");
        tvName.add("Police");
        tvName.add("Police");
        tvName.add("Police");
        tvName.add("Police");

        imagecall.add(R.drawable.telephone);
        imagecall.add(R.drawable.telephone);
        imagecall.add(R.drawable.telephone);
        imagecall.add(R.drawable.telephone);
        imagecall.add(R.drawable.telephone);
        imagecall.add(R.drawable.telephone);


        tvnumber.add("101");
        tvnumber.add("112");
        tvnumber.add("101");
        tvnumber.add("101");
        tvnumber.add("101");
        tvnumber.add("101");

        imagemap.add(R.drawable.map);
        imagemap.add(R.drawable.map);
        imagemap.add(R.drawable.map);
        imagemap.add(R.drawable.map);
        imagemap.add(R.drawable.map);
        imagemap.add(R.drawable.map);


        imagelogo.add(R.drawable.emergancy1);
        imagelogo.add(R.drawable.emergancy1);
        imagelogo.add(R.drawable.emergancy1);
        imagelogo.add(R.drawable.emergancy1);
        imagelogo.add(R.drawable.emergancy1);
        imagelogo.add(R.drawable.emergancy1);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        RecycleViewEmergency.setLayoutManager(gridLayoutManager);
        RecycleViewEmergency.setHasFixedSize(true);
        RecycleViewEmergency.setAdapter(new EmergencyAdapter(requireContext(),tvName,imagecall,tvnumber,imagemap,imagelogo));

        return view;

    }
}