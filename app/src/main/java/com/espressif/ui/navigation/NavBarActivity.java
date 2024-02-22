package com.espressif.ui.navigation;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.espressif.ui.login.OTPVerificationActivity;
import com.espressif.ui.navigation.ControlBouquet.ControlBouquetHorizontalParentFragment;
import com.espressif.ui.navigation.DoNotDisturb.DoNotDisturbFragment;
import com.espressif.ui.navigation.Home.HomeFragment;
import com.espressif.ui.navigation.MyProfile.MyProfileFragment;
import com.espressif.ui.navigation.RoomControl.RoomControlFragment;
import com.espressif.wifi_provisioning.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class NavBarActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    TextView userIdTextView;

    private static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        bottomNavigation = findViewById(R.id.bottomNavigation);


        replace(new HomeFragment());


        bottomNavigation.show(3, true);


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.privacy));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.smartphone));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.temperature_control));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.my_profile));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()) {
                    case 1:
                        replace(new DoNotDisturbFragment());
                        break;
                    case 2:
                        replace(new RoomControlFragment());
                        break;
                    case 3:
                        replace(new HomeFragment());
                        break;
                    case 4:
                        replace(new ControlBouquetHorizontalParentFragment());
                        break;
                    case 5:
                        replace(new MyProfileFragment());
                        break;
                }

                return null;
            }
        });

        // Retrieve GUID ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String GUID = OTPVerificationActivity.getUserId(sharedPreferences);

        Log.e(TAG, "onCreate: "+ GUID);
        Log.e(TAG, "GUID: "+ GUID);

    }

    private void replace(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,fragment).addToBackStack(null);
        transaction.commit();
    }
}