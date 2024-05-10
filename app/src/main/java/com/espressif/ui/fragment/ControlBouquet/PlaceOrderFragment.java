package com.espressif.ui.fragment.ControlBouquet;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.ui.adapters.PlaceOrderAdapter;
import com.espressif.wifi_provisioning.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFragment extends BottomSheetDialogFragment {


    public PlaceOrderFragment() {
        // Required empty public constructor
    }

    RecyclerView RecycleViewPlaceOrder;

    private List<Integer> imageVegNonveg;
    private List<String> tvMemuName;
    private List<String> tvMemuPrice;
    private List<String> tvtype;
    private List<Integer> imageMenu;

    private PlaceOrderAdapter adapterPlaceOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_order, container, false);

        RecycleViewPlaceOrder = view.findViewById(R.id.rv_place_order);

        imageVegNonveg = new ArrayList<>();
        tvMemuName = new ArrayList<>();
        tvMemuPrice = new ArrayList<>();
        tvtype = new ArrayList<>();
        imageMenu = new ArrayList<>();

        adapterPlaceOrder = new PlaceOrderAdapter(requireContext(),imageVegNonveg,tvMemuName,tvMemuPrice,tvtype,imageMenu);

        imageVegNonveg.add(R.drawable.nonveg_img);
        imageVegNonveg.add(R.drawable.nonveg_img);
        imageVegNonveg.add(R.drawable.nonveg_img);



        tvMemuName.add("Chicken Dum Biryani");
        tvMemuName.add("Chicken Dum Biryani");
        tvMemuName.add("Chicken Manchow Soup");


        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");


        tvtype.add("Dinner");
        tvtype.add("Dinner");
        tvtype.add("Dinner");

        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);



        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        RecycleViewPlaceOrder.setLayoutManager(gridLayoutManager);
        RecycleViewPlaceOrder.setHasFixedSize(true);
        RecycleViewPlaceOrder.setAdapter(new PlaceOrderAdapter(requireContext(),imageVegNonveg,tvMemuName,tvMemuPrice,tvtype,imageMenu));

        return view;
    }


}