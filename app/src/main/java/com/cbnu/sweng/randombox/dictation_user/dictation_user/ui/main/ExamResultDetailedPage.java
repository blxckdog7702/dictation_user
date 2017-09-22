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
                String submittedAnswer = questionResult.getSubmittedAnswer();
                tvCandWord.setText(submittedAnswer);

                SpannableStringBuilder studentAnswerBuilder = new SpannableStringBuilder(submittedAnswer);
                SpannableStringBuilder candWordBuilder = new SpannableStringBuilder(submittedAnswer);

                List<ArrayMap<String, String>> rectifies = questionResult.getRectify();
                Log.d("DetailedPage/R : ", String.valueOf(rectifies.size()));
                for(ArrayMap<String, String> rectify : rectifies){
                    int nCorrectMethod = Integer.parseInt(rectify.get("nCorrectMethod"));
                    int start = Integer.parseInt(rectify.get("m_nStart"));
                    int end = Integer.parseInt(rectify.get("m_nEnd"));
                    String CandWord = rectify.get("CandWord");
                    System.out.println("nCorrectMethod : " + nCorrectMethod);
                    Log.d("nCorrectMethod : ", String.valueOf(nCorrectMethod));

                    if(nCorrectMethod == 0){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 1){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 2){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        candWordBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    else if(nCorrectMethod == 3){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#5F00FF")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 4){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 6, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 5){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#5F00FF")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 6){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1DDB16")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 7){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#5F00FF")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 8){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#5F00FF")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 9){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#5F00FF")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                    else if(nCorrectMethod == 10){
                        studentAnswerBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1DDB16")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        System.out.println("nCorrectMethod : " + nCorrectMethod);
                    }
                }
                tvCandWord.setText(candWordBuilder);
                tvStudentAnswer.setText(studentAnswerBuilder);
            }

        }
    }
}
