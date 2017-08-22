package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 2017-08-21.
 */

public class Grade implements Serializable {

    private int questionNumber;
    private boolean correct;
    private int score;
    private ArrayList<String[]> rectify;
    private String question;

    public boolean isCorrect() {
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

    public ArrayList<String[]> getRectify() {
        return rectify;
    }

    public void setRectify(ArrayList<String[]> rectify) {
        this.rectify = rectify;
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
}
