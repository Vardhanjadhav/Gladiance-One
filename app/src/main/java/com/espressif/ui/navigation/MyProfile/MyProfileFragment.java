package com.espressif.ui.navigation.MyProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.espressif.wifi_provisioning.R;


public class MyProfileFragment extends Fragment {



    public MyProfileFragment() {
        // Required empty public constructor
    }
    TextView tv_view_more;
    LinearLayout Project;
    LinearLayout Automation;
    LinearLayout AddDevice;
    LinearLayout subscription;
    LinearLayout Integration;
    LinearLayout Help;
    LinearLayout AboutUs;
    LinearLayout Setting;
    GridLayout gridLayout3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);


        tv_view_more = view.findViewById(R.id.tv_view_more);

        tv_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BasicInfoFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();

            }
        });

        Project = view.findViewById(R.id.Project);
        Project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProjectFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        Automation = view.findViewById(R.id.Automation);
        Automation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AutomationFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        AddDevice = view.findViewById(R.id.AddDevice);
        AddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddDeviceFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        subscription = view.findViewById(R.id.subscription);
        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SubscriptionFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        Integration = view.findViewById(R.id.Integration);
        Integration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new IntegrationFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        Help = view.findViewById(R.id.Help);
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HelpFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        AboutUs = view.findViewById(R.id.AboutUs);
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AboutUsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        Setting = view.findViewById(R.id.Setting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}