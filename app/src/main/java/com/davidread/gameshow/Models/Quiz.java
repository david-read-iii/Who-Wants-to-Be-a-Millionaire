package com.davidread.gameshow.Models;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

/**
 * This model class represents a quiz. In terms of member variables, it has an array list of
 * question objects representing questions of the quiz, an int with the total count of questions,
 * an int representing the index the question object that is the current question, and an int
 * representing the number of questions answered correctly. Functions are available to execute quiz
 * functions, such as grading the current question. Functions are also available to get information
 * about the quiz, such as the score, current question, and whether the quiz is complete.
 */
public class Quiz {

    // Model member variables.
    private final ArrayList<Question> questions;
    private final int countTotalQuestions;
    private int indexCurrentQuestion;
    private int countQuestionsCorrect;

    /**
     * Constructs a quiz with an array list of question objects specified.
     */
    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        countTotalQuestions = questions.size();
        indexCurrentQuestion = 0;
        countQuestionsCorrect = 0;
    }

    /**
     * Returns a question object with the current question.
     */
    public Question getCurrentQuestion() {
        return questions.get(indexCurrentQuestion);
    }

    /**
     * Grades the current question and increments the question index. Returns true if the graded
     * question was correct.
     */
    public boolean gradeCurrentQuestion(boolean isOption1Selected, boolean isOption2Selected, boolean isOption3Selected, boolean isOption4Selected) {

        boolean isCurrentQuestionCorrect;

        // Grade the current question.
        isCurrentQuestionCorrect = false;

        if ((isOption1Selected && questions.get(indexCurrentQuestion).isOption1Correct()) ||
                (isOption2Selected && questions.get(indexCurrentQuestion).isOption2Correct()) ||
                (isOption3Selected && questions.get(indexCurrentQuestion).isOption3Correct()) ||
                (isOption4Selected && questions.get(indexCurrentQuestion).isOption4Correct())) {
            isCurrentQuestionCorrect = true;
            countQuestionsCorrect++;
        }

        // Increment the current question index.
        indexCurrentQuestion++;

        return isCurrentQuestionCorrect;
    }

    /**
     * Returns the number of the current question. Starts at 1.
     */
    public int getNumberCurrentQuestion() {
        return indexCurrentQuestion + 1;
    }

    /**
     * Returns the number of total questions in the quiz.
     */
    public int getNumberTotalQuestions() {
        return countTotalQuestions;
    }

    /**
     * Returns the count of correct questions answered.
     */
    public int getCountQuestionsCorrect() {
        return countQuestionsCorrect;
    }

    /**
     * Returns the score of the quiz in currency format. A quiz with one correct question has a
     * score of $50, and every subsequent correct question doubles the score.
     */
    public String getScore() {

        NumberFormat numberFormat;

        // Setup number formatter to show the score in USD.
        numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        numberFormat.setCurrency(Currency.getInstance("USD"));
        numberFormat.setMinimumFractionDigits(0);

        // Calculate the score given the count of questions correct.
        if (countQuestionsCorrect > 1) {

            int score = 50;

            for (int i = 0; i < countQuestionsCorrect - 1; i++)
                score = score * 2;

            return numberFormat.format(score);

        } else if (countQuestionsCorrect == 1)
            return numberFormat.format(50);
        else
            return numberFormat.format(0);
    }

    /**
     * Returns true if the quiz is finished. That is, it returns true if the index of the current
     * question points at a nonexistent question in the array list.
     */
    public boolean isQuizFinished() {
        return indexCurrentQuestion >= countTotalQuestions;
    }

    /**
     * Returns true if all answers of the quiz were answered correctly.
     */
    public boolean isQuizPerfect() {
        return countQuestionsCorrect >= countTotalQuestions;
    }
}