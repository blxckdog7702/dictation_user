package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseDrawerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ReadyPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.RecordManagerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectExamOrPractice extends BaseDrawerActivity {

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @BindView(R.id.btPractice) Button btPractice;
    @OnClick(R.id.btPractice)
    void onClickBtPractice(){
        Util.getInstance().moveActivity(this, SelectPracticeTypeActivity.class);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @BindView(R.id.btExam) Button btExam;
    @OnClick(R.id.btExam)
    void onClickBtExam(){
        setting = getSharedPreferences("setting", MODE_PRIVATE);
        editor = setting.edit();// editor가져오기

        if(TextUtils.isEmpty(setting.getString("myStudentId", ""))){
            TastyToast.makeText(getApplicationContext(), "로그인을 해야 합니다.", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
        }
        else {
            Util.getInstance().moveActivity(this, ReadyPage.class);
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
        }

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
