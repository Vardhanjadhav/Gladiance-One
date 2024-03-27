package com.espressif.ui.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.espressif.ui.login.LoginActivity;
import com.espressif.wifi_provisioning.BuildConfig;
import com.espressif.wifi_provisioning.R;
import com.google.android.material.card.MaterialCardView;

public class ConsentActivity extends AppCompatActivity {

    private TextView tvPolicy;
    private MaterialCardView btnProceed;
    private TextView txtProceedBtn;
    private AppCompatCheckBox cbTermsCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent);
        initViews();
    }
    private void initViews() {

      //  tvPolicy = findViewById(R.id.tv_terms_condition);
//        cbTermsCondition = findViewById(R.id.cb_terms_condition);
        btnProceed = findViewById(R.id.btn_proceed);
        txtProceedBtn = findViewById(R.id.text_btn);
//        txtProceedBtn.setText(R.string.btn_proceed);

//        SpannableString stringForPolicy = new SpannableString(getString(R.string.user_agreement));

        ClickableSpan privacyPolicyClick = new ClickableSpan() {

            @Override
            public void onClick(View textView) {
                textView.invalidate();
//                Intent openURL = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.PRIVACY_URL));
//                startActivity(openURL);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
                ds.setUnderlineText(true);
            }
        };

        ClickableSpan termsOfUseClick = new ClickableSpan() {

            @Override
            public void onClick(View textView) {
                textView.invalidate();
//                Intent openURL = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TERMS_URL));
//                startActivity(openURL);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
                ds.setUnderlineText(true);
            }
        };

//        stringForPolicy.setSpan(privacyPolicyClick, 83, 97, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        stringForPolicy.setSpan(termsOfUseClick, 102, 114, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        tvPolicy.setText(stringForPolicy);
        tvPolicy.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tvAppVersion = findViewById(R.id.tv_app_version);

        String version = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String appVersion = getString(R.string.app_version) + " - v" + version;
        tvAppVersion.setText(appVersion);

        btnProceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbTermsCondition.isChecked()) {
                    launchLoginScreen();
                } else {
                    displayConsentError();
                }
            }
        });
    }

    private void launchLoginScreen() {
//        Intent espMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        Intent espMainActivity = new Intent(getApplicationContext(), LoginActivity.class);

        espMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(espMainActivity);
        finish();
    }

    private void displayConsentError() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.dialog_title_error);
//        builder.setMessage(R.string.error_user_agreement);
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        AlertDialog userDialog = builder.create();
        userDialog.show();
    }
}