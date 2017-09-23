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

        PusanSpellChecker pusanSpellChecker = new PusanSpellChecker();
        try {
            pusanSpellChecker.execute("아 버지가");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
