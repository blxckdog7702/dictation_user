package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable{

    public volatile static Student student;
    private Student(){}

    public static Student getInstance() {
        if (student == null) {
            synchronized (Student.class) {
                if (student == null) {
                    student = new Student();
                }
            }
        }
        return student;
    }

    public Student(String id, String school, String grade, String _class, int studentid, String name, int v, List<QuizResult> quizResults){
        this.setId(id);
        this.setSchool(school);
        this.setGrade(grade);
        this.setClass_(_class);
        this.setStudentId(studentid);
        this.setName(name);
        this.setV(v);
        this.setQuizResults(quizResults);
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

    @SerializedName("__v")
    @Expose
    private Integer v;

    @SerializedName("teachers")
    @Expose
    private List<Teacher> teachers;

    @SerializedName("quiz_results")
    @Expose
    private List<QuizResult> quizResults = null;

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        Student.student = student;
    }

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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
