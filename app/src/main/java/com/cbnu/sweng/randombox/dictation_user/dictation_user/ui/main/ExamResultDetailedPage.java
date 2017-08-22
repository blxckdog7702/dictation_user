package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamResultDetailedPage extends AppCompatActivity {

    QuizResult quizResults;
    @BindView(R.id.tvTable1) TextView tvTable1;
    @BindView(R.id.tvTable2) TextView tvTable2;
    @BindView(R.id.tvTable3) TextView tvTable3;
    @BindView(R.id.tvStudentAnswer) TextView tvStudentAnswer;
    @BindView(R.id.tvAnswerSheet) TextView tvAnswerSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_exam_result_detailed_page);

        ButterKnife.bind(this);

        tvTable1.setPaintFlags(tvTable1.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        tvTable2.setPaintFlags(tvTable1.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        tvTable3.setPaintFlags(tvTable1.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

//        Intent intent = getIntent();
//        quizResults = (QuizResult) intent.getSerializableExtra("OBJECT");
//        int questionNumber = intent.getIntExtra("questionNumber", 0);
//
//        for(QuestionResult questionResult : quizResults.getQuestionResult()){
//
//            if(questionResult.getQuestionNumber() == questionNumber){
//                tvStudentAnswer.setText(questionResult.getSubmittedAnswer());
//                ArrayList<String[]> AnswerSheet = questionResult.getRectify();
//                String result = "";
//                for(String str[] : AnswerSheet){
//                    if(str[0].equals("green")){
//                        result += "<font color=\"##1DDB16\">" + str[1] + "</font>";
//                    }
//                    else if(str[0].equals("red")){
//                        result += "<font color=\"##FF0000\">" + str[1] + "</font>";
//
//                    }
//                    else if(str[0].equals("purple")){
//                        result += "<font color=\"##5F00FF\">" + str[1] + "</font>";
//                    }
//                }
//                tvAnswerSheet.setText(Html.fromHtml(result));
//            }
//
//        }

    }
}
