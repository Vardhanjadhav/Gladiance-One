package com.espressif.ui.navigation.Home;

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


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }
    private RecyclerView recycleViewButton,mRecycleView;

    private List<String> titles1;

    private List<String> titles;
    private List<Integer> mImages;

    private HomeAdapter homeAdapter;
    private HomeFavoriteAdapter homeFavoriteAdapter;
    private ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Recycle View (Button)Code
        // imageView = view.findViewById(R.id.ivLeft);
        recycleViewButton = view.findViewById(R.id.rv_RoomList);
        homeAdapter = new HomeAdapter(requireContext(),titles1);

        titles1 = new ArrayList<>();

        titles1.add("Favourite");
        titles1.add("Bedroom");
        titles1.add("Living Room");
        titles1.add("Bedroom 2");
        titles1.add("All Room 6");
        titles1.add("Living Room 2");

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(),1, GridLayoutManager.HORIZONTAL,false);
        recycleViewButton.setLayoutManager(gridLayoutManager1);
        recycleViewButton.setHasFixedSize(true);

        recycleViewButton.setAdapter(new HomeAdapter(requireContext(),titles1));

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int totalItemCount = recycleViewButton.getAdapter().getItemCount();
//                if (totalItemCount <= 0) return;
//                int lastVisibleItemIndex = gridLayoutManager1.findLastVisibleItemPosition();
//                if (lastVisibleItemIndex >= totalItemCount) return;
//                gridLayoutManager1.smoothScrollToPosition(recycleViewButton,null,3);
//
//            }
//        });


        //Recycle View (cardView)Code
        mRecycleView = view.findViewById(R.id.recycleview);

        homeFavoriteAdapter = new HomeFavoriteAdapter(requireContext(),titles,mImages);

        titles = new ArrayList<>();
        mImages = new ArrayList<>();

        homeFavoriteAdapter = new HomeFavoriteAdapter(requireContext(),titles,mImages);

        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);
        mImages.add(R.drawable.sun__white);


        titles.add("Good Morning");
        titles.add("Welcome");
        titles.add("Sunny");
        titles.add("Vacation");
        titles.add("Good Morning");
        titles.add("Welcome");
        titles.add("Sunny");
        titles.add("Vacation");
        titles.add("Good Morning");
        titles.add("Welcome");
        titles.add("Sunny");
        titles.add("Vacation");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(gridLayoutManager);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(new HomeFavoriteAdapter(requireContext(),titles,mImages));

        return view;
    }
}