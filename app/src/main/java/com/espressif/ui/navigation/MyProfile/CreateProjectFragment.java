package com.espressif.ui.navigation.MyProfile;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class CreateProjectFragment extends Fragment {



    public CreateProjectFragment() {
        // Required empty public constructor
    }

    private Spinner customSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_project, container, false);

        Spinner dropdownSpinner = view.findViewById(R.id.customSpinner);

        // Create a list of items for the dropdown
        List<String> items = new ArrayList<>();
        items.add("Home");
        items.add("Hotel");
        items.add("Office");
        items.add("Hospital");
        items.add("Other");

        // Create a custom adapter with your custom layout for dropdown items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.dropdown_item1,
                R.id.text1,
                items
        );

        // Set the adapter for the dropdown spinner
        dropdownSpinner.setAdapter(adapter);


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
                Log.d(TAG, "handleOnBackPressed: ");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        return view;
    }

}

