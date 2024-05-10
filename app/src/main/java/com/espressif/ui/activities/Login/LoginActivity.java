package com.espressif.ui.activities.Login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.activities.Home.ProjectSpaceActivity;
import com.espressif.ui.models.LoginRequestModel;
import com.espressif.ui.models.LoginResponseModel;
import com.espressif.wifi_provisioning.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    RelativeLayout relativeLayoutGoogleBtn;
    ImageView googleImg;

    TextView textViewForgotPass;



    private SharedPreferences sharedPreferences2 , sharedPreferences3;

    Context context = this;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String USER_ID_KEY = "userId";

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



        getGUID();

        //Password Icon code
        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking if password is showing or not
                if (passwordShowing) {
                    passwordShowing = false;

                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show);
                } else {
                    passwordShowing = true;

                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide);
                }

                editTextPassword.setSelection(editTextPassword.length());
            }
        });


//        btnLogin.setEnabled(false);
//
//        editTextUserId.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//                boolean isReady = editTextUserId.getText().toString().length() > 0;
//                boolean isReady2 = editTextPassword.getText().toString().length() > 0;
//
//                if (isReady && isReady2 == true) {
//                    btnLogin.setBackgroundResource(R.drawable.orange_button_background);
//                    btnLogin.setEnabled(true);
//
//                } else {
//                    btnLogin.setBackgroundResource(R.drawable.transparent_button_background);
//                }
//            }
//
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//        });
//
//        editTextPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//                boolean isReady = editTextUserId.getText().toString().length() > 0;
//                boolean isReady2 = editTextPassword.getText().toString().length() > 0;
//
//                if (isReady && isReady2 == true) {
//                    btnLogin.setBackgroundResource(R.drawable.orange_button_background);
//                    btnLogin.setEnabled(true);
//                }
//                else {
//                    btnLogin.setBackgroundResource(R.drawable.transparent_button_background);
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//        });



        // Retrieve GUID ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String GUID = LoginActivity.getUserId(sharedPreferences);

        Log.e(TAG, "onCreate: "+ GUID);
        Log.e(TAG, "GUID: "+ GUID);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add Method For LoginPostReq
                String userId = editTextUserId.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String deviceId = GUID.trim();
                btnLoginRequestClick(userId, password,deviceId);
                // Check if user input is empty
                if (userId.isEmpty() && password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Email ID and Password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (userId.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter Email ID", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                }

                //Intent intent = new Intent(getApplicationContext(), ProjectSpaceActivity.class);
                //startActivity(intent);
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


    //Login Post Req Method Code
    public void btnLoginRequestClick(String userId, String password,String deviceId) {
        // Create an instance of ApiService
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        // Prepare login request
        LoginRequestModel loginRequest = new LoginRequestModel(userId, password, deviceId);

        // Make API call
        Call<LoginResponseModel> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    LoginResponseModel loginResponse = response.body();
                    if (loginResponse != null && loginResponse.isSuccessful()) {
                        // Handle successful response
                        Log.d("LoginResponse", "Successful: " + loginResponse.isSuccessful());
                        Log.d("LoginResponse", "Message: " + loginResponse.getMessage());
                        Log.d("LoginResponse", "LoginToken: " + loginResponse.getLoginToken());
                        Log.d("LoginResponse", "UserTypes: " + loginResponse.getUserTypes().toString());
                        Log.d("LoginResponse", "UserDisplayName: " + loginResponse.getUserDisplayName());

                        Log.e(TAG, "onResponse: "+ loginResponse.getLoginToken());
                        String retrievedLoginToken = loginResponse.getLoginToken();
                        saveLoginToken(retrievedLoginToken);

                        String retrievedUserDisplayName = loginResponse.getUserDisplayName();
                        saveUserDisplayName(retrievedUserDisplayName);

                        Intent intent = new Intent(getApplicationContext(), ProjectSpaceActivity.class);
                        startActivity(intent);
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("LoginResponse", "Unsuccessful: " + response.message());
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                // Handle failure
                Log.e("LoginResponse", "Failure: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Please check Email Id and Password", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveUserDisplayName(String userDisplayName) {
        sharedPreferences3 = getSharedPreferences("MyPreferencesDN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences3.edit();
        editor.putString("UserDisplayName", userDisplayName);
        Log.e(TAG, "UserDisplayName1: "+userDisplayName );
        editor.apply();
    }

    private void saveLoginToken(String loginDeviceId) {
        sharedPreferences2 = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.putString("LoginToken", loginDeviceId);
        Log.e(TAG, "save LoginToken: "+loginDeviceId );
        editor.apply();
    }


    //Generate a GUID Code with SharedPreferences Project GUID/LoginDeviceId:
    public void getGUID(){
        String GUID = UUID.randomUUID().toString();
        // Save the generated ID
        Log.e(TAG, "getGUID/LoginDeviceId: "+GUID );
        saveUserId(GUID);
    }

    private void saveUserId(String GUID) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString(USER_ID_KEY, GUID);
        editor.apply();
    }

    public static String getUserId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(USER_ID_KEY, null);
    }

    //
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
    @Override
    public void onBackPressed() {
        finishAffinity(); // Close the app when back button is pressed
    }
}
