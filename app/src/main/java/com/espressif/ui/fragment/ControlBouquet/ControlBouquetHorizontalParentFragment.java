package com.espressif.ui.fragment.ControlBouquet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.ui.adapters.ControlBouquetHorizontalAdapter;
import com.espressif.ui.fragment.RoomControl.DoorLockFragment;
import com.espressif.ui.fragment.RoomControl.MoodsFragment;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class ControlBouquetHorizontalParentFragment extends Fragment implements ControlBouquetHorizontalAdapter.OnItemClickListener {


    public ControlBouquetHorizontalParentFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewControlBouquetHorizontal;
    private ControlBouquetHorizontalAdapter controlBouquetHorizontalAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control_bouquet_horizontal_parent, container, false);
        recyclerViewControlBouquetHorizontal = view.findViewById(R.id.recycleViewControlBouquetHorizontal);
        recyclerViewControlBouquetHorizontal.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize your adapter and set it to the RecyclerView
        controlBouquetHorizontalAdapter = new ControlBouquetHorizontalAdapter(getData(), this);
        recyclerViewControlBouquetHorizontal.setAdapter(controlBouquetHorizontalAdapter);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerViewControlBouquetHorizontal.setLayoutManager(gridLayoutManager1);
        recyclerViewControlBouquetHorizontal.setHasFixedSize(true);


        return view;
    }

    private List<String> getData() {
        // Replace this with your data source
        List<String> data = new ArrayList<>();
        data.add("Housekeeping");
        data.add("Room Service");
        data.add("Laundry");
        data.add("Bill View");
        data.add("Feedback");
        data.add("Hotel Info");
        data.add("Promotion");
        data.add("Messaging");
        data.add("Door Lock");
        data.add("Emergency");
        data.add("Door Camera");
        return data;
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click, replace fragment, etc.
        replaceFragmentBasedOnPosition(position);
    }

    private void replaceFragmentBasedOnPosition(int position) {
        Fragment newFragment;
        switch (position) {
            case 0:
                newFragment = new HousekeepingFragment();
                break;
            case 1:
                newFragment = new RoomServiceFragment();
                break;
            case 2:
                newFragment = new LaundryFragment();
                break;
            case 3:
                newFragment = new BillViewFragment();
                break;
            case 4:
                newFragment = new FeedbackFragment();
                break;
            case 5:
                newFragment = new HotelInfoFragment();
                break;
            case 6:
                newFragment = new PromotionFragment();
                break;
            case 7:
                newFragment = new MessagingFragment();
                break;
            case 8:
                newFragment = new DoorLockFragment();
                break;
            case 9:
                newFragment = new EmergencyFragment();
                break;
            case 10:
                newFragment = new DoorCameraFragment();
                break;

            default:
                newFragment = new MoodsFragment();
        }

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayoutControlBouquet, newFragment)
                .addToBackStack(null)
                .commit();
    }
}