package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.main;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.Grader;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.NaverSpellChecker;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.BeforeCheck;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Grade;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Quiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.atk.text.CandidateInfo;
import com.myscript.certificate.MyCertificate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity implements // 답안, 문제, 문제번호 넘김
        SingleLineWidgetApi.OnConfiguredListener,
        SingleLineWidgetApi.OnTextChangedListener,
        CustomEditText.OnSelectionChanged,
        SingleLineWidgetApi.OnUserScrollBeginListener,
        SingleLineWidgetApi.OnUserScrollEndListener,
        SingleLineWidgetApi.OnUserScrollListener

{

    private static final String TAG = "SingleLineDemo";

    private SingleLineWidgetApi widget;
    private CustomEditText mTextField;
    private int isCorrectionMode;
    String SubmittedAnswer;
    ArrayList<QuizResult> info;
    //String answer[] = new String[10];
    int count = 0;
    String number;
    String question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exam);

        registerReceiver(myReceiver, new IntentFilter(MyFirebaseMessagingService.QUIZ_CONTROL_INTENT));

        mTextField = (CustomEditText) findViewById(R.id.textField);

        mTextField.setOnSelectionChangedListener(this);
        widget = (SingleLineWidget) findViewById(R.id.widget);
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

        // references assets directly from the APK to avoid extraction in application
        // file system
        widget.addSearchDir("zip://" + getPackageCodePath() + "!/assets/conf");

        // The configuration is an asynchronous operation. Callbacks are provided to
        // monitor the beginning and end of the configuration process and update the UI
        // of the input method accordingly.
        //
        // "en_US" references the en_US bundle name in conf/en_US.conf file in your assets.
        // "cur_text" references the configuration name in en_US.conf
        widget.configure("ko_KR", "cur_text");

        widget.setText(mTextField.getText().toString());
        isCorrectionMode = 0;

        ApiRequester apiRequester = new ApiRequester();

        try {
            apiRequester.getTeachersQuizzes(new ApiRequester.UserCallback<List<Quiz>>() {
                @Override
                public void onSuccess(List<Quiz> result) {
                    for(Quiz quiz : result){
                        for(Question ques : quiz.getQuestions()) {
                            String number = String.valueOf(ques.getNumber());
                            String question = ques.getSentence();
                        }
                    }
                }

                @Override
                public void onFail() {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



//    public void onClearButtonClick(ArrayList<String[]> testRes) {
//
//        ArrayList<String[]> arr= new ArrayList<String[]>();
//
//        String[] str = new String[10];
//
//        str[0] = question;
//        str[1] = num;
//        str[2] = answer;
//
//                arr.add(str);
//
//
//        SubmittedAnswer = mTextField.getText().toString();
//        QR.setSubmittedAnswer(SubmittedAnswer);
//
//        widget.clear();
//
//    }

    //public void EndExam(ArrayList<String[]> testRes) {
//        info= new ArrayList<QuizResult>();
//
//        QuizResult quizResult = new QuizResult();
//
//        for (String[] result : testRes) {
//            quizResult.setQuizNumber(Integer.parseInt(result[0])); // 문제번호
//            quizResult.set(result[1]); // 문제
//            quizResult.setSubmittedanswer(SubmittedAnswer); // 작성답안
//
//
//            info.add(quizResult);
//        }
//
//        Intent intent = new Intent(ExamActivity.this, ExamResultPage.class);
//        intent.putExtra("OBJECT", info);



    @Override
    protected void onDestroy() {
        widget.setOnTextChangedListener(null);
        widget.setOnConfiguredListener(null);

        super.onDestroy();
    }

    public void onSpaceButtonClick(View v) {
        int index = widget.getCursorIndex();
        boolean replaced = widget.replaceCharacters(index, index, " ");
        if (replaced) {
            widget.setCursorIndex(index + 1);
            isCorrectionMode++;
        }
    }

    public void onDeleteButtonClick(View v) {
        int index = widget.getCursorIndex();
        CandidateInfo info = widget.getCharacterCandidates(widget.getCursorIndex() - 1);
        boolean replaced = widget.replaceCharacters(info.getStart(), info.getEnd(), null);
        if (replaced) {
            widget.setCursorIndex(index - (info.getEnd() - info.getStart()));
            isCorrectionMode++;
        }
    }

    public void onCheckButtonClick(View v) {
        SubmittedAnswer = mTextField.getText().toString(); // 텍스트 변수 저장 // 서버에 의해서 화면 전환 될 때마다 배열로 답안 저장
        Toast.makeText(getApplicationContext(), SubmittedAnswer, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigured(SingleLineWidgetApi widget, boolean success) {
        if (!success) {
            Toast.makeText(getApplicationContext(), widget.getErrorString(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "Unable to configure the Single Line Widget: " + widget.getErrorString());
            return;
        }
        //Toast.makeText(getApplicationContext(), "Single Line Widget Configured", Toast.LENGTH_SHORT).show();
        if (BuildConfig.DEBUG)
            Log.d(TAG, "Single Line Widget configured!");
    }

    @Override
    public void onTextChanged(SingleLineWidgetApi widget, String text, boolean intermediate) {
//        Toast.makeText(getApplicationContext(), "Recognition update", Toast.LENGTH_SHORT).show();
//        if(BuildConfig.DEBUG)
//        {
//            Log.d(TAG, "Single Line Widget recognition: " + widget.getText());
//        }
        Log.d(TAG, "Text changed to \"" + text + "\" (" + (intermediate ? "intermediate" : "stable") + ")");
        // temporarily disable selection changed listener to prevent spurious cursor jumps
        mTextField.setOnSelectionChangedListener(null);
        mTextField.setTextKeepState(text);
        if (isCorrectionMode == 0) {
            mTextField.setSelection(text.length());
            mTextField.setOnSelectionChangedListener(this);
            widget.setCursorIndex(mTextField.length());
        } else {
            mTextField.setSelection(widget.getCursorIndex());
            mTextField.setOnSelectionChangedListener(this);
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
            } else {
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
            mTextField.setOnSelectionChangedListener(null);
            mTextField.setSelection(widget.getCursorIndex());
            mTextField.setOnSelectionChangedListener(this);
        }
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().getString("keyword").equals("next")) {
                moveToNextQuestion();
            }
            else if (intent.getExtras().getString("keyword").equals("previous")) {
                moveToPreviousQuestion();
            }
        }
    };

    //선생님으로부터 다음 문제 신호를 받았을 때 실행되는 메서드.
    public void moveToNextQuestion() {
//        Toast.makeText(getApplicationContext(), "다음문제로!!", Toast.LENGTH_LONG).show();
    }

    //선생님으로부터 이전 문제 신호를 받았을 때 실행되는 메서드.
    public void moveToPreviousQuestion() {
//        Toast.makeText(getApplicationContext(), "이전문제로!!", Toast.LENGTH_LONG).show();
    }



}