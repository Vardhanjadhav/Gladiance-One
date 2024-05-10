package com.espressif.ui.fragment.MyProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.wifi_provisioning.R;


public class AboutUsFragment extends Fragment {


    private static final String PREFS_NAME = "MyPrefsFile";

    public AboutUsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

//        // Retrieve GUID ID from SharedPreferences
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        String GUID = OTPVerificationActivity.getUserId(sharedPreferences);
//
//        Log.e(TAG, "onCreate: "+ GUID);
//        Log.e(TAG, "About Us GUID: "+ GUID);

        return view;
    }
}