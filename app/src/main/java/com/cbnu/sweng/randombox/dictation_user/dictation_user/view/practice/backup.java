package com.cbnu.sweng.randombox.dictation_user.dictation_user.view.practice;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.CustomEditText;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.certificate.MyCertificate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class backup extends AppCompatActivity implements SingleLineWidgetApi.OnConfiguredListener,
                                                                            SingleLineWidgetApi.OnTextChangedListener,
                                                                            CustomEditText.OnSelectionChanged,
                                                                            SingleLineWidgetApi.OnUserScrollBeginListener,
                                                                            SingleLineWidgetApi.OnUserScrollEndListener,
                                                                            SingleLineWidgetApi.OnUserScrollListener{

    @BindView(R.id.textField) CustomEditText textField;
    @BindView(R.id.widget) SingleLineWidget widget;

    private static final String TAG = "WordPractice";
    private int isCorrectionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_word_practice_acitivity);

        ButterKnife.bind(this);

        textField.setOnSelectionChangedListener(this);

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
        widget.setBaselinePosition(getResources().getDimension(R.dimen.baseline_position));
        widget.setWritingAreaBackgroundResource(R.drawable.sltw_bg_pattern);
        widget.setScrollbarResource(R.drawable.sltw_scrollbar_xml);
        widget.setScrollbarMaskResource(R.drawable.sltw_scrollbar_mask);
        widget.setScrollbarBackgroundResource(R.drawable.sltw_scrollbar_background);
        widget.setLeftScrollArrowResource(R.drawable.sltw_arrowleft_xml);
        widget.setRightScrollArrowResource(R.drawable.sltw_arrowright_xml);
        widget.setCursorResource(R.drawable.sltw_text_cursor_holo_light);
        widget.addSearchDir("zip://" + getPackageCodePath() + "!/assets/conf");
        widget.configure("ko_KR", "cur_text");
        widget.setText(textField.getText().toString());

        isCorrectionMode = 0;
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
        textField.setOnSelectionChangedListener(null);
        textField.setTextKeepState(text);
        if (isCorrectionMode == 0) {
            textField.setSelection(text.length());
            textField.setOnSelectionChangedListener(this);
            widget.setCursorIndex(textField.length());
        }
        else {
            textField.setSelection(widget.getCursorIndex());
            textField.setOnSelectionChangedListener(this);
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
            textField.setOnSelectionChangedListener(null);
            textField.setSelection(widget.getCursorIndex());
            textField.setOnSelectionChangedListener(this);
        }
    }

    private void setWords(){
        ArrayMap<Integer, String> words = new ArrayMap<>();


    }
}
