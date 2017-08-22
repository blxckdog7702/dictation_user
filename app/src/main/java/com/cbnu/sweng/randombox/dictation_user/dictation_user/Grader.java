package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Grade;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 2017-08-21.
 */

public class Grader {

    ArrayList<Grade> result;
    NaverSpellChecker naverSpellCheckParser;
    private int score = 100;

    public void excute(ArrayList<String[]> qnas)
    {
        result = new ArrayList<Grade>();
        naverSpellCheckParser = new NaverSpellChecker();

        for(String[] qna : qnas){
            String questionNumber = qna[0];
            final String question = qna[1];
            final String answer = qna[2];
            Grade gradeResult = new Grade();

            if(question.equals(answer)){
                gradeResult.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeResult.setCorrect(true);
                gradeResult.setRectify(new ArrayList<String[]>()
                {
                    {
                       add(new String[]{"black", answer});
                     }
                });
                gradeResult.setQuestion(question);
            }
            else{
                gradeResult.setQuestionNumber(Integer.parseInt(questionNumber));
                gradeResult.setCorrect(false);
                gradeResult.setQuestion(question);

                try {
                    gradeResult.setRectify((naverSpellCheckParser.execute(answer).get()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                score -= 10;
            }

            if(gradeResult.getQuestionNumber() == 10){
                gradeResult.setScore(score);
            }
            result.add(gradeResult);
        }
    }
}
