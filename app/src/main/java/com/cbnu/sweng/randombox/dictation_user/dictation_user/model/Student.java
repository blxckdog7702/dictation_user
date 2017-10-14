package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import android.app.Application;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017-08-14.
 */

public class Student implements Serializable{

    public volatile static Student student;

    public static Student getInstance() {
        if(student == null){
            student = new Student();
        }
        return student;
    }

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("school")
    @Expose
    private String school;

    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("class")
    @Expose
    private String _class;

    @SerializedName("student_id")
    @Expose
    private Integer studentId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("info")
    @Expose
    private String info;

    @SerializedName("__v")
    @Expose
    private Integer v;

    @SerializedName("quiz_results")
    @Expose
    private List<QuizResult> quizResults = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(List<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }

}
