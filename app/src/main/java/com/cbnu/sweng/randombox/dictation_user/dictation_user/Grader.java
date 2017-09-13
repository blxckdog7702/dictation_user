package com.cbnu.sweng.randombox.dictation_user.dictation_user;

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

    public ArrayList<Grade> execute(ArrayList<String[]> qnas)
    {
        result = new ArrayList<Grade>();
        pusanSpellChecker = new PusanSpellChecker();

        for(String[] qna : qnas){
            String questionNumber = qna[0];
            final String question = qna[1];
            final String answer = qna[2];
            Grade gradeResult = new Grade();

            if(question.equals(answer)){
                gradeResult.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeResult.setCorrect(true);
                gradeResult.setRectify(null);
                gradeResult.setQuestion(question);
                gradeResult.setSubmittedAnswer(answer);
            }
            else{
                gradeResult.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeResult.setCorrect(false);
                gradeResult.setQuestion(question);
                gradeResult.setSubmittedAnswer(answer);

                try {
                    gradeResult.setRectify(pusanSpellChecker.execute("아 버지가 안 방에 들어가쉰다."));
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
