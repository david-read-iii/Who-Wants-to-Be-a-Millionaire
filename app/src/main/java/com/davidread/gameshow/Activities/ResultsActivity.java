package com.davidread.gameshow.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.davidread.gameshow.R;

/**
 * This activity represents a results screen for the quiz. It's layout has text views displaying the
 * results of the quiz and final score. The finish button restarts the welcome activity.
 */
public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Called when the activity is initially created. It inflates a results layout, gets the state
     * of the finished quiz from the quiz activity, populates the results layout with that state,
     * and sets up a click listener for the finish button.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        String score;
        boolean isQuizPerfect;
        ImageView imageView;
        TextView textViewMessage, textViewScore;
        Button buttonFinish;

        // Inflate results layout.
        setContentView(R.layout.activity_results);

        // Get state of finished quiz from the quiz activity.
        intent = getIntent();
        score = intent.getStringExtra(QuizActivity.EXTRA_SCORE);
        isQuizPerfect = intent.getBooleanExtra(QuizActivity.EXTRA_IS_QUIZ_PERFECT, false);

        // Setup view objects from results layout.
        imageView = findViewById(R.id.image_view_results);
        textViewMessage = findViewById(R.id.text_view_results_message);
        textViewScore = findViewById(R.id.text_view_results_score);

        // If the quiz is perfect, populate the view objects with success messages.
        if (isQuizPerfect) {
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_circle_24));
            textViewMessage.setText(getString(R.string.text_view_results_message_success));
        }

        // If the quiz is imperfect, populate the view objects with failure messages.
        else {
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_cancel_24));
            textViewMessage.setText(getString(R.string.text_view_results_message_failed));
        }

        // Populate text view with the score.
        textViewScore.setText(getString(R.string.text_view_results_score, score));

        // Setup the finish button with a click listener.
        buttonFinish = findViewById(R.id.button_finish);
        buttonFinish.setOnClickListener(this);
    }

    /**
     * Called when the hardware back button is pressed. Is set to do nothing so the user cannot
     * navigate back to the quiz activity.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * Handles finish button clicks. It starts the welcome activity and discards all previous
     * activity history.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
