package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.os.Bundle;
import android.util.ArrayMap;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;

import java.util.ArrayList;

public class VowelOrConsonantPracticeActivity extends BasePracticeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel_or_consonant);
    }

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
}
