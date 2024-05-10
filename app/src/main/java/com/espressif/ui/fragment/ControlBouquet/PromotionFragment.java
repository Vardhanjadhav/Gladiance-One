package com.espressif.ui.fragment.ControlBouquet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.ui.adapters.PromotionAdapter;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class PromotionFragment extends Fragment {



    public PromotionFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerViewPromotion;
    private List<Integer> imgProm;
    private List<String> txtProm;
    private PromotionAdapter adapterPromotion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);

        recyclerViewPromotion = view.findViewById(R.id.rv_promotion);

        adapterPromotion = new PromotionAdapter(requireContext(),imgProm,txtProm);

        imgProm = new ArrayList<>();
        txtProm = new ArrayList<>();


        imgProm.add(R.drawable.promotion_img);
        imgProm.add(R.drawable.promotion_img);
        imgProm.add(R.drawable.promotion_img);

        txtProm.add("Get ready to indulge in your favorite street foods from various cities and states of India at the Streets of India Food Festival.Get ready to indulge in your favorite street foods from various cities and states of India at the Streets of India Food Festival.");
        txtProm.add("Get ready to indulge in your favorite street foods from various cities and states of India at the Streets of India Food Festival.");
        txtProm.add("Get ready to indulge in your favorite street foods from various cities and states of India at the Streets of India Food Festival.");



        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        recyclerViewPromotion.setLayoutManager(gridLayoutManager);
        recyclerViewPromotion.setHasFixedSize(true);
        recyclerViewPromotion.setAdapter(new PromotionAdapter(requireContext(),imgProm,txtProm));

        return view;
    }
}