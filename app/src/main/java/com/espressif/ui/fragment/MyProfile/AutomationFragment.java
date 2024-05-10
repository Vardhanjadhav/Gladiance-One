package com.espressif.ui.fragment.MyProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.espressif.wifi_provisioning.R;


public class AutomationFragment extends Fragment {



    public AutomationFragment() {
        // Required empty public constructor
    }

    Button setMood;
    LinearLayout llAirConditions;
    LinearLayout llFan;
    LinearLayout llCurtain;
    LinearLayout llLight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_automation, container, false);

        setMood = view.findViewById(R.id.SetMood);
        setMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SetMoodFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.set_mood, fragment).addToBackStack(null)
                        .commit();
            }
        });




        return view;

    }
}