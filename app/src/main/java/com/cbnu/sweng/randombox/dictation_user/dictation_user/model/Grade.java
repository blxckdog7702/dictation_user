package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017-08-21.
 */

public class Grade implements Serializable {

    private int questionNumber;
    private boolean correct;
    private int score;
    private ArrayList<ArrayMap<String, String>> rectify;
    private String question;
    private String SubmittedAnswer;

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getSubmittedAnswer() {
        return SubmittedAnswer;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        SubmittedAnswer = submittedAnswer;
    }

    public ArrayList<ArrayMap<String, String>> getRectify() {
        return rectify;
    }

    public void setRectify(ArrayList<ArrayMap<String, String>> rectify) {
        this.rectify = rectify;
    }
}
