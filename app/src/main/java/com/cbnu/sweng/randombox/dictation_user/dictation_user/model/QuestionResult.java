
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import android.util.ArrayMap;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuNlpSpeller;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionResult implements Serializable {

    @SerializedName("question_number")
    @Expose
    private Integer questionNumber;
    @SerializedName("correct")
    @Expose
    private Boolean correct;
    @SerializedName("rectify")
    @Expose
    private PnuNlpSpeller rectify; //틀린부분
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

    public PnuNlpSpeller getRectify() {
        return rectify;
    }

    public void setRectify(PnuNlpSpeller rectify) {
        this.rectify = rectify;
    }
}
