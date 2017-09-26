package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.Util;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.CandWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.Help;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWord;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuNlpSpeller;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamResultDetailedPage extends AppCompatActivity {

    QuizResult quizResult;
    ArrayList<Question> questions;
    String CorrectAnswer = "";
    @BindView(R.id.tvTable1) TextView tvTable1;
    @BindView(R.id.tvTable2) TextView tvTable2;
    @BindView(R.id.tvTable3) TextView tvTable3;
    @BindView(R.id.tvStudentAnswer) TextView tvStudentAnswer;
    @BindView(R.id.tvCorrectAnswer) TextView tvCorrectAnswer;
    @BindView(R.id.tvCandWord) TextView tvCandWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_exam_result_detailed_page);

        ButterKnife.bind(this);

        tvTable1.setPaintFlags(tvTable1.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        tvTable2.setPaintFlags(tvTable1.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        tvTable3.setPaintFlags(tvTable1.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        Intent intent = getIntent();
        quizResult = (QuizResult) intent.getSerializableExtra("quizResult");
        questions = (ArrayList<Question>) intent.getSerializableExtra("questionsList");
        int questionNumber = intent.getIntExtra("questionNumber", 0);

        for(int i = 0; i < 10; i++){
            if(questions.get(i).getNumber() == questionNumber){
                CorrectAnswer = questions.get(i).getSentence();
                tvCorrectAnswer.setText(CorrectAnswer);
                break;
            }
        }

        for(QuestionResult questionResult : quizResult.getQuestionResult()){
            if(questionResult.getQuestionNumber() == questionNumber){
                String candWord = questionResult.getSubmittedAnswer();
                String submittedAnswer = questionResult.getSubmittedAnswer();

                SpannableStringBuilder candWordBuilder = new SpannableStringBuilder(candWord);
                SpannableStringBuilder studentAnswerBuilder = new SpannableStringBuilder(submittedAnswer);

                PnuNlpSpeller rectify = questionResult.getRectify();
                if(rectify != null){
                    for(PnuErrorWordList pnuErrorWordList : rectify.getPnuErrorWordList()){
                        System.out.println("reapeat : " + pnuErrorWordList.getRepeat());
                        System.out.println("error : " + pnuErrorWordList.getError().getMsg());
                        if(pnuErrorWordList.getError().getMsg().equals("PASS")){
                            for(PnuErrorWord pnuErrorWord : pnuErrorWordList.getPnuErrorWord()){
                                Help help = pnuErrorWord.getHelp();
                                CandWordList candWordList = pnuErrorWord.getCandWordList();
                                System.out.println("help.getNCorrectMethod() : " + help.getNCorrectMethod());
                                System.out.println("getCandWord :  " + pnuErrorWord.getCandWordList().getCandWord()[0]);
                                System.out.println("getOrgStr : " + pnuErrorWord.getOrgStr());
                                System.out.println("RIndex : " + Util.getInstance().getIndexOfDifference(pnuErrorWord.getCandWordList().getCandWord()[0],
                                        pnuErrorWord.getOrgStr()));

                                if(help.getNCorrectMethod() == 0){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#000000")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 1){
                                    int replaceIndex = Util.getInstance().getIndexOfDifference(pnuErrorWord.getCandWordList().getCandWord()[0],
                                                                                                pnuErrorWord.getOrgStr());
//                                    candWordBuilder.replace(replaceIndex, replaceIndex + 1, "˅");
//                                    candWordBuilder.setSpan(
//                                            new ForegroundColorSpan(Color.parseColor("#FF0000")), replaceIndex,
//                                            replaceIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#1DDB16")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 2){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#FF0000")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 3){
                                    int replaceIndex = Util.getInstance().getIndexOfDifference(pnuErrorWord.getCandWordList().getCandWord()[0],
                                                                                                pnuErrorWord.getOrgStr());
                                    candWordBuilder.replace(replaceIndex, replaceIndex + 1, "︵");
                                    candWordBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#FF0000")), replaceIndex,
                                            replaceIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#1DDB16")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 4){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#FF0000")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 5){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#5F00FF")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 6){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#1DDB16")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 7){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#5F00FF")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 8){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#5F00FF")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 9){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#5F00FF")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                else if(help.getNCorrectMethod() == 10){
                                    studentAnswerBuilder.setSpan(
                                            new ForegroundColorSpan(Color.parseColor("#5F00FF")), pnuErrorWord.getM_nStart(),
                                            pnuErrorWord.getM_nEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                        }
                        else{
                            Log.e("WordList.getError() : ", "NOT NULL");
                        }
                    }
                }
                else{
                    Log.e("getRectify()", "NULL");
                }
                tvStudentAnswer.setText(studentAnswerBuilder);
                tvCandWord.setText(candWordBuilder);
            }
        }
    }
}
