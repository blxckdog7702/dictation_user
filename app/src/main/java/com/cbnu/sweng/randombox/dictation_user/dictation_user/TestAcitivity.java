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
            pusanSpellChecker.execute("맞춤뻡 검사기를 통해 빠르고 간편하게 틀린 맞춤법을 수정할수있습니다." +
                    "카카오톡 전숑기능이 있습니다.누규든지 쉽게 이용할수 있슴니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
