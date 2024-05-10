package com.espressif.ui.activities.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.espressif.wifi_provisioning.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignUp;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;

    private boolean passwordShowing = false;
    private boolean rePasswordShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), OTPVerificationActivity.class);
                startActivity(intent);
            }
        });

        String completeText = getString(R.string.TermsAndCondition);

        TextView textView = findViewById(R.id.tvPrivacyPolice);

        // Create a SpannableString
        SpannableString spannableString = new SpannableString(completeText);

        // Find the start and end indices of the placeholders
        int startIndex = completeText.indexOf("Privacy Police");
        int endIndex = startIndex + 14; // The length of "%1$s"

        int startIndex2 = completeText.indexOf("User Agreement");
        int endIndex2 = startIndex2 + 14;

        // Create a ClickableSpan for the first link
        ClickableSpan firstLinkSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click action for the first link

            }
        };

        // Set the ClickableSpan for the first link
        spannableString.setSpan(firstLinkSpan, startIndex, endIndex, 0);

        // Create a ClickableSpan for the first link
        ClickableSpan secondLinkSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click action for the first link
            }
        };
        spannableString.setSpan(secondLinkSpan, startIndex2, endIndex2, 0);

        // Set the SpannableString to the TextView
        textView.setText(spannableString);

        // Make the links clickable
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        //second string
        String completeText2 = getString(R.string.Login);

        TextView textViewLogin = findViewById(R.id.tVLogin);

        SpannableString spannableString2 = new SpannableString(completeText2);

        // Find the start and end indices of the placeholders
        int startIndex3 = completeText2.indexOf("Login");
        int endIndex3 = startIndex3 + 5; // The length of "%1$s"

        // Create a ClickableSpan for the first link
        ClickableSpan thirdLinkSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        };

        // Set the ClickableSpan for the first link
        spannableString2.setSpan(thirdLinkSpan, startIndex3, endIndex3, 0);

        textViewLogin.setText(spannableString2);

        // Make the links clickable
        textViewLogin.setMovementMethod(LinkMovementMethod.getInstance());


        //Google sing up code

        name = findViewById(R.id.eTName);
        email = findViewById(R.id.eTEmail);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        //Password Icon code
        final EditText editTextPassword = findViewById(R.id.eTPassword);
        final ImageView passwordIcon = findViewById(R.id.iVPasswordShow);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking if password is showing or not
                if(passwordShowing){
                    passwordShowing =false;

                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show);
                }
                else{
                    passwordShowing =true;

                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide);
                }

                editTextPassword.setSelection(editTextPassword.length());
            }
        });

        //Password Icon code
        final EditText editReTextPassword = findViewById(R.id.eTRePassword);
        final ImageView RePasswordIcon = findViewById(R.id.iVRePasswordShow);

        RePasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking if password is showing or not
                if(rePasswordShowing){
                    rePasswordShowing =false;

                    editReTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    RePasswordIcon.setImageResource(R.drawable.show);
                }
                else{
                    rePasswordShowing =true;

                    editReTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    RePasswordIcon.setImageResource(R.drawable.hide);
                }

                editReTextPassword.setSelection(editReTextPassword.length());
            }
        });
    }
}