package com.davidread.gameshow.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.davidread.gameshow.R;

/**
 * This activity class represents a welcome screen. It displays quiz instructions in a text view and
 * has a start button for beginning the quiz.
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Called when the activity is initially created. It inflates a welcome layout and sets a click
     * listener for the start button.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate welcome layout.
        setContentView(R.layout.activity_welcome);

        // Setup click listener for start button.
        Button buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);
    }

    /**
     * Called when the hardware back button is pressed. Is set to do nothing so the user cannot
     * navigate back to the splash screen.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * Handles start button clicks. It starts the quiz activity.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(WelcomeActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
