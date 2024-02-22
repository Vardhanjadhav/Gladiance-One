package com.espressif.ui.navigation.MyProfile;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.espressif.ui.login.LoginActivity;
import com.espressif.wifi_provisioning.R;

import java.util.Objects;


public class BasicInfoFragment extends Fragment {



    public BasicInfoFragment() {
        // Required empty public constructor
    }

    LinearLayout llChangePassword;
    LinearLayout llInviteUser;
    LinearLayout llJoinUser;
    LinearLayout llLogout;
    LinearLayout editProfile;

    TextView tvMessage;

    Button btnYes,btnNo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_info, container, false);

        llChangePassword = view.findViewById(R.id.llChangePassword);
        llChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChangePasswordFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.basic_info_frameLayout, fragment).addToBackStack(null)
                        .commit();
            }
        });

        llInviteUser = view.findViewById(R.id.llInviteUser);
        llInviteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new InviteUserFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.basic_info_frameLayout, fragment).addToBackStack(null)
                        .commit();
            }
        });

        llJoinUser = view.findViewById(R.id.llJoinUser);
        llJoinUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new JoinUserFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.basic_info_frameLayout, fragment).addToBackStack(null)
                        .commit();
            }
        });

        llLogout = view.findViewById(R.id.llLogout);


        editProfile = view.findViewById(R.id.editProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EditProfileFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.basic_info_frameLayout, fragment).addToBackStack(null)
                        .commit();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
                Log.d(TAG, "handleOnBackPressed: ");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Are you sure you want to log out from gladiance";
                showCustomDialogBox(message);
            }

            private void showCustomDialogBox(String message) {

                final Dialog dialog = new Dialog(requireContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layout_custom_dailog);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                tvMessage = dialog.findViewById(R.id.tvMessage);
                btnYes = dialog.findViewById(R.id.btn_Yes);
                btnNo = dialog.findViewById(R.id.btn_No);

                tvMessage.setText(message);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });

        return view;
    }

}