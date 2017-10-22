package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BasePracticeActivity;

import java.util.ArrayList;

public class VowelAndConsonantPracticeActivity extends BasePracticeActivity {

    @Override
    protected void setWords(){
        Integer[] images = {R.drawable.word_1, R.drawable.word_11, R.drawable.word_21, R.drawable.word_31,
                            R.drawable.word_41, R.drawable.word_52, R.drawable.word_62, R.drawable.word_73,
                            R.drawable.word_83, R.drawable.word_93, R.drawable.word_104, R.drawable.word_114,
                            R.drawable.word_124, R.drawable.word_134,
                            R.drawable.word_2, R.drawable.word_12, R.drawable.word_22, R.drawable.word_32,
                            R.drawable.word_42, R.drawable.word_53, R.drawable.word_63, R.drawable.word_74,
                            R.drawable.word_84, R.drawable.word_94, R.drawable.word_105, R.drawable.word_115,
                            R.drawable.word_125, R.drawable.word_135,
                            R.drawable.word_3, R.drawable.word_13, R.drawable.word_23, R.drawable.word_33,
                            R.drawable.word_43, R.drawable.word_54, R.drawable.word_64, R.drawable.word_75,
                            R.drawable.word_85, R.drawable.word_95, R.drawable.word_106, R.drawable.word_116,
                            R.drawable.word_126, R.drawable.word_136,
                            R.drawable.word_4, R.drawable.word_14, R.drawable.word_24, R.drawable.word_34,
                            R.drawable.word_44, R.drawable.word_55, R.drawable.word_65, R.drawable.word_76,
                            R.drawable.word_86, R.drawable.word_96, R.drawable.word_107, R.drawable.word_117,
                            R.drawable.word_127, R.drawable.word_137,
                            R.drawable.word_5, R.drawable.word_15, R.drawable.word_25, R.drawable.word_35,
                            R.drawable.word_45, R.drawable.word_56, R.drawable.word_66, R.drawable.word_77,
                            R.drawable.word_87, R.drawable.word_97, R.drawable.word_108, R.drawable.word_118,
                            R.drawable.word_128, R.drawable.word_138,
                            R.drawable.word_6, R.drawable.word_16, R.drawable.word_26, R.drawable.word_36,
                            R.drawable.word_46, R.drawable.word_57, R.drawable.word_68, R.drawable.word_78,
                            R.drawable.word_88, R.drawable.word_99, R.drawable.word_109, R.drawable.word_119,
                            R.drawable.word_129, R.drawable.word_139,
                            R.drawable.word_7, R.drawable.word_17, R.drawable.word_27, R.drawable.word_37,
                            R.drawable.word_47, R.drawable.word_58, R.drawable.word_69, R.drawable.word_79,
                            R.drawable.word_89, R.drawable.word_100, R.drawable.word_110, R.drawable.word_120,
                            R.drawable.word_130, R.drawable.word_140,
                            R.drawable.word_8, R.drawable.word_18, R.drawable.word_8, R.drawable.word_38,
                            R.drawable.word_49, R.drawable.word_59, R.drawable.word_70, R.drawable.word_80,
                            R.drawable.word_90, R.drawable.word_101, R.drawable.word_111, R.drawable.word_121,
                            R.drawable.word_131, R.drawable.word_150,
                            R.drawable.word_9, R.drawable.word_19, R.drawable.word_29, R.drawable.word_39,
                            R.drawable.word_50, R.drawable.word_60, R.drawable.word_71, R.drawable.word_81,
                            R.drawable.word_91, R.drawable.word_102, R.drawable.word_112, R.drawable.word_122,
                            R.drawable.word_132, R.drawable.word_151,
                            R.drawable.word_10, R.drawable.word_20, R.drawable.word_30, R.drawable.word_40,
                            R.drawable.word_51, R.drawable.word_61, R.drawable.word_72, R.drawable.word_82,
                            R.drawable.word_92, R.drawable.word_103, R.drawable.word_113, R.drawable.word_123,
                            R.drawable.word_133, R.drawable.word_152,
                            };
        String[] titles = {"가", "나", "다", "라",
                            "마", "바", "사", "아",
                            "자", "차", "카", "타",
                            "파", "하",
                            "갸", "냐", "댜", "랴",
                            "먀", "뱌", "샤", "야",
                            "쟈", "챠", "캬", "탸",
                            "파", "햐",
                            "거", "너", "더", "러",
                            "머", "버", "서", "어",
                            "저", "처", "커", "터",
                            "퍼", "허",
                            "겨", "녀", "뎌", "려",
                            "며", "벼", "셔", "여",
                            "져", "쳐", "켜", "텨",
                            "펴", "혀",
                            "고", "노", "도", "로",
                            "모", "보", "소", "오",
                            "조", "초", "코", "토",
                            "포", "호",
                            "교", "뇨", "됴", "료",
                            "묘", "뵤", "쇼", "요",
                            "죠", "쵸", "쿄", "툐",
                            "표", "효",
                            "구", "누", "두", "루",
                            "무", "부", "수", "우",
                            "주", "추", "쿠", "투",
                            "푸", "후",
                            "규", "뉴", "듀", "류",
                            "뮤", "뷰", "슈", "유",
                            "쥬", "츄", "큐", "튜",
                            "퓨", "휴",
                            "그", "느", "드", "르",
                            "므", "브", "스", "르",
                            "즈", "츠", "크", "트",
                            "프", "흐",
                            "기", "니", "디", "리",
                            "미", "비", "시", "이",
                            "지", "치", "키", "티",
                            "피", "히"
                            };
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
            Log.d("VACPA", "DATA SETTING ERROR!");
        }
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
