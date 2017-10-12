package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CustomEditText;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.certificate.MyCertificate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BasePracticeActivity extends AppCompatActivity implements SingleLineWidgetApi.OnConfiguredListener,
        SingleLineWidgetApi.OnTextChangedListener,
        CustomEditText.OnSelectionChanged,
        SingleLineWidgetApi.OnUserScrollBeginListener,
        SingleLineWidgetApi.OnUserScrollEndListener,
        SingleLineWidgetApi.OnUserScrollListener{
    protected int isCorrectionMode;
    protected ArrayMap<Integer, String> words;
    protected ArrayList<Integer> keys;
    protected int wordNum = 0;
    protected int wordSize = 0;
    @BindView(R.id.widget) SingleLineWidget widget;

    @BindView(R.id.tvWord) TextView tvWord;
    @BindView(R.id.ivWord) ImageView ivWord;

    @OnClick(R.id.btClear)
    void onClickBtClear(){
        widget.clear();
    }
    @OnClick(R.id.btPrev)
    void onClickBtPrev(){
        System.out.println("WordSize() : " + wordSize);
        System.out.println("WordNum() : " + wordNum);
        if(wordNum > 0 ){
            showWords(wordNum);
            wordNum--;
        }
        else{
            Toast.makeText(getApplicationContext(), "이전 낱말이 없습니다.", Toast.LENGTH_LONG).show();
        }
        widget.clear();
    }
    @OnClick(R.id.btNext)
    void onClickBtNext(){
        System.out.println("WordSize() : " + wordSize);
        System.out.println("WordNum() : " + wordNum);
        if(wordNum < wordSize){
            showWords(wordNum);
            wordNum++;
        }
        else{
            Toast.makeText(getApplicationContext(), "다음 낱말이 없습니다.", Toast.LENGTH_LONG).show();
        }
        widget.clear();
    }

    @Override
    public void setContentView(int layoutResID) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        Init();
    }

    public void Init(){
        if (!widget.registerCertificate(MyCertificate.getBytes())) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Please use a valid certificate.");
            dlgAlert.setTitle("Invalid certificate");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss the dialog
                }
            });
            dlgAlert.create().show();
            return;
        }
        widget.setOnConfiguredListener(this);
        widget.setOnTextChangedListener(this);
        widget.setOnUserScrollBeginListener(this);
        widget.setOnUserScrollEndListener(this);
        widget.setOnUserScrollListener(this);
        widget.setWritingAreaBackgroundResource(R.drawable.sltw_bg_pattern);
        widget.setScrollbarResource(R.drawable.sltw_scrollbar_xml);
        widget.setScrollbarMaskResource(R.drawable.sltw_scrollbar_mask);
        widget.setScrollbarBackgroundResource(R.drawable.sltw_scrollbar_background);
        widget.setLeftScrollArrowResource(R.drawable.sltw_arrowleft_xml);
        widget.setRightScrollArrowResource(R.drawable.sltw_arrowright_xml);
        widget.setCursorResource(R.drawable.sltw_text_cursor_holo_light);
        widget.addSearchDir("zip://" + getPackageCodePath() + "!/assets/conf");
        widget.configure("ko_KR", "cur_text");
        isCorrectionMode = 0;

        setWords();
        showWords(wordNum);
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

    @Override
    public void onConfigured(SingleLineWidgetApi widget, boolean success) {
        if (!success) {
            Toast.makeText(getApplicationContext(), widget.getErrorString(), Toast.LENGTH_LONG).show();
            Log.e("TAG", "Unable to configure the Single Line Widget: " + widget.getErrorString());
            return;
        }
        if (BuildConfig.DEBUG)
            Log.d("TAG", "Single Line Widget configured!");
    }

    @Override
    public void onTextChanged(SingleLineWidgetApi widget, String text, boolean intermediate) {

        Log.d("TAG", "Text changed to \"" + text + "\" (" + (intermediate ? "intermediate" : "stable") + ")");
        // temporarily disable selection changed listener to prevent spurious cursor jumps
        if (isCorrectionMode == 0) {
        }
        else {
            isCorrectionMode--;
        }
    }

    @Override
    public void onSelectionChanged(EditText editText, int selStart, int selEnd) {
        Log.d("TAG", "Selection changed to [" + selStart + "-" + selEnd + "]");
        if (widget.getCursorIndex() != selEnd) {
            widget.setCursorIndex(selEnd);
            if (selEnd == widget.getText().length()) {
                widget.scrollTo(selEnd);
            }
            else {
                widget.centerTo(selEnd);
            }
        }
    }

    @Override
    public void onUserScrollBegin(SingleLineWidgetApi w) {}

    @Override
    public void onUserScrollEnd(SingleLineWidgetApi w) {}

    @Override
    public void onUserScroll(SingleLineWidgetApi w) {}

    @Override
    protected void onDestroy() {
        widget.setOnTextChangedListener(null);
        widget.setOnConfiguredListener(null);
        super.onDestroy();
    }
}