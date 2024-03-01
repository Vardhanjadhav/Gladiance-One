package com.espressif.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.espressif.ui.activities.EspMainActivity;
import com.espressif.ui.navigation.Home.HomeFragment;
import com.espressif.ui.navigation.NavBarActivity;
import com.espressif.wifi_provisioning.R;

import java.util.UUID;

public class OTPVerificationActivity extends AppCompatActivity {

    Button btnOtpSend;
    String userId;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        btnOtpSend = findViewById(R.id.btn_otp_send);

        btnOtpSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTPVerificationActivity.this, NavBarActivity.class);
                startActivity(intent);
            }
        });

        //Link String Click Code
        String completeText = getString(R.string.Resent);

        TextView textView = findViewById(R.id.textViewResendOTP);


        // Create a SpannableString
        SpannableString spannableString = new SpannableString(completeText);

        // Find the start and end indices of the placeholders
        int startIndex = completeText.indexOf("Resend here");
        int endIndex = startIndex + 11; // The length of "%1$s"


        // Create a ClickableSpan for the first link
        ClickableSpan firstLinkSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }
        };

        // Set the ClickableSpan for the second link
        spannableString.setSpan(firstLinkSpan, startIndex, endIndex, 0);


        textView.setText(spannableString);

        // Make the links clickable
        textView.setMovementMethod(LinkMovementMethod.getInstance());

//        //Generate a GUID Code with SharedPreferences
//        btnOtpSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Generate unique ID
//                String GUID = UUID.randomUUID().toString();
//
//                // Save the generated ID
//                saveUserId(GUID);
//
//                // Start next activity
//                startActivity(new Intent(OTPVerificationActivity.this, NavBarActivity.class));
//            }
//        });
//    }
//
//    private void saveUserId(String GUID) {
//        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putString(USER_ID_KEY, GUID);
//        editor.apply();
//    }
//
//    public static String getUserId(SharedPreferences sharedPreferences) {
//        return sharedPreferences.getString(USER_ID_KEY, null);
//    }
    }
}