package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import android.util.ArrayMap;
import android.util.Log;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.GradeModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.PusanSpellChecker;

import java.util.ArrayList;

public class Grader {

    ArrayList<GradeModel> gradeModels;
    PusanSpellChecker pusanSpellChecker;
    private int score = 100;

    public ArrayList<GradeModel> execute(ArrayList<ArrayMap<String, String>> qnas)
    {
        gradeModels = new ArrayList<GradeModel>();
        pusanSpellChecker = new PusanSpellChecker();

        for(ArrayMap<String, String> qna : qnas){
            Log.e("asdasd : ", ""+qnas.size());
            String questionNumber = qna.get("questionNumber");
            final String question = qna.get("question");
            final String SubmittedAnswer = qna.get("SubmittedAnswer");
            GradeModel gradeModel = new GradeModel();

            if(question.equals(SubmittedAnswer)){
                gradeModel.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeModel.setCorrect(true);
                gradeModel.setRectify(null);
                gradeModel.setQuestion(question);
                gradeModel.setSubmittedAnswer(SubmittedAnswer);
            }
            else{
                gradeModel.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeModel.setCorrect(false);
                gradeModel.setQuestion(question);
                gradeModel.setSubmittedAnswer(SubmittedAnswer);

                try {
                    gradeModel.setRectify(pusanSpellChecker.execute(SubmittedAnswer));

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Grader", e.getMessage());
                }

                score -= 10;
            }

            if(gradeModel.getQuestionNumber() == 10){
                gradeModel.setScore(score);
            }
            gradeModels.add(gradeModel);
        }
        return gradeModels;
    }
}
