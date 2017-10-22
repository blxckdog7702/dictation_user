package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam;

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

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWord;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RectifyCount;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.BuildConfig;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CustomEditText;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Grader;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.GradeModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Quiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.myscript.atk.sltw.SingleLineWidget;
import com.myscript.atk.sltw.SingleLineWidgetApi;
import com.myscript.atk.text.CandidateInfo;
import com.myscript.certificate.MyCertificate;
import com.sdsmdg.tastytoast.TastyToast;

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

    private Teacher teacher;
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
        teacher = (Teacher) intent.getSerializableExtra("Teacher");
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

        try {
            ApiRequester.getInstance().getTeachersQuizzes(teacher.getId(), new ApiRequester.UserCallback<List<Quiz>>() {
                @Override
                public void onSuccess(List<Quiz> quizs) {
                    if(quizs != null){
                        for(Quiz temp : quizs) {
                            if(quizNumber.equals(temp.getNumber().toString())) {
                                quiz = temp;
                                questions = quiz.getQuestions();
                            }
                        }
                    }
                    else{
                        Intent intent = new Intent(ExamPage.this, ReadyPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        ExamPage.this.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                        TastyToast.makeText(getApplicationContext(), "일시적인 오류가 발생하였습니다.", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
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
        unregisterReceiver(myReceiver);
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
        TastyToast.makeText(getApplicationContext(), Integer.toString(questionNumber) + "번 문제입니다.", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);

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

    //선생님으로부터 이전 문제 신호를 받았을 때 실행되는 메서드.
    public void moveToPreviousQuestion() {
        SubmittedAnswer = mTextField.getText().toString();
        SubmittedAnswers[(questionNumber--) - 1] = SubmittedAnswer;

        widget.clear();
        TastyToast.makeText(getApplicationContext(), Integer.toString(questionNumber) + "번 문제입니다.", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);

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

    //선생님으로부터 받아쓰기 종료 신호를 받았을 때 실행되는 메서드.
    public void endDictation() {
        SubmittedAnswer = mTextField.getText().toString();
        SubmittedAnswers[(questionNumber) - 1] = SubmittedAnswer;

//        TEST CASE
//        1. 세수를 합니다.
//        2. 잠을 잡니다.
//        3. 책을 읽습니다.
//        4. 꼬리를 흔듭니다.
//        5. 시소 미끄럼틀
//        6. 놀이터에서 놀아요.
//        7. 그네를 타요.
//        8. 콩쥐가 울고
//        9. 항아리가 깨졌습니다.
//        10. 다람쥐가 도와줍니다.
        SubmittedAnswers[0] = "아버지가 방에 들어가쉰다.";
        SubmittedAnswers[1] = "틀린 곳이 만으면";
        SubmittedAnswers[2] = "컴퓨터을 빠르게 삿다.";
        SubmittedAnswers[3] = "아쉬운점이 있다면 아쉽다.";
        SubmittedAnswers[4] = "익숙치 않으신분들은";
        SubmittedAnswers[5] = "정말로 힘드실꺼에요. 안그레요?";
        SubmittedAnswers[6] = "아픈건 다 낳았어?";
        SubmittedAnswers[7] = "왠지 나도 몰르겠다.";
        SubmittedAnswers[8] = "틈틈이 글이 쓰고 있다.";
        SubmittedAnswers[9] = "일도 해야되서 힘들다.";
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

        TastyToast.makeText(getApplicationContext(), "시험이 종료되었습니다.", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

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
                                rectifyCount.setProperty9(rectifyCount.getProperty9() + 1);
                            }
                            else if(pnuErrorWordList.getPnuErrorWord()[i].getHelp().getNCorrectMethod() == 10){
                                rectifyCount.setProperty10(rectifyCount.getProperty10() + 1);
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
        quizResult.setQuiz(quiz);
        quizResult.setStudentName(Student.getInstance().getName());
        quizResult.setRectifyCount(rectifyCount);

        try {
            ApiRequester.getInstance().endQuiz(quizHitoryID, Student.getInstance().getId(),
                                    quizResult, new ApiRequester.UserCallback<QuizHistory>(){

                        @Override
                        public void onSuccess(QuizHistory result) {
                            Log.v("exampage", "Send data to server");
                        }

                        @Override
                        public void onFail() {
                            Log.e("exampage", "Server Error!");
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

        Util.getInstance().moveActivity(this, ExamResultPage.class, quizResult, (ArrayList<Question>) questions);
        unsubScribe(teacher.getLoginId());
        this.finishAffinity();
    }
    private void unsubScribe(String topic){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }

    @Override
    public void onBackPressed() {
        //종료 불가
    }
}