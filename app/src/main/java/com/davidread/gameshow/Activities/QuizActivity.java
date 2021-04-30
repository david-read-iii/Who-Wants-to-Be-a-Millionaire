package com.davidread.gameshow.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.davidread.gameshow.Models.Question;
import com.davidread.gameshow.Models.Quiz;
import com.davidread.gameshow.R;

import java.util.ArrayList;

/**
 * This activity represents every screen with a quiz question. It's layout is set to always show the
 * current question of the quiz. The layout shows information about the current question and the
 * state of the quiz.
 */
public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    // Intent extra constants.
    public static String EXTRA_SCORE = "score";
    public static String EXTRA_IS_QUIZ_PERFECT = "is_quiz_perfect";

    // Activity objects.
    private TextView textViewQuizPosition, textViewScore, textViewQuestion;
    private RadioGroup radioGroupOptions;
    private RadioButton radioButtonOption1, radioButtonOption2, radioButtonOption3, radioButtonOption4;
    private Quiz quiz;

    /**
     * Called when the activity is initially created. It inflates an empty quiz question layout,
     * sets up view objects from the layout, sets the next button click listener, and populates
     * the layout with the first question in the quiz.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate quiz layout.
        setContentView(R.layout.activity_quiz);

        // Setup view objects from quiz layout.
        textViewQuizPosition = findViewById(R.id.text_view_quiz_position);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestion = findViewById(R.id.text_view_question);
        radioGroupOptions = findViewById(R.id.radio_group_options);
        radioButtonOption1 = findViewById(R.id.radio_option_1);
        radioButtonOption2 = findViewById(R.id.radio_option_2);
        radioButtonOption3 = findViewById(R.id.radio_option_3);
        radioButtonOption4 = findViewById(R.id.radio_option_4);

        // Setup next button click listener.
        Button buttonNext = findViewById(R.id.button_next);
        buttonNext.setOnClickListener(this);

        // Setup quiz object.
        quiz = new Quiz(getSampleQuestions());

        // Populate the layout with the first question.
        populateLayoutWithCurrentQuestion();
    }

    /**
     * Called when the hardware back button is pressed. Is set to do nothing so the user cannot
     * navigate back to the welcome activity.
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * Handles next button clicks. It grades the current question. If the quiz is finished or the
     * current question was answered incorrectly, it starts the results activity. Otherwise, it
     * populates the layout with the next question.
     */
    @Override
    public void onClick(View v) {

        // Grade the current question.
        boolean isCurrentQuestionCorrect = quiz.gradeCurrentQuestion(
                radioButtonOption1.isChecked(),
                radioButtonOption2.isChecked(),
                radioButtonOption3.isChecked(),
                radioButtonOption4.isChecked()
        );

        // If the quiz is finished or the question was answered incorrectly, start the results activity.
        if (quiz.isQuizFinished() || !isCurrentQuestionCorrect) {
            Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
            intent.putExtra(EXTRA_SCORE, quiz.getScore());
            intent.putExtra(EXTRA_IS_QUIZ_PERFECT, quiz.isQuizPerfect());
            startActivity(intent);
        }

        // Otherwise, populate the layout with the new current question.
        else {
            populateLayoutWithCurrentQuestion();
        }
    }

    /**
     * Returns an array list of sample question objects.
     */
    private ArrayList<Question> getSampleQuestions() {

        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "How many time zones are there in Russia?",
                "13",
                "11",
                "9",
                "7",
                false,
                true,
                false,
                false
        ));
        questions.add(new Question(
                "What country has the most islands in the world?",
                "United States",
                "Indonesia",
                "Sweden",
                "Philippines",
                false,
                false,
                true,
                false
        ));
        questions.add(new Question(
                "When did they open the London underground?",
                "1863",
                "1866",
                "1869",
                "1860",
                true,
                false,
                false,
                false
        ));
        questions.add(new Question(
                "Where is Billie Eilish from?",
                "Los Angeles",
                "Nashville",
                "Omaha",
                "Denton",
                true,
                false,
                false,
                false
        ));
        questions.add(new Question(
                "Where was the first modern Olympic Games held?",
                "Paris, France",
                "St. Louis, United States",
                "Berlin, Germany",
                "Athens, Greece",
                false,
                false,
                false,
                true
        ));
        questions.add(new Question(
                "When was Netflix founded?",
                "1997",
                "2001",
                "2009",
                "2015",
                true,
                false,
                false,
                false
        ));
        questions.add(new Question(
                "What’s the national flower of Japan?",
                "Rose of Sharon",
                "Plum blossom",
                "Cherry blossom",
                "Lotus",
                false,
                false,
                true,
                false
        ));
        questions.add(new Question(
                "What’s the smallest country in the world?",
                "Tuvalu",
                "The Vatican",
                "The Republic of Nauru",
                "Japan",
                false,
                true,
                false,
                false
        ));
        questions.add(new Question(
                "Name the best-selling book series of the 21st century?",
                "Who Moved My Cheese?",
                "Harry Potter",
                "Don Quixote",
                "The Brethren",
                false,
                true,
                false,
                false
        ));
        questions.add(new Question(
                "Who invented the World Wide Web?",
                "Tim Berners-Lee",
                "Linus Torvalds",
                "Bill Gates",
                "Robert Kahn",
                true,
                false,
                false,
                false
        ));
        questions.add(new Question(
                "What city do The Beatles come from?",
                "Liverpool",
                "Birmingham",
                "Glasgow",
                "Manchester",
                true,
                false,
                false,
                false
        ));
        questions.add(new Question(
                "Which football team is known as ‘The Red Devils’?",
                "LA Galaxy",
                "Manchester United",
                "FC Cincinnati",
                "New Jersey Devils",
                false,
                true,
                false,
                false
        ));
        questions.add(new Question(
                "What was the most-watched series on Netflix in 2020?",
                "Emily in Paris",
                "The Umbrella Academy",
                "Lucifer",
                "The Queen's Gambit",
                false,
                false,
                false,
                true
        ));
        questions.add(new Question(
                "How many stripes are there on the US flag?",
                "11",
                "13",
                "50",
                "3",
                false,
                true,
                false,
                false
        ));
        questions.add(new Question(
                "What’s the capital of Canada?",
                "Toronto",
                "Ottawa",
                "Montreal",
                "Vancouver",
                false,
                true,
                false,
                false
        ));
        questions.add(new Question(
                "Name Pixar’s first feature-length movie?",
                "Toy Story",
                "Monsters Inc.",
                "A Bug's Life",
                "Snow White",
                true,
                false,
                false,
                false
        ));

        return questions;
    }

    /**
     * Populates the quiz layout with the attributes of the current question and the current state
     * of the quiz. This includes updating the quiz position, the score, the current question,
     * the current question options, and clearing the radio buttons.
     */
    private void populateLayoutWithCurrentQuestion() {

        // Get the current question from the quiz object.
        Question currentQuestion = quiz.getCurrentQuestion();

        // Populate the layout with attributes of the current question and state of the quiz.
        textViewQuizPosition.setText(getString(R.string.action_bar_label_quiz, quiz.getNumberCurrentQuestion(), quiz.getNumberTotalQuestions()));
        textViewScore.setText(getString(R.string.action_bar_label_score, quiz.getScore()));
        textViewQuestion.setText(currentQuestion.getQuestion());
        radioGroupOptions.clearCheck();
        radioButtonOption1.setText(currentQuestion.getOption1());
        radioButtonOption2.setText(currentQuestion.getOption2());
        radioButtonOption3.setText(currentQuestion.getOption3());
        radioButtonOption4.setText(currentQuestion.getOption4());
    }
}
