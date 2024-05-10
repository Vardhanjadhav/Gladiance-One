package com.espressif.ui.fragment.MyProfile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class WifiIntegrationFragment extends Fragment {



    public WifiIntegrationFragment() {
        // Required empty public constructor
    }

    Button starButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wifi_integration, container, false);

        starButton = view.findViewById(R.id.starButton);
        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CheckConnectionFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.WifiConnection_fragment, fragment).addToBackStack(null)
                        .commit();
            }
        });

        Spinner dropdownSpinner = view.findViewById(R.id.dropdownSpinner1);

        // Create a list of items for the dropdown
        List<String> items = new ArrayList<>();
        items.add("ABC");
        items.add("XYZ");
        items.add("ABC");

        // Create a custom adapter with your custom layout for dropdown items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.dropdown_item1,
                R.id.text1,
                items
        );

        // Set the adapter for the dropdown spinner
        dropdownSpinner.setAdapter(adapter);

        return view;
    }
}