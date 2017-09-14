package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.util.ArrayMap;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Grade;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 2017-08-21.
 */

public class Grader {

    ArrayList<Grade> result;
    PusanSpellChecker pusanSpellChecker;
    private int score = 100;

    public ArrayList<Grade> execute(ArrayList<ArrayMap<String, String>> qnas)
    {
        result = new ArrayList<Grade>();
        pusanSpellChecker = new PusanSpellChecker();

        for(ArrayMap<String, String> qna : qnas){
            String questionNumber = qna.get("questionNumber");
            final String question = qna.get("question");
            final String SubmittedAnswer = qna.get("SubmittedAnswer");
            Grade gradeResult = new Grade();

            if(question.equals(SubmittedAnswer)){
                gradeResult.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeResult.setCorrect(true);
                gradeResult.setRectify(null);
                gradeResult.setQuestion(question);
                gradeResult.setSubmittedAnswer(SubmittedAnswer);
            }
            else{
                gradeResult.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeResult.setCorrect(false);
                gradeResult.setQuestion(question);
                gradeResult.setSubmittedAnswer(SubmittedAnswer);

                try {
                    gradeResult.setRectify(pusanSpellChecker.execute(SubmittedAnswer));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                score -= 10;
            }

            if(gradeResult.getQuestionNumber() == 10){
                gradeResult.setScore(score);
            }
            result.add(gradeResult);
        }
        return result;
    }
}
