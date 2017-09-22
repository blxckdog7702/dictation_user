package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAcitivity extends AppCompatActivity {

    @BindView(R.id.test) EditText test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acitivity);

        ButterKnife.bind(this);

        SpannableStringBuilder ssb = new SpannableStringBuilder("가나다라마바사아자차카타파하");
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 1, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        test.setText(ssb);
    }
}
