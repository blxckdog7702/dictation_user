package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SAMSUNG on 2017-08-22.
 */

public class BeforeCheck {
    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("sentence")
    @Expose
    private String sentence;

    @SerializedName("submittedanswer")
    @Expose
    private String submittedanswer;

    String answer[] = new String[10];

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }

    public void setSentence(String sentence)
    {
        this.sentence = sentence;
    }

    public String getSentence()
    {
        return sentence;
    }

    public void setSubmittedanswer(String submittedanswer)
    {
        this.submittedanswer = submittedanswer;
    }

    public String getSubmittedanswer()
    {
        return submittedanswer;
    }

}
