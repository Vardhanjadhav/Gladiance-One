package com.espressif.ui.navigation.MyProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.espressif.wifi_provisioning.R;


public class SetMoodFragment extends Fragment {



    public SetMoodFragment() {
        // Required empty public constructor
    }

    Button setScheduling;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_mood, container, false);

        setScheduling = view.findViewById(R.id.scheduling);
        setScheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AutomationFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.fragment_scheduling, fragment).addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }
}