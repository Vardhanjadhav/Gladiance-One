package com.espressif.ui.fragment.RoomControl;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.espressif.ui.adapters.RoomControlAdapter;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class RoomControlFragment extends Fragment implements RoomControlAdapter.OnItemClickListener {

    private RecyclerView recyclerViewRoomControl;
    private RoomControlAdapter adapterRoomControl;

    Button bottomsheetlight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_control, container, false);

        recyclerViewRoomControl = view.findViewById(R.id.recycleViewRoomControl);
        recyclerViewRoomControl.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize your adapter and set it to the RecyclerView
        adapterRoomControl = new RoomControlAdapter(getData(), this);
        recyclerViewRoomControl.setAdapter(adapterRoomControl);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerViewRoomControl.setLayoutManager(gridLayoutManager1);
        recyclerViewRoomControl.setHasFixedSize(true);

        //Bedroom Click

        LinearLayout linearLayout = view.findViewById(R.id.ll_1);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private List<String> getData() {
        // Replace this with your data source
        List<String> data = new ArrayList<>();
        data.add("Moods");
        data.add("Lights");
        data.add("Air Conditioner");
        data.add("Curtain");
        data.add("Ceiling Fan");
        data.add("Smart TV");
        data.add("Door Lock");
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
                newFragment = new MoodsFragment();
                break;
            case 1:
                newFragment = new LightsFragment();
                break;
            case 2:
                newFragment = new AirConditioningFragment();
                break;
            case 3:
                newFragment = new CurtainsFragment();
                break;
            case 4:
                newFragment = new CeilingFanFragment();
                break;
            case 5:
                newFragment = new SmartTVFragment();
                break;
            case 6:
                newFragment = new DoorLockFragment();
                break;

            default:
                newFragment = new MoodsFragment();
        }

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayoutRoomControl, newFragment)
                .addToBackStack(null)
                .commit();
    }

    //Down Pop up Show Dialog Code
    private void showDialog() {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        TextView textView1 = dialog.findViewById(R.id.tvbedroom1);
        TextView textView2 = dialog.findViewById(R.id.tvbedroom2);
        TextView textView3 = dialog.findViewById(R.id.tvbedroom3);
        TextView textView4 = dialog.findViewById(R.id.tvbedroom4);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Click Bedroom", Toast.LENGTH_SHORT).show();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Click Living Room", Toast.LENGTH_SHORT).show();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Click Master Bedroom", Toast.LENGTH_SHORT).show();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Click Bathroom", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}