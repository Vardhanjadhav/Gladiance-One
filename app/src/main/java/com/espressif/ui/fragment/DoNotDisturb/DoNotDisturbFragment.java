package com.espressif.ui.fragment.DoNotDisturb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.espressif.wifi_provisioning.R;


public class DoNotDisturbFragment extends Fragment {


    public DoNotDisturbFragment() {
        // Required empty public constructor
    }

    TextView textView;
    Switch aSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do_not_disturb, container, false);

        textView = view.findViewById(R.id.privacy);
        aSwitch = view.findViewById(R.id.switchButtonDimmer);

        textView.setText(aSwitch.isChecked() ? "Privacy Mode Activated" : "Privacy Mode Deactivated");

        // Listen for switch state changes
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update TextView based on switch state
                textView.setText(isChecked ? "Privacy Mode Activated" : "Privacy Mode Deactivated");

            }
        });

        return view;
    }
}