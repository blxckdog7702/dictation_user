package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.util.ArrayMap;
import android.util.Log;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-21.
 */

public class Grader {

    ArrayList<Grade> grades;
    PusanSpellChecker pusanSpellChecker;
    private int score = 100;

    public ArrayList<Grade> execute(ArrayList<ArrayMap<String, String>> qnas)
    {
        grades = new ArrayList<Grade>();
        pusanSpellChecker = new PusanSpellChecker();

        for(ArrayMap<String, String> qna : qnas){
            String questionNumber = qna.get("questionNumber");
            final String question = qna.get("question");
            final String SubmittedAnswer = qna.get("SubmittedAnswer");
            Grade grade = new Grade();

            if(question.equals(SubmittedAnswer)){
                grade.setQuestionNumber(Integer.parseInt(questionNumber));
                grade.setCorrect(true);
                grade.setRectify(null);
                grade.setQuestion(question);
                grade.setSubmittedAnswer(SubmittedAnswer);
            }
            else{
                grade.setQuestionNumber(Integer.parseInt(questionNumber));
                grade.setCorrect(false);
                grade.setQuestion(question);
                grade.setSubmittedAnswer(SubmittedAnswer);

                try {
                    grade.setRectify((List) pusanSpellChecker.execute(SubmittedAnswer));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                score -= 10;
            }

            if(grade.getQuestionNumber() == 10){
                grade.setScore(score);
            }
            Log.d("Grader/R", String.valueOf(grade.getRectify().size()));
            grades.add(grade);
        }
        return grades;
    }
}
