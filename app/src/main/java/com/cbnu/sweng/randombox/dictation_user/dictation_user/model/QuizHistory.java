
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizHistory implements Serializable {

    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("quiz_number")
    @Expose
    private Integer quizNumber;
    @SerializedName("quiz_results")
    @Expose
    private List<QuizResult> quizResults = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rectify_count")
    @Expose
    private RectifyCount rectifyCount;
    @SerializedName("average")
    @Expose
    private Double average;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(Integer quizNumber) {
        this.quizNumber = quizNumber;
    }

    public List<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public RectifyCount getRectifyCount() {
      return rectifyCount;
    }
    public void setRectifyCount(RectifyCount rectifyCount) {
    	  this.rectifyCount = rectifyCount;
    }
    public Double getAverage() {
   	   return average;
   	}
    public void setAverage(Double average) {
   	   this.average = average;
    }
}
