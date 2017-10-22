package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.TTSRequester;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Arrays;

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
    private String[] exceptStr = new String[]{"ㅏ", "ㅑ", "ㅓ", "ㅕ",
                                                "ㅗ","ㅛ", "ㅜ", "ㅠ",
                                                "ㅡ", "ㅣ"};
    private String[] replaceStr = new String[]{"아", "야", "어", "여",
                                                "오", "요", "우", "유",
                                                "으", "이"};

    @BindView(R.id.tvWord) TextView tvWord;
    @BindView(R.id.ivWord) ImageView ivWord;

    @OnClick(R.id.previousBt) // 이전 낱말 보기
    void onClickBtPrev(){

        if(wordNum > 0 ){
            wordNum--;
//            Drawable d = ivWord.getDrawable();
//            if (d != null && d instanceof BitmapDrawable) {
//                Bitmap bm = ((BitmapDrawable) d).getBitmap();
//                if (bm != null)
//                    bm.recycle();
//            }
            showWords(wordNum);
        }
        else{
            TastyToast.makeText(getApplicationContext(), "이전 낱말이 없습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
        }
        drawableView.clear();
    }

    @OnClick(R.id.nextBt) // 다음 낱말 보기
    void onClickBtNext(){
        if(wordNum < wordSize){
            wordNum++;
            if(wordNum == wordSize)
            {
                TastyToast.makeText(getApplicationContext(), "다음 낱말이 없습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
            }
            else
            {
//                Drawable d = ivWord.getDrawable();
//                if (d != null && d instanceof BitmapDrawable) {
//                    Bitmap bm = ((BitmapDrawable) d).getBitmap();
//                    if (bm != null)
//                        bm.recycle();
//                }
                showWords(wordNum);
            }
        }
        else
        {
            TastyToast.makeText(getApplicationContext(), "다음 낱말이 없습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);

        }

        drawableView.clear();
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

    @OnClick(R.id.speaker) // 펜으로 전환
    void onClickSpeaker() {
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
            if(Arrays.asList(exceptStr).contains(tvWord.getText().toString())){
                mTTSRequester.execute(replaceStr[Arrays.asList(exceptStr).indexOf(tvWord.getText().toString())]);
            }
            else{
                mTTSRequester.execute(tvWord.getText().toString());
            }
        }
        //그 외 버튼 눌렀을 때
        else {
            if (mTTSRequester.getMp() == null) {
                return;
            }
            if (!mTTSRequester.getMp().isPlaying()) {
                mTTSRequester = new TTSRequester();
                if(Arrays.asList(exceptStr).contains(tvWord.getText().toString())){
                    mTTSRequester.execute(replaceStr[Arrays.asList(exceptStr).indexOf(tvWord.getText().toString())]);
                }
                else{
                    mTTSRequester.execute(tvWord.getText().toString());
                }
            }
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews() {
        super.bindViews();
        ButterKnife.bind(this);
        setupToolbar();
        initUi();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.context_menu).setVisible(false);
        return true;
    }

    protected void setupToolbar() {
        super.setupToolbar();
    }

    private void initUi() {
        drawableView = (DrawableView) findViewById(R.id.paintView);
        drawableView.setAlpha(0.9f);
        Button nextBt = (Button) findViewById(R.id.nextBt);
        Button previousBt = (Button) findViewById(R.id.previousBt);
        Button eraseBt = (Button) findViewById(R.id.eraseBt); // 손으로 지우기
        Button penBt = (Button) findViewById(R.id.penBt);

        Button undoButton = (Button) findViewById(R.id.undoButton); // 한 획씩 지우기

        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setShowCanvasBounds(false);
        config.setStrokeWidth(20.0f);
        config.setCanvasHeight(1080);
        config.setCanvasWidth(1920);
        drawableView.setConfig(config);

        undoButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                drawableView.undo();
            }
        });

        setWords();
        showWords(wordNum);
    }

    protected void showWords(int num){
        Integer key = keys.get(num);
        ivWord.setImageResource(key);
        if(words.get(key).length() == 1){
            tvWord.setTextSize(150);
            config.setStrokeWidth(20.0f);
            drawableView.setConfig(config);
        }
        else if(words.get(key).length() == 3){
            tvWord.setTextSize(130);
            config.setStrokeWidth(20.0f);
            drawableView.setConfig(config);
        }
        else if(words.get(key).length() == 5){
            tvWord.setTextSize(80);
            config.setStrokeWidth(15.0f);
            drawableView.setConfig(config);
        }
        else if(words.get(key).length() == 7){
            tvWord.setTextSize(60);
            config.setStrokeWidth(10.0f);
            drawableView.setConfig(config);
        }
        tvWord.setText(words.get(key));
    }

    protected abstract void setWords();
}