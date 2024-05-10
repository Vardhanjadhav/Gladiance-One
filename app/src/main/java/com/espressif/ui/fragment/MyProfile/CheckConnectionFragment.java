package com.espressif.ui.fragment.MyProfile;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.espressif.wifi_provisioning.R;

import java.util.Objects;


public class CheckConnectionFragment extends Fragment {



    public CheckConnectionFragment() {
        // Required empty public constructor
    }

    Button doneButton,btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_connection, container, false);

        doneButton = view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                showCustomDialogBox(message);

            }

            private void showCustomDialogBox(String message) {

                final Dialog dialog = new Dialog(requireContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.wifi_conection_save_dialog);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                btnSave = dialog.findViewById(R.id.btnSave);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Fragment fragment = new MyDeviceFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.CheckConnectionFragment, fragment).addToBackStack(null)
                                .commit();
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }

        });

        return view;
    }
}