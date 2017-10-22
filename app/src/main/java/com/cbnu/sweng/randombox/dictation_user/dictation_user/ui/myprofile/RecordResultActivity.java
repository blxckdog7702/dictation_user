package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuestionResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseChartActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ExamResultDetailedPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class RecordResultActivity extends BaseChartActivity implements OnChartValueSelectedListener {

    @BindView(R.id.pieChart) PieChart pieChart;
    private Typeface tf;
    private ArrayList<Integer> myProperty = new ArrayList<>();
    String[] marker = {"맞춤법","띄어쓰기","붙여쓰기","4번","5번",
            "6번","7번","8번","9번","10번"};
    String quizHistoryId;
    QuizHistory quizHistory;

    QuizResult quizResult;
    ArrayList<Question> questions;
    @BindView(R.id.ivGradeOne)
    ImageView ivGradeOne;
    @BindView(R.id.ivGradeTwo) ImageView ivGradeTwo;
    @BindView(R.id.ivGradeThree) ImageView ivGradeThree;
    @BindView(R.id.ivGradeFour) ImageView ivGradeFour;
    @BindView(R.id.ivGradeFive) ImageView ivGradeFive;
    @BindView(R.id.ivGradeSix) ImageView ivGradeSix;
    @BindView(R.id.ivGradeSeven) ImageView ivGradeSeven;
    @BindView(R.id.ivGradeEight) ImageView ivGradeEight;
    @BindView(R.id.ivGradeNine) ImageView ivGradeNine;
    @BindView(R.id.ivGradeTen) ImageView ivGradeTen;
    @BindView(R.id.ivScore) ImageView ivScore;

    @OnClick(R.id.btResultOne)
    void onClickBtResultOne(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 1);
    }
    @OnClick(R.id.btResultTwo)
    void onClickBtResultTwo(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 2);
    }
    @OnClick(R.id.btResultThree)
    void onClickBtResultThree(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 3);
    }
    @OnClick(R.id.btResultFour)
    void onClickBtResultFour(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 4);
    }
    @OnClick(R.id.btResultFive)
    void onClickBtResultFive(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 5);
    }
    @OnClick(R.id.btResultSix)
    void onClickBtResultSix(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 6);
    }
    @OnClick(R.id.btResultSeven)
    void onClickBtResultSeven(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 7);
    }
    @OnClick(R.id.btResultEight)
    void onClickBtResultEight(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 8);
    }
    @OnClick(R.id.btResultNine)
    void onClickBtResultNine(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 9);
    }
    @OnClick(R.id.btResultTen)
    void onClickBtResultTen(){
        Util.getInstance().moveActivity(this, ExamResultDetailedPage.class, quizResult,
                questions, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_result);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        quizHistoryId = intent.getStringExtra("String");

        getServerData();
    }

    private void getServerData(){
        try {
            ApiRequester.getInstance().getQuizHistory(quizHistoryId, new ApiRequester.UserCallback<QuizHistory>() {
                @Override
                public void onSuccess(QuizHistory result) {
                    quizHistory = result;
                    if(quizHistory != null){
                        initModel();
                        setupView();

                        initResultModel();
                        setupResultView();
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
    private void initModel(){
        if(quizHistory != null){
            for(int i = 0; i < 10; i++){
                myProperty.add(quizHistory.getRectifyCount().getProperty1());
                myProperty.add(quizHistory.getRectifyCount().getProperty2());
                myProperty.add(quizHistory.getRectifyCount().getProperty3());
                myProperty.add(quizHistory.getRectifyCount().getProperty4());
                myProperty.add(quizHistory.getRectifyCount().getProperty5());
                myProperty.add(quizHistory.getRectifyCount().getProperty6());
                myProperty.add(quizHistory.getRectifyCount().getProperty7());
                myProperty.add(quizHistory.getRectifyCount().getProperty8());
                myProperty.add(quizHistory.getRectifyCount().getProperty9());
                myProperty.add(quizHistory.getRectifyCount().getProperty10());
            }
        }
    }

    private void initResultModel(){
        for(QuizResult quizResult : quizHistory.getQuizResults()){
            if(Student.getInstance().getName().equals(quizResult.getStudentName())){
                RecordResultActivity.this.quizResult = quizResult;
                RecordResultActivity.this.questions = (ArrayList<Question>) quizResult.getQuiz().getQuestions();
                break;
            }
        }
    }

    private void setupResultView(){
        if(quizResult.getScore() == 0){
            ivScore.setImageResource(R.drawable.score_00);
        }
        else if(quizResult.getScore() == 10){
            ivScore.setImageResource(R.drawable.score_10);
        }
        else if(quizResult.getScore() == 20){
            ivScore.setImageResource(R.drawable.score_20);
        }
        else if(quizResult.getScore() == 30){
            ivScore.setImageResource(R.drawable.score_30);
        }
        else if(quizResult.getScore() == 40){
            ivScore.setImageResource(R.drawable.score_40);
        }
        else if(quizResult.getScore() == 50){
            ivScore.setImageResource(R.drawable.score_50);
        }
        else if(quizResult.getScore() == 60){
            ivScore.setImageResource(R.drawable.score_60);
        }
        else if(quizResult.getScore() == 70){
            ivScore.setImageResource(R.drawable.score_70);
        }
        else if(quizResult.getScore() == 80){
            ivScore.setImageResource(R.drawable.score_80);
        }
        else if(quizResult.getScore() == 90){
            ivScore.setImageResource(R.drawable.score_90);
        }
        else if(quizResult.getScore() == 100){
            ivScore.setImageResource(R.drawable.score_100);
        }

        for (QuestionResult questionResult : quizResult.getQuestionResult()) {
            if(questionResult.getQuestionNumber() == 1){
                if(questionResult.getCorrect()){
                    ivGradeOne.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeOne.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 2){
                if(questionResult.getCorrect()){
                    ivGradeTwo.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeTwo.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 3){
                if(questionResult.getCorrect()){
                    ivGradeThree.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeThree.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 4){
                if(questionResult.getCorrect()){
                    ivGradeFour.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeFour.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 5){
                if(questionResult.getCorrect()){
                    ivGradeFive.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeFive.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 6){
                if(questionResult.getCorrect()){
                    ivGradeSix.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeSix.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 7){
                if(questionResult.getCorrect()){
                    ivGradeSeven.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeSeven.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 8){
                if(questionResult.getCorrect()){
                    ivGradeEight.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeEight.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 9){
                if(questionResult.getCorrect()){
                    ivGradeNine.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeNine.setImageResource(R.drawable.ic_check_no);
                }
            }
            else if(questionResult.getQuestionNumber() == 10){
                if(questionResult.getCorrect()){
                    ivGradeTen.setImageResource(R.drawable.ic_check_ok);
                }
                else{
                    ivGradeTen.setImageResource(R.drawable.ic_check_no);
                }
            }
        }
    }
    private void setupView(){
        pieChart.getLayoutParams().height = (int) ((Util.getInstance().getDisplayHeigth(this) / 5) * 3);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        pieChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        pieChart.setOnChartValueSelectedListener(this);

        setData(myProperty, marker);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setEnabled(false);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(mTfRegular);
        pieChart.setEntryLabelTextSize(12f);

        pieChart.animateX(750);
        pieChart.animateY(700);
    }

    private void setData(ArrayList<Integer> values, String[] marker) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < marker.length; i++) {
            entries.add(new PieEntry(values.get(i), marker[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(10f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);

//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tf);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("어떤 맞춤법이 많이\n틀렸는지 확인해보세요.");
//        s.setSpan(new RelativeSizeSpan(1.5f), 0, 14, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length(), 0);
//        s.setSpan(new RelativeSizeSpan(.65f), 14, s.length(), 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length(), s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length(), s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
