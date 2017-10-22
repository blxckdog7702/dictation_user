package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseDrawerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.lid.lib.LabelButtonView;
import com.lid.lib.LabelView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectClassActivity extends BaseActivity {

    @BindView(R.id.btClass1) LabelButtonView btClass1;
    @BindView(R.id.btClass2) LabelButtonView btClass2;
    @BindView(R.id.btClass3) LabelButtonView btClass3;
    @BindView(R.id.btClass4) LabelButtonView btClass4;
    @BindView(R.id.btClass5) LabelButtonView btClass5;
    @BindView(R.id.btClass6) LabelButtonView btClass6;
    @BindView(R.id.btClass7) LabelButtonView btClass7;
    @BindView(R.id.btClass8) LabelButtonView btClass8;
    @BindView(R.id.btClass9) LabelButtonView btClass9;
    @BindView(R.id.btClass10) LabelButtonView btClass10;
    @BindView(R.id.btClass11) LabelButtonView btClass11;
    @BindView(R.id.btClass12) LabelButtonView btClass12;
    @BindView(R.id.btClass13) LabelButtonView btClass13;
    @BindView(R.id.btClass14) LabelButtonView btClass14;
    @BindView(R.id.btClass15) LabelButtonView btClass15;


    @OnClick(R.id.btClass1)
    void btClass1() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "1");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass2)
    void btClass2() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "2");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass3)
    void btClas3() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "3");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass4)
    void btClass4() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "4");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass5)
    void btClass5() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "5");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass6)
    void btClass6() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "6");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass7)
    void btClass7() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "7");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass8)
    void btClass8() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "8");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass9)
    void btClass9() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "9");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass10)
    void btClass10() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "10");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass11)
    void btClass11() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "11");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass12)
    void btClass12() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "12");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass13)
    void btClass13() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "13");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass14)
    void btClass14() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "14");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    @OnClick(R.id.btClass15)
    void btClass15() {
        Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentGrade + "-" + "15");
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
    }
    int studentGrade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        ButterKnife.bind(this);
        btClass1.setLabelText("미완료");
        btClass1.setLabelDistance(20);
        btClass1.setLabelTextColor(Color.WHITE);
        btClass1.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass1.setLabelVisual(true);
        btClass2.setLabelText("미완료");
        btClass2.setLabelDistance(20);
        btClass2.setLabelTextColor(Color.WHITE);
        btClass2.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass2.setLabelVisual(true);
        btClass3.setLabelText("미완료");
        btClass3.setLabelDistance(20);
        btClass3.setLabelTextColor(Color.WHITE);
        btClass3.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass3.setLabelVisual(true);
        btClass4.setLabelText("미완료");
        btClass4.setLabelDistance(20);
        btClass4.setLabelTextColor(Color.WHITE);
        btClass4.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass4.setLabelVisual(true);
        btClass5.setLabelText("미완료");
        btClass5.setLabelDistance(20);
        btClass5.setLabelTextColor(Color.WHITE);
        btClass5.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass5.setLabelVisual(true);
        btClass6.setLabelText("미완료");
        btClass6.setLabelDistance(20);
        btClass6.setLabelTextColor(Color.WHITE);
        btClass6.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass6.setLabelVisual(true);
        btClass7.setLabelText("미완료");
        btClass7.setLabelDistance(20);
        btClass7.setLabelTextColor(Color.WHITE);
        btClass7.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass7.setLabelVisual(true);
        btClass8.setLabelText("미완료");
        btClass8.setLabelDistance(20);
        btClass8.setLabelTextColor(Color.WHITE);
        btClass8.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass8.setLabelVisual(true);
        btClass9.setLabelText("미완료");
        btClass9.setLabelDistance(20);
        btClass9.setLabelTextColor(Color.WHITE);
        btClass9.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass9.setLabelVisual(true);
        btClass10.setLabelText("미완료");
        btClass10.setLabelDistance(20);
        btClass10.setLabelTextColor(Color.WHITE);
        btClass10.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass10.setLabelVisual(true);
        btClass11.setLabelText("미완료");
        btClass11.setLabelDistance(20);
        btClass11.setLabelTextColor(Color.WHITE);
        btClass11.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass11.setLabelVisual(true);
        btClass12.setLabelText("미완료");
        btClass12.setLabelDistance(20);
        btClass12.setLabelTextColor(Color.WHITE);
        btClass12.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass12.setLabelVisual(true);
        btClass13.setLabelText("미완료");
        btClass13.setLabelDistance(20);
        btClass13.setLabelTextColor(Color.WHITE);
        btClass13.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass13.setLabelVisual(true);
        btClass14.setLabelText("미완료");
        btClass14.setLabelDistance(20);
        btClass14.setLabelTextColor(Color.WHITE);
        btClass14.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass14.setLabelVisual(true);
        btClass15.setLabelText("미완료");
        btClass15.setLabelDistance(20);
        btClass15.setLabelTextColor(Color.WHITE);
        btClass15.setLabelBackgroundColor(ContextCompat.getColor(this, R.color.labelbutton));
        btClass15.setLabelVisual(true);

        Intent intent = getIntent();
        studentGrade = intent.getIntExtra("int", 0);
    }
}
