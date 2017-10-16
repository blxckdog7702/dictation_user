package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CustomEditText;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.TTSRequester;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.certificate.MyCertificate;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public abstract class BasePracticeActivity extends BaseActivity {

    private TTSRequester mTTSRequester = null;
    private long mLastClickTime = 0;
    private DrawableView drawableView;
    private DrawableViewConfig config = new DrawableViewConfig();
    protected ArrayMap<Integer, String> words;
    protected ArrayList<Integer> keys;
    protected int wordNum = 0;
    protected int wordSize = 0;

    @BindView(R.id.tvWord) TextView tvWord;
    @BindView(R.id.ivWord) ImageView ivWord;

    @OnClick(R.id.previousBt) // 이전 낱말 보기
    void onClickBtPrev(){

        if(wordNum > 0 ){
            wordNum--;
            showWords(wordNum);
        }
        else{
            Toast.makeText(getApplicationContext(), "이전 낱말이 없습니다.", Toast.LENGTH_LONG).show();
        }
        drawableView.clear();
        System.out.println("이전 WordSize() : " + wordSize);
        System.out.println("이전 WordNum() : " + wordNum);
    }

    @OnClick(R.id.nextBt) // 다음 낱말 보기
    void onClickBtNext(){
        if(wordNum < wordSize-1){
            wordNum++;
            if(wordNum==wordSize-1)
            {
                Toast.makeText(getApplicationContext(), "다음 낱말이 없습니다.", Toast.LENGTH_LONG).show();
            }
            else
            {
                showWords(wordNum);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "다음 낱말이 없습니다.", Toast.LENGTH_LONG).show();

        }
        drawableView.clear();

        System.out.println("다음 WordSize() : " + wordSize);
        System.out.println("다음 WordNum() : " + wordNum);

    }

    @OnClick(R.id.eraseBt) // 지우기
    void onClickEraseBt () {
        config.setStrokeColor(Color.WHITE);
        config.setStrokeWidth(config.getStrokeWidth() + 30);
    }

    @OnClick(R.id.penBt) // 펜으로 전환
    void onClickPenBt() {
        config.setStrokeColor(Color.BLACK);
        config.setStrokeWidth(20.0f);
    }

    public void readSentenceClick (View view){ // 낱말 읽기(tts)
        //disable double click
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();


        //null check
        if (tvWord.getText() == null) {
            return;
        }

        //초기 1회
        if (mTTSRequester == null) {
            mTTSRequester = new TTSRequester();
            mTTSRequester.execute(tvWord.getText().toString());
        }
        //그 외 버튼 눌렀을 때
        else {
            if (mTTSRequester.getMp() == null) {
                return;
            }
            if (!mTTSRequester.getMp().isPlaying()) {
                mTTSRequester = new TTSRequester();
                mTTSRequester.execute(tvWord.getText().toString());
            }
        }
    }

    protected void showWords(int num){
        Integer key = keys.get(num);
        ivWord.setImageResource(key);
        if(words.get(key).length() == 1){
            tvWord.setTextSize(150);
        }
        else if(words.get(key).length() == 3){
            tvWord.setTextSize(130);
        }
        else if(words.get(key).length() == 5){
            tvWord.setTextSize(90);
        }
        else if(words.get(key).length() == 7){
            tvWord.setTextSize(100);
        }
        tvWord.setText(words.get(key));
    }

    protected abstract void setWords();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_practice);
        ButterKnife.bind(this);
        initUi();
    }

    @Override
    public void setContentView(int layoutResID) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        drawableView = (DrawableView) findViewById(R.id.paintView);
        drawableView.setAlpha(0.5f);
        Button nextBt = (Button) findViewById(R.id.nextBt);
        Button previousBt = (Button) findViewById(R.id.previousBt);
        Button eraseBt = (Button) findViewById(R.id.eraseBt); // 손으로 지우기
        Button penBt = (Button) findViewById(R.id.penBt);

        Button undoButton = (Button) findViewById(R.id.undoButton); // 한 획씩 지우기

        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setShowCanvasBounds(true);
        config.setStrokeWidth(20.0f);
        config.setMinZoom(1.0f);
        config.setMaxZoom(3.0f);
        config.setCanvasHeight(1080);
        config.setCanvasWidth(1920);
        drawableView.setConfig(config);

//        previousBt.setOnClickListener(new View.OnClickListener() {
//
//            @Override public void onClick(View v) {
//                config.setStrokeWidth(config.getStrokeWidth() + 10);
//            }
//        });
//        nextBt.setOnClickListener(new View.OnClickListener() {
//
//            @Override public void onClick(View v) {
//                config.setStrokeWidth(config.getStrokeWidth() - 10);
//            }
//        });
//        changeColorButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override public void onClick(View v) { // 선 색깔 바꾸는 메소드
//                Random random = new Random();
//                config.setStrokeColor(
//                        Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
//            }
//        });

        undoButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                drawableView.undo();
            }
        });

        setWords();
        showWords(wordNum);
    }
}
