package com.espressif.ui.fragment.MyProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.espressif.wifi_provisioning.R;


public class IntegrationFragment extends Fragment {

   LinearLayout llVoiceService;

    public IntegrationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_integration, container, false);

        llVoiceService = view.findViewById(R.id.voiceService);

        llVoiceService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new VoiceServicesFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.thirdPartyIntegration, fragment).addToBackStack(null)
                        .commit();

            }
        });


        return view;
    }
}