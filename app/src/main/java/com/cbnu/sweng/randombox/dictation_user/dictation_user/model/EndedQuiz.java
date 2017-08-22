
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EndedQuiz {

    @SerializedName("quiz_history_id")
    @Expose
    private String quizHistoryId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("quiz_results")
    @Expose
    private QuizResult quizResult;

    public String getQuizHistoryId() {
        return quizHistoryId;
    }

    public void setQuizHistoryId(String quizHistoryId) {
        this.quizHistoryId = quizHistoryId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public QuizResult getQuizResult() {
        return quizResult;
    }

    public void setQuizResult(QuizResult quizResult) {
        this.quizResult = quizResult;
    }

}
