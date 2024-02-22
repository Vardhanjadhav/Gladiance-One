package com.espressif.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.espressif.ui.navigation.NavBarActivity;
import com.espressif.wifi_provisioning.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    RelativeLayout relativeLayoutGoogleBtn;
    ImageView googleImg;

    //Animation
    //Animation topSlideAnimation;
    //View view;

    TextView textViewForgotPass;


    private boolean passwordShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       //topSlideAnimation = AnimationUtils.loadAnimation(this,R.anim.top_slide);

        TextView textView = findViewById(R.id.TextView);
        final EditText editTextUserId = findViewById(R.id.eTUserId);
        final EditText editTextPassword = findViewById(R.id.eTPassword);
        final ImageView passwordIcon = findViewById(R.id.iVPasswordShow);
        final Button btnLogin = findViewById(R.id.Loginbtn);

        //View view = findViewById(R.id.screen1);

        //Password Icon code
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

        btnLogin.setEnabled(false);

        editTextUserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // Toast.makeText(getApplicationContext(), "Please Enter Username and Password", Toast.LENGTH_SHORT).show();

                boolean isReady = editTextUserId.getText().toString().length() > 2;
                boolean isReady2 = editTextPassword.getText().toString().length() > 2;

                if(isReady && isReady2 == true){
                    enableSubmitIfReady();
                }
            }

            public void enableSubmitIfReady() {

                boolean isReady = editTextUserId.getText().toString().length() > 2;
                boolean isReady2 = editTextPassword.getText().toString().length() > 2;

                btnLogin.setEnabled(isReady && isReady2);

                btnLogin.setBackgroundResource(R.drawable.orange_button_background);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });


        //String Link Click Code
        // Make the links clickable
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        //Second Link
        String completeText2 = getString(R.string.SignUp);

        TextView textViewLogin = findViewById(R.id.textviewSignup);

        SpannableString spannableString2 = new SpannableString(completeText2);

        // Find the start and end indices of the placeholders
        int startIndex3 = completeText2.indexOf("Sign Up");
        int endIndex3 = startIndex3 + 7; // The length of "%1$s"

        // Create a ClickableSpan for the first link
        ClickableSpan thirdLinkSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);

            }
        };

        // Set the ClickableSpan for the first link
        spannableString2.setSpan(thirdLinkSpan, startIndex3, endIndex3, 0);

        textViewLogin.setText(spannableString2);

        // Make the links clickable
        textViewLogin.setMovementMethod(LinkMovementMethod.getInstance());

        //Forgot password code
        textViewForgotPass = findViewById(R.id.tVForgotPass);

        findViewById(R.id.tVForgotPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });






        // Google Sing in Code

        googleImg = findViewById(R.id.googleImg);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        googleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn();
            }
        });

    }

    void singIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }


}
