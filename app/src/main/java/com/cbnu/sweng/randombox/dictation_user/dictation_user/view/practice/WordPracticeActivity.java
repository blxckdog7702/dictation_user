package com.cbnu.sweng.randombox.dictation_user.dictation_user.view.practice;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.CustomEditText;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.atk.text.CandidateInfo;
import com.myscript.certificate.MyCertificate;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WordPracticeActivity extends AppCompatActivity implements SingleLineWidgetApi.OnConfiguredListener,
                                                                            SingleLineWidgetApi.OnTextChangedListener,
                                                                            CustomEditText.OnSelectionChanged,
                                                                            SingleLineWidgetApi.OnUserScrollBeginListener,
                                                                            SingleLineWidgetApi.OnUserScrollEndListener,
                                                                            SingleLineWidgetApi.OnUserScrollListener{
    @BindView(R.id.widget) SingleLineWidget widget;
    @BindView(R.id.tvWord) TextView tvWord;
    @BindView(R.id.ivWord) ImageView ivWord;
    @OnClick(R.id.btSpace)
    void onClickBtSpace(){
        int index = widget.getCursorIndex();
        boolean replaced = widget.replaceCharacters(index, index, " ");
        if (replaced) {
            widget.setCursorIndex(index + 1);
            isCorrectionMode++;
        }
    }
    @OnClick(R.id.btErase)
    void onClickBtErase(){
        int index = widget.getCursorIndex();
        CandidateInfo info = widget.getCharacterCandidates(widget.getCursorIndex() - 1);
        boolean replaced = widget.replaceCharacters(info.getStart(), info.getEnd(), null);
        if (replaced) {
            widget.setCursorIndex(index - (info.getEnd() - info.getStart()));
            isCorrectionMode++;
        }
    }
    @OnClick(R.id.btPrev)
    void onClickBtPrev(){
        if(wordNum > 0 ){
            wordNum--;
            showWords(wordNum);
        }
        else{
            Toast.makeText(getApplicationContext(), "이전 낱말이 없습니다.", Toast.LENGTH_LONG).show();
        }


    }
    @OnClick(R.id.btNext)
    void onClickBtNext(){
        if(wordNum < wordSize - 2){
            wordNum++;
            showWords(wordNum);
        }
        else{
            Toast.makeText(getApplicationContext(), "다음 낱말이 없습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private static final String TAG = "WordPractice";
    private int isCorrectionMode;
    ArrayMap<Integer, String> words;
    ArrayList<Integer> keys;
    private int wordNum = 0;
    private int wordSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_word_practice_acitivity);

        ButterKnife.bind(this);

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
        //widget.setBaselinePosition(getResources().getDimension(R.dimen.baseline_position));
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

    @Override
    protected void onDestroy() {
        widget.setOnTextChangedListener(null);
        widget.setOnConfiguredListener(null);
        super.onDestroy();
    }

    @Override
    public void onConfigured(SingleLineWidgetApi widget, boolean success) {
        if (!success) {
            Toast.makeText(getApplicationContext(), widget.getErrorString(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "Unable to configure the Single Line Widget: " + widget.getErrorString());
            return;
        }
        if (BuildConfig.DEBUG)
            Log.d(TAG, "Single Line Widget configured!");
    }

    @Override
    public void onTextChanged(SingleLineWidgetApi widget, String text, boolean intermediate) {

        Log.d(TAG, "Text changed to \"" + text + "\" (" + (intermediate ? "intermediate" : "stable") + ")");
        // temporarily disable selection changed listener to prevent spurious cursor jumps
        if (isCorrectionMode == 0) {
        }
        else {
            isCorrectionMode--;
        }
    }

    @Override
    public void onSelectionChanged(EditText editText, int selStart, int selEnd) {
        Log.d(TAG, "Selection changed to [" + selStart + "-" + selEnd + "]");
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
    public void onUserScrollBegin(SingleLineWidgetApi w) {
        Log.d(TAG, "User scroll begin");
    }

    @Override
    public void onUserScrollEnd(SingleLineWidgetApi w) {
        Log.d(TAG, "User scroll end");
    }

    @Override
    public void onUserScroll(SingleLineWidgetApi w) {
        Log.d(TAG, "User scroll");
        if (widget.moveCursorToVisibleIndex()) {
            // temporarily disable selection changed listener
        }
    }

    private void setWords(){
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
    private void showWords(int num){
        Integer key = keys.get(num);
        ivWord.setImageResource(key);
        if(words.get(key).length() == 1){
            tvWord.setTextSize(150);
        }
        else if(words.get(key).length() == 3){
            tvWord.setTextSize(130);
        }
        else if(words.get(key).length() == 5){
            tvWord.setTextSize(100);
        }
        else if(words.get(key).length() == 7){
            tvWord.setTextSize(100);
        }
        tvWord.setText(words.get(key));
    }

}
