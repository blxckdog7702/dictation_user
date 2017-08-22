
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {

    @SerializedName("number")
    @Expose
    private Integer Number;
    @SerializedName("sentence")
    @Expose
    private String sentence;
    @SerializedName("SubmittedAnswer")
    @Expose
    private String SubmittedAnswer;

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer questionNumber) {
        this.Number = questionNumber;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String answer) {
        this.sentence = answer;
    }


}
