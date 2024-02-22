package com.espressif.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.window.SplashScreen;

import com.espressif.wifi_provisioning.R;

public class SplashScreenActivity extends AppCompatActivity {

    Animation a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        a = AnimationUtils.loadAnimation(this,R.anim.top_slide);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.top_slide,0);
                finish();
            }
        }, 1000);
    }
}