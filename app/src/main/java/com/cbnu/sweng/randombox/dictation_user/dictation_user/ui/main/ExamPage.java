package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.Grader;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.Util;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Grade;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Quiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.atk.text.CandidateInfo;
import com.myscript.certificate.MyCertificate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExamPage extends AppCompatActivity implements SingleLineWidgetApi.OnConfiguredListener,
                                                                SingleLineWidgetApi.OnTextChangedListener,
                                                                CustomEditText.OnSelectionChanged,
                                                                SingleLineWidgetApi.OnUserScrollBeginListener,
                                                                SingleLineWidgetApi.OnUserScrollEndListener,
                                                                SingleLineWidgetApi.OnUserScrollListener {
    private static final String TAG = "SingleLineDemo";
    private SingleLineWidgetApi widget;
    private CustomEditText mTextField;
    private int isCorrectionMode;

    private String SubmittedAnswer;
    private String SubmittedAnswers[] = new String[10];
    private String quizHitoryID;
    private String quizNumber;
    private int questionNumber = 1;

    private Quiz quiz = null;
    private List<Question> questions = null;
    private ArrayList<ArrayMap<String, String>> qnas = new ArrayList<>();
    private ArrayList<QuestionResult> questionResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Intent intent = getIntent();
        quizHitoryID = intent.getStringExtra("quizHistoryId");
        quizNumber = intent.getStringExtra("quizNumber");
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
        widget.addSearchDir("zip://" + getPackageCodePath() + "!/assets/conf");
        widget.configure("ko_KR", "cur_text");
        widget.setText(mTextField.getText().toString());
        isCorrectionMode = 0;

        ApiRequester apiRequester = new ApiRequester();
        try {
            apiRequester.getTeachersQuizzes(new ApiRequester.UserCallback<List<Quiz>>() {
                @Override
                public void onSuccess(List<Quiz> quizs) {
                    for(Quiz temp : quizs)
                    {
                        if(quizNumber.equals(temp.getNumber().toString()))
                        {
                            quiz = temp;
                            questions = quiz.getQuestions();
                        }
                    }
                }
                @Override
                public void onFail() {
                    //Fail
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        //
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
            else if (intent.getExtras().getString("keyword").equals("end")) {
                endDictation();
            }

        }
    };

    //선생님으로부터 다음 문제 신호를 받았을 때 실행되는 메서드.
    public void moveToNextQuestion() {
        SubmittedAnswer = mTextField.getText().toString();
        SubmittedAnswers[(questionNumber++) - 1] = SubmittedAnswer;

        widget.clear();
        Toast.makeText(getApplicationContext(),
                Integer.toString(questionNumber) + "번 문제입니다.",
                Toast.LENGTH_SHORT).show();

        if(!TextUtils.isEmpty(SubmittedAnswers[questionNumber - 1])){
            mTextField.postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    mTextField.setText(SubmittedAnswers[questionNumber - 1]);
                }
            }, 500);
        }

    }

    //선생님으로부터 이전 문제 신호를 받았을 때 실행되는 메서드.
    public void moveToPreviousQuestion() {
        SubmittedAnswer = mTextField.getText().toString();
        SubmittedAnswers[(questionNumber--) - 1] = SubmittedAnswer;

        widget.clear();
        Toast.makeText(getApplicationContext(),
                Integer.toString(questionNumber) + "번 문제입니다.",
                Toast.LENGTH_SHORT).show();

        if(!TextUtils.isEmpty(SubmittedAnswers[questionNumber - 1])){
            mTextField.postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    mTextField.setText(SubmittedAnswers[questionNumber - 1]);
                }
            }, 500);
        }
    }

    //선생님으로부터 받아쓰기 종료 신호를 받았을 때 실행되는 메서드.
    public void endDictation() {
        SubmittedAnswer = mTextField.getText().toString();
        SubmittedAnswers[(questionNumber) - 1] = SubmittedAnswer;

        Toast.makeText(getApplicationContext(),
                "시험이 종료되었습니다.",
                Toast.LENGTH_SHORT).show();

        for(int i=0; i<10; i++) {
            ArrayMap<String, String> qna = new ArrayMap<>();
            qna.put("questionNumber", Integer.toString(questions.get(i).getNumber()));
            qna.put("question", questions.get(i).getSentence());
            qna.put("SubmittedAnswer", SubmittedAnswers[i]);
            System.out.println(qna.get("questionNumber") + "  " + qna.get("question") + "  " + qna.get("SubmittedAnswer") );
            qnas.add(qna);
        }

        Grader grader = new Grader();
        ArrayList<Grade> grades = grader.execute(qnas);
        QuizResult quizResult = new QuizResult();

        for(Grade grade : grades){
            QuestionResult questionResult = new QuestionResult();
            questionResult.setCorrect(grade.getCorrect());
            questionResult.setRectify(grade.getRectify());
            Log.d("ExamPage/R", String.valueOf(grade.getRectify().size()));
            questionResult.setQuestionNumber(grade.getQuestionNumber());
            questionResult.setSubmittedAnswer(grade.getSubmittedAnswer());
            questionResults.add(questionResult);
            if (questionResult.getQuestionNumber() == 10){
                quizResult.setScore(grade.getScore());
            }
        }
        quizResult.setQuestionResult(questionResults);
        quizResult.setQuizNumber(Integer.parseInt(quizNumber));
        quizResult.setStudentName("반상민");

        Util.getInstance().moveActivity(this, ExamResultPage.class, quizResult, (ArrayList<Question>) questions);
    }
}