package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ExamResultDetailedPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ReadyPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectExamOrPractice extends AppCompatActivity {

    @BindView(R.id.btPractice) Button btPractice;
    @OnClick(R.id.btPractice)
    void onClickBtPractice(){
        Util.getInstance().moveActivity(this, ReadyPage.class);
    }
    @BindView(R.id.btExam) Button btExam;
    @OnClick(R.id.btExam)
    void onClickBtExam(){
        Util.getInstance().moveActivity(this, SelectPracticeTypeActivity.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exam_or_practice);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
            Util.getInstance().onBackPressed(this);
    }
}
