
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import android.util.ArrayMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionResult implements Serializable {

    @SerializedName("question_number")
    @Expose
    private Integer questionNumber;
    @SerializedName("correct")
    @Expose
    private Boolean correct;
    @SerializedName("rectify")
    @Expose
    private ArrayList<ArrayMap<String, String>> rectify; //틀린부분
    @SerializedName("submitted_answer")
    @Expose
    private String submittedAnswer;

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public ArrayList<ArrayMap<String, String>> getRectify() {
        return rectify;
    }

    public void setRectify(ArrayList<ArrayMap<String, String>> rectify) {
        this.rectify = rectify;
    }
}
