package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acitivity);

        PusanSpellChecker pusanSpellChecker = new PusanSpellChecker();
        try {
            pusanSpellChecker.execute("아 버지가 안 방에 들어가쉰다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
