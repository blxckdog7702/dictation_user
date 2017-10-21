package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.GradeModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RectifyCount;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ExamResultPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CustomEditText;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Grader;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.TTSRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.atk.text.CandidateInfo;
import com.myscript.certificate.MyCertificate;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DictationPracticeActivity extends AppCompatActivity implements SingleLineWidgetApi.OnConfiguredListener,
                                                                    SingleLineWidgetApi.OnTextChangedListener,
                                                                    CustomEditText.OnSelectionChanged,
                                                                    SingleLineWidgetApi.OnUserScrollBeginListener,
                                                                    SingleLineWidgetApi.OnUserScrollEndListener,
                                                                    SingleLineWidgetApi.OnUserScrollListener {
    private int questionNumber = 1;
    private ArrayList<Question> questions = new ArrayList<>();
    private static final String TAG = "SingleLineDemo";
    private SingleLineWidgetApi widget;
    private CustomEditText mTextField;
    private int isCorrectionMode;

    private TTSRequester mTTSRequester = null;
    private long mLastClickTime = 0;

    private String SubmittedAnswer;
    private String SubmittedAnswers[] = new String[10];
    private ArrayList<ArrayMap<String, String>> qnas = new ArrayList<>();
    private ArrayList<QuestionResult> questionResults = new ArrayList<>();
    private String questionType;

    @OnClick(R.id.btPrev)
    void btPrev() {
        if(questionNumber != 1 ){
            SubmittedAnswer = mTextField.getText().toString();
            SubmittedAnswers[(questionNumber--) - 1] = SubmittedAnswer;

            widget.clear();
            Toast.makeText(getApplicationContext(),
                    Integer.toString(questionNumber) + "번 문제입니다.",
                    Toast.LENGTH_SHORT).show();

            if(!TextUtils.isEmpty(SubmittedAnswers[questionNumber - 1]) && questionNumber > 0){
                mTextField.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        mTextField.setText(SubmittedAnswers[questionNumber - 1]);
                        widget.setText(SubmittedAnswers[questionNumber - 1]);
                    }
                }, 500);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "이전 문제가 없습니다.", Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R.id.btNext)
    void btNext() {
        if(questionNumber < 10){
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
                        widget.setText(SubmittedAnswers[questionNumber - 1]);
                    }
                }, 500);
            }
        }
        else {
            endDictation();
        }
    }
    @OnClick(R.id.btSpeaker)
    void btSpeaker() {
        //disable double click
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        //null check
        if (questions.get(questionNumber) == null) {
            return;
        }

        //초기 1회
        if (mTTSRequester == null) {
            mTTSRequester = new TTSRequester();
            mTTSRequester.execute(questions.get(questionNumber).getSentence());
        }
        //그 외 버튼 눌렀을 때
        else {
            if (mTTSRequester.getMp() == null) {
                return;
            }
            if (!mTTSRequester.getMp().isPlaying()) {
                mTTSRequester = new TTSRequester();
                mTTSRequester.execute(questions.get(questionNumber).getSentence());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_dictation_practice);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        questionType = intent.getStringExtra("String");

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

        initModel();
    }

    @Override
    public void onBackPressed() {
        Util.getInstance().onBackPressed(this);
    }

    private void initModel() {
        ArrayMap<Integer, String> qs = new ArrayMap<>();
        qs.put(1, "세수를 합니다.");
        qs.put(2, "잠을 잡니다.");
        qs.put(3, "책을 읽습니다.");
        qs.put(4, "꼬리를 흔듭니다.");
        qs.put(5, "시소 미끄럼틀");
        qs.put(6, "놀이터에서 놀아요.");
        qs.put(7, "그네를 타요.");
        qs.put(8, "콩쥐가 울고");
        qs.put(9, "항아리가 깨졌습니다.");
        qs.put(10, "다람쥐가 도와줍니다.");
        for(int i = 1; i < 11; i++){
            Question question = new Question();
            question.setNumber(i);
            question.setSentence(qs.get(i));
            questions.add(question);
        }
    }

    private void endDictation() {
        SubmittedAnswer = mTextField.getText().toString();
        SubmittedAnswers[(questionNumber) - 1] = SubmittedAnswer;

        SubmittedAnswers[0] = "아 버지가 방에 들어가쉰다.";
        SubmittedAnswers[1] = "잠을 잡니다.";
        SubmittedAnswers[2] = "책을";
        SubmittedAnswers[3] = "꼬리를 흔듬니다.";
        SubmittedAnswers[4] = "시소 미끄럼틀";
        SubmittedAnswers[5] = "노리터에서 노라요";
        SubmittedAnswers[6] = "다람쥐";
        SubmittedAnswers[7] = "다람쥐";
        SubmittedAnswers[8] = "다람쥐";
        SubmittedAnswers[9] = "다람지가 도아줌니다.";

//        SubmittedAnswers[0] = "세수를 합니다.";
//        SubmittedAnswers[1] = "잠을 잡니다.";
//        SubmittedAnswers[2] = "책을 읽습니다.";
//        SubmittedAnswers[3] = "꼬리를 흔듭니다.";
//        SubmittedAnswers[4] = "시소 미끄럼틀";
//        SubmittedAnswers[5] = "놀이터에서 놀아요.";
//        SubmittedAnswers[6] = "그네를 타요.";
//        SubmittedAnswers[7] = "콩쥐가 울고";
//        SubmittedAnswers[8] = "항아리가 깨졌습니다.";
//        SubmittedAnswers[9] = "다람쥐가 도와줍니다.";

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
        ArrayList<GradeModel> gradeModels = grader.execute(qnas);
        QuizResult quizResult = new QuizResult();
        RectifyCount rectifyCount = new RectifyCount();

        for(GradeModel gradeModel : gradeModels){
            if(gradeModel.getRectify() != null){
                for(PnuErrorWordList pnuErrorWordList : gradeModel.getRectify().getPnuErrorWordList()){
                    if(pnuErrorWordList.getError().getMsg().equals("PASS")){
                        for(int i = 0; i < pnuErrorWordList.getPnuErrorWord().length; i++){
                            if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 1){
                                rectifyCount.setProperty1(rectifyCount.getProperty1() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 2){
                                rectifyCount.setProperty2(rectifyCount.getProperty2() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 3){
                                rectifyCount.setProperty3(rectifyCount.getProperty3() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 4){
                                rectifyCount.setProperty4(rectifyCount.getProperty4() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 5){
                                rectifyCount.setProperty5(rectifyCount.getProperty5() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 6){
                                rectifyCount.setProperty6(rectifyCount.getProperty6() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 7){
                                rectifyCount.setProperty7(rectifyCount.getProperty7() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 8){
                                rectifyCount.setProperty8(rectifyCount.getProperty8() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 9){
                                rectifyCount.setProperty9(rectifyCount.getProperty9()
                                        + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 10){
                                rectifyCount.setProperty10(rectifyCount.getProperty10()
                                        + pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod());
                            }
                        }
                    }
                    else{
                        continue;
                    }
                }
            }
            QuestionResult questionResult = new QuestionResult();
            questionResult.setCorrect(gradeModel.getCorrect());
            questionResult.setRectify(gradeModel.getRectify());
            questionResult.setQuestionNumber(gradeModel.getQuestionNumber());
            questionResult.setSubmittedAnswer(gradeModel.getSubmittedAnswer());
            questionResults.add(questionResult);
            if (questionResult.getQuestionNumber() == 10){
                quizResult.setScore(gradeModel.getScore());
            }
        }
        quizResult.setQuestionResult(questionResults);
        quizResult.setStudentName(Student.getInstance().getName());
        quizResult.setRectifyCount(rectifyCount);

        Util.getInstance().moveActivity(this, ExamResultPage.class, quizResult, (ArrayList<Question>) questions);
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
        }
        else {
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
            mTextField.setOnSelectionChangedListener(null);
            mTextField.setSelection(widget.getCursorIndex());
            mTextField.setOnSelectionChangedListener(this);
        }
    }
}
