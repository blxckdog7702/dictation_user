package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BasePracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.ButterKnife;
import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class WordPracticeActivity extends BasePracticeActivity {

    private DrawableView drawableView;
    private DrawableViewConfig config = new DrawableViewConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_practice);

        ButterKnife.bind(this);
    }

    @Override
    protected void setWords(){
        Integer[] images = {R.drawable.word_apple, R.drawable.word_bachu, R.drawable.word_bae, R.drawable.word_bam,
                R.drawable.word_banana, R.drawable.word_boksunga, R.drawable.word_busut, R.drawable.word_chamwa,
                R.drawable.word_ddalki, R.drawable.word_ddangken, R.drawable.word_gam, R.drawable.word_gazzi,
                R.drawable.word_gochu, R.drawable.word_boksunga, R.drawable.word_goguma, R.drawable.word_grape,
                R.drawable.word_gyul, R.drawable.word_kiwwi, R.drawable.word_manel, R.drawable.word_melon,
                R.drawable.word_mu, R.drawable.word_onion, R.drawable.word_subak, R.drawable.word_pineapple};
        String[] titles = {"사 과", "배 추", "배", "밤",
                "바 나 나", "복 숭 아", "버 섯", "참 외",
                "딸 기", "당 근", "감", "가 지",
                "고 추", "복 숭 아", "고 구 마", "포 도",
                "귤", "키 위", "마 늘", "메 론",
                "무", "양 파", "수 박", "파 인 애 플"};

        words = new ArrayMap<>();
        wordSize = titles.length;
        for(int i = 0; i < wordSize; i++){
            words.put(images[i], titles[i]);
        }
        keys = new ArrayList(words.keySet());
        Collections.shuffle(keys);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

}