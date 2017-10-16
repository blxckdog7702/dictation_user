package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseDrawerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BasePracticeActivity;

import java.util.ArrayList;

public class VowelAndConsonantPracticeActivity extends BasePracticeActivity {

    @Override
    protected void setWords(){
        Integer[] images = {R.drawable.consonant0, R.drawable.consonant1, R.drawable.consonant2, R.drawable.consonant3,
                R.drawable.consonant4, R.drawable.consonant5, R.drawable.consonant6, R.drawable.consonant7,
                R.drawable.consonant8, R.drawable.consonant9, R.drawable.consonant10, R.drawable.consonant11,
                R.drawable.consonant12, R.drawable.consonant13};
        String[] titles = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

        words = new ArrayMap<>();
        wordSize = titles.length;
        for(int i = 0; i < wordSize; i++){
            words.put(images[i], titles[i]);
        }
        keys = new ArrayList(words.keySet());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel_and_consonant);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
