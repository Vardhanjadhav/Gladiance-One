package com.espressif.ui.navigation.ControlBouquet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class RoomServiceFragment extends Fragment {



    public RoomServiceFragment() {
        // Required empty public constructor
    }

    RecyclerView RecycleViewFood;

    private List<Integer> imageVegNonveg;
    private List<String> tvMemuName;
    private List<String> tvMemuPrice;
    private List<Integer> imageMenu;
    private List<String> btnAdd;

    private FoodMenuAdapter adapter;

    ImageView imageViewPlaceOrder;

    NewPlaceOrderFragment newplaceOrderFragment;

    PlaceOrderFragment placeOrderFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_service, container, false);

        imageViewPlaceOrder = view.findViewById(R.id.imageViewPlaceOrder);

        imageViewPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceOrderFragment placeOrderFragment = new PlaceOrderFragment();
                placeOrderFragment.show(getChildFragmentManager(), placeOrderFragment.getTag());

            }
        });



        //Recycle view code
        RecycleViewFood = view.findViewById(R.id.rv_food_menu);

        imageVegNonveg = new ArrayList<>();
        tvMemuName = new ArrayList<>();
        tvMemuPrice = new ArrayList<>();
        imageMenu = new ArrayList<>();
        btnAdd = new ArrayList<>();

        adapter = new FoodMenuAdapter(requireContext(),imageVegNonveg,tvMemuName,tvMemuPrice,imageMenu,btnAdd);

        imageVegNonveg.add(R.drawable.veg_img);
        imageVegNonveg.add(R.drawable.veg_img);
        imageVegNonveg.add(R.drawable.veg_img);
        imageVegNonveg.add(R.drawable.veg_img);
        imageVegNonveg.add(R.drawable.veg_img);
        imageVegNonveg.add(R.drawable.veg_img);


        tvMemuName.add("Chicken Manchow Soup");
        tvMemuName.add("Chicken Dum Biryani (Full)");
        tvMemuName.add("Chicken Manchow Soup");
        tvMemuName.add("Chicken Manchow Soup");
        tvMemuName.add("Chicken Dum Biryani (Full)");
        tvMemuName.add("Chicken Manchow Soup");

        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");
        tvMemuPrice.add("Rs 200");

        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);
        imageMenu.add(R.drawable.biryaniimg);

        btnAdd.add("Add");
        btnAdd.add("Add");
        btnAdd.add("Add");
        btnAdd.add("Add");
        btnAdd.add("Add");
        btnAdd.add("Add");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        RecycleViewFood.setLayoutManager(gridLayoutManager);
        RecycleViewFood.setHasFixedSize(true);
        RecycleViewFood.setAdapter(new FoodMenuAdapter(requireContext(),imageVegNonveg,tvMemuName,tvMemuPrice,imageMenu,btnAdd));


        return view;
    }
}