package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import java.io.Serializable;

/**
 * Created by user on 2017-08-14.
 */

public class Student implements Serializable {

    public volatile static Student student;
    private String name;
    private String School;
    private String class_name;
    private int id;
    private String type;
    private String result;
    private String user;
    private String grade;

    public static Student getInstance() {
        if(student == null){
            student = new Student();
        }
        return student;
    }

    private Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
