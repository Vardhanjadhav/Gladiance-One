package com.espressif.ui.fragment.RoomControl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.espressif.wifi_provisioning.R;


public class DoorLockFragment extends Fragment {

    ImageView imageView;
    TextView textView;
    private boolean isUnlocked = false;
    public DoorLockFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_door_lock, container, false);

        imageView = view.findViewById(R.id.imgLook);
        textView = view.findViewById(R.id.tvTap);

        // Set click listener for imgLook
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle unlocked status
                isUnlocked = !isUnlocked;

                // Change text and image accordingly
                if (isUnlocked) {
                    imageView.setImageResource(R.drawable.doorlock);
                    textView.setText("Tap To Door Unlock");
                } else {
                    imageView.setImageResource(R.drawable.doorunlock);
                    textView.setText("Tap to Door Lock");
                }
            }
        });

        return view;
    }
}