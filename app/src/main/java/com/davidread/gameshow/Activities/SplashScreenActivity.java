package com.davidread.gameshow.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.davidread.gameshow.R;

/**
 * This activity class represents a splash screen.
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Called when the activity is initially created. It inflates a splash screen layout and waits
     * two seconds before starting the welcome activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate splash screen layout.
        setContentView(R.layout.activity_splash_screen);

        // Wait two seconds before starting the welcome activity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}