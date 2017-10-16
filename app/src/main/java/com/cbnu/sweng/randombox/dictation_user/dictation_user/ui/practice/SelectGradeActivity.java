package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.os.Bundle;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseDrawerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectGradeActivity extends BaseDrawerActivity {

    @OnClick(R.id.btGrade1)
    void btGrade1() {
        Util.getInstance().moveActivity(this, SelectClassActivity.class, 1);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btGrade2)
    void btGrade2() {
        Util.getInstance().moveActivity(this, SelectClassActivity.class, 2);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btGrade3)
    void btGrade3() {
        Util.getInstance().moveActivity(this, SelectClassActivity.class, 3);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btGrade4)
    void btGrade4() {
        Util.getInstance().moveActivity(this, SelectClassActivity.class, 4);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grade);

        ButterKnife.bind(this);
    }

}
