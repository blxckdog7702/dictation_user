package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BasePracticeActivity;

import java.util.ArrayList;

public class VowelOrConsonantPracticeActivity extends BasePracticeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vowel_or_consonant);
    }

    @Override
    protected void setWords(){
        Integer[] images = {R.drawable.vowel_1, R.drawable.vowel_2, R.drawable.vowel_3, R.drawable.vowel_4,
                R.drawable.vowel_5, R.drawable.vowel_6, R.drawable.vowel_7, R.drawable.vowel_8,
                R.drawable.vowel_9, R.drawable.vowel_10, R.drawable.vowel_11, R.drawable.vowel_12,
                R.drawable.vowel_13, R.drawable.vowel_14,
                R.drawable.consonant_1, R.drawable.consonant_2, R.drawable.consonant_3, R.drawable.consonant_4,
                R.drawable.consonant_5, R.drawable.consonant_6, R.drawable.consonant_7, R.drawable.consonant_8,
                R.drawable.consonant_9, R.drawable.consonant_10};
        String[] titles = {"ㄱ", "ㄴ", "ㄷ", "ㄹ",
                            "ㅁ", "ㅂ", "ㅅ", "ㅇ",
                            "ㅈ", "ㅊ", "ㅋ", "ㅌ",
                            "ㅍ", "ㅎ"
                            ,"ㅏ", "ㅑ", "ㅓ", "ㅕ",
                            "ㅗ","ㅛ", "ㅜ", "ㅠ",
                            "ㅡ", "ㅣ"};
        if(images.length == titles.length){
            words = new ArrayMap<>();
            wordSize = titles.length;
            for(int i = 0; i < wordSize; i++){
                words.put(images[i], titles[i]);
            }
            keys = new ArrayList<>();
            for(int i = 0; i< images.length; i++){
                keys.add(images[i]);
            }
        }
        else{
            Log.d("VOCPA", "DATA SETTING ERROR!");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
