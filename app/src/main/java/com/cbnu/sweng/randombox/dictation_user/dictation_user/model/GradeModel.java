package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import android.util.ArrayMap;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuNlpSpeller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GradeModel implements Serializable {

    private int questionNumber;
    private boolean correct;
    private int score;
    private PnuNlpSpeller rectify;
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

    public PnuNlpSpeller getRectify() {
        return rectify;
    }

    public void setRectify(PnuNlpSpeller rectify) {
        this.rectify = rectify;
    }
}
