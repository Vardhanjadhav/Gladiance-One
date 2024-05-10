package com.espressif.ui.fragment.MyProfile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.espressif.wifi_provisioning.R;


public class VoiceServicesFragment extends Fragment {

    private RelativeLayout rlAlexa, rlGva;

    public VoiceServicesFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice_services, container, false);
      //  initViews(view);
        return view;
    }

  //  private void initViews(View view) {
        // Initialize your views here using the inflated view

//        rlAlexa = view.findViewById(R.id.rl_alexa);
//        rlGva = view.findViewById(R.id.rl_gva);
//        rlAlexa.setOnClickListener(alexaClickListener);
//        rlGva.setOnClickListener(gvaClickListener);
//    }

//    private View.OnClickListener alexaClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            {
//
//                String nullStr = "null";
//
//                if ((!TextUtils.isEmpty(BuildConfig.AUTH_URL) && !nullStr.equalsIgnoreCase(BuildConfig.AUTH_URL))
//                        && (!TextUtils.isEmpty(BuildConfig.ALEXA_CLIENT_ID) && !nullStr.equalsIgnoreCase(BuildConfig.ALEXA_CLIENT_ID))
//                        && (!TextUtils.isEmpty(BuildConfig.ALEXA_CLIENT_SECRET) && !nullStr.equalsIgnoreCase(BuildConfig.ALEXA_CLIENT_SECRET))
//                        && (!TextUtils.isEmpty(BuildConfig.ALEXA_RM_CLIENT_ID) && !nullStr.equalsIgnoreCase(BuildConfig.ALEXA_RM_CLIENT_ID))
//                        && (!TextUtils.isEmpty(BuildConfig.ALEXA_REDIRECT_URL) && !nullStr.equalsIgnoreCase(BuildConfig.ALEXA_REDIRECT_URL))
//                        && (!TextUtils.isEmpty(BuildConfig.SKILL_ID) && !nullStr.equalsIgnoreCase(BuildConfig.SKILL_ID))
//                        && (!TextUtils.isEmpty(BuildConfig.SKILL_STAGE) && !nullStr.equalsIgnoreCase(BuildConfig.SKILL_STAGE))
//                        && (!TextUtils.isEmpty(BuildConfig.ALEXA_ACCESS_TOKEN_URL) && !nullStr.equalsIgnoreCase(BuildConfig.ALEXA_ACCESS_TOKEN_URL))) {
//
//                    Intent intent = new Intent(requireContext(), AlexaAppLinkingActivity.class);
//                    startActivity(intent);
//                } else {
//                    String url = "https://rainmaker.espressif.com/docs/3rd-party.html#enabling-alexa";
//                    Intent openURL = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(openURL);
//                }
//            }
//        }
//    };
//
//    private View.OnClickListener gvaClickListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            String url = "https://rainmaker.espressif.com/docs/3rd-party.html#enabling-google-voice-assistant-gva";
//            Intent openURL = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(openURL);
//        }
//    };



}