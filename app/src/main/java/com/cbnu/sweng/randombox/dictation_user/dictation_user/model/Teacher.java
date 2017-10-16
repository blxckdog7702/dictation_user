package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017-09-14.
 */

public class Teacher implements Serializable {

    public volatile static Teacher teacher;
    private Teacher(){}

    public static Teacher getInstance() {
        if (teacher == null) {
            synchronized (Student.class) {
                if (teacher == null) {
                    teacher = new Teacher();
                }
            }
        }
        return teacher;
    }

    public Teacher(String id, String school, String grade, String _class, String loginId, String name, int v, List<QuizResult> quizResults){
        this.setId(id);
        this.setSchool(school);
        this.setGrade(grade);
        this.setClass_(_class);
        this.setLoginId(loginId);
        this.setName(name);
        this.setV(v);
    }

    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("quiz_histories")
    @Expose
    private List<String> quizHistories = null;
    @SerializedName("students")
    @Expose
    private List<String> students = null;

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

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getQuizHistories() {
        return quizHistories;
    }

    public void setQuizHistories(List<String> quizHistories) {
        this.quizHistories = quizHistories;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

}
