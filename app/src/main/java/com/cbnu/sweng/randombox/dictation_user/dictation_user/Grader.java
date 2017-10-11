package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.util.ArrayMap;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.GradeModel;

import java.util.ArrayList;

/**
 * Created by user on 2017-08-21.
 */

public class Grader {

    ArrayList<GradeModel> gradeModels;
    PusanSpellChecker pusanSpellChecker;
    private int score = 100;

    public ArrayList<GradeModel> execute(ArrayList<ArrayMap<String, String>> qnas)
    {
        gradeModels = new ArrayList<GradeModel>();
        pusanSpellChecker = new PusanSpellChecker();

        for(ArrayMap<String, String> qna : qnas){
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
