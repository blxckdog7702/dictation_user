package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.ChartDataAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.BubbleChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.ChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.CombinedChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.PieChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseChartFragment;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsFragment extends BaseChartFragment {

    @BindView(R.id.lvStats) ListView lvStats;
    @BindView(R.id.tvStats) TextView tvStats;
    private ArrayList<ChartItem> chartItems;
    private ArrayList<Teacher> teachers;
    private ArrayList<QuizHistory> quizHistories = new ArrayList<>();

    ArrayList<Integer> myProperty = new ArrayList<>();
    ArrayList<Integer> groupProperty = new ArrayList<>();
    ArrayList<Integer> myScore = new ArrayList<>();
    ArrayList<Double> groupAverage = new ArrayList<>();
    String[] marker = {"띄어쓰기","맞춤법","붙여쓰기"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);

        getServerData();

        return view;
    }

    private void initModels() {
        myProperty.clear();
        groupProperty.clear();
        myScore.clear();
        groupAverage.clear();
        if(quizHistories != null){
            for(int i = 0; i < 3; i++){
                myProperty.add(i, 0);
                groupProperty.add(i, 0);
            }
            for(QuizHistory quizHistory : quizHistories){
                if(quizHistory.getAverage() == null){
                    continue;
                }
                groupAverage.add(quizHistory.getAverage());
                groupProperty.set(0, groupProperty.get(0) + quizHistory.getRectifyCount().getProperty1());
                groupProperty.set(1, groupProperty.get(1) + quizHistory.getRectifyCount().getProperty2());
                groupProperty.set(2, groupProperty.get(2) + quizHistory.getRectifyCount().getProperty3());
//                groupProperty.set(3, groupProperty.get(3) + quizHistory.getRectifyCount().getProperty4());
//                groupProperty.set(4, groupProperty.get(4) + quizHistory.getRectifyCount().getProperty5());
//                groupProperty.set(5, groupProperty.get(5) + quizHistory.getRectifyCount().getProperty6());
//                groupProperty.set(6, groupProperty.get(6) + quizHistory.getRectifyCount().getProperty7());
//                groupProperty.set(7, groupProperty.get(7) + quizHistory.getRectifyCount().getProperty8());
//                groupProperty.set(8, groupProperty.get(8) + quizHistory.getRectifyCount().getProperty9());
//                groupProperty.set(9, groupProperty.get(9) + quizHistory.getRectifyCount().getProperty10());
                for(QuizResult quizResult : quizHistory.getQuizResults()){
                    if(quizResult.getStudentName().equals(Student.getInstance().getName())){
                        myScore.add(quizResult.getScore());
                        myProperty.set(0, myProperty.get(0) + quizResult.getRectifyCount().getProperty1());
                        myProperty.set(1, myProperty.get(1) + quizResult.getRectifyCount().getProperty2());
                        myProperty.set(2, myProperty.get(2) + quizResult.getRectifyCount().getProperty3());
//                        myProperty.set(3, myProperty.get(3) + quizResult.getRectifyCount().getProperty4());
//                        myProperty.set(4, myProperty.get(4) + quizResult.getRectifyCount().getProperty5());
//                        myProperty.set(5, myProperty.get(5) + quizResult.getRectifyCount().getProperty6());
//                        myProperty.set(6, myProperty.get(6) + quizResult.getRectifyCount().getProperty7());
//                        myProperty.set(7, myProperty.get(7) + quizResult.getRectifyCount().getProperty8());
//                        myProperty.set(8, myProperty.get(8) + quizResult.getRectifyCount().getProperty9());
//                        myProperty.set(9, myProperty.get(9) + quizResult.getRectifyCount().getProperty10());
                    }
                }
            }
            tvStats.setText("");
        }
        else{
            tvStats.setText("시험 결과가 없습니다.");
        }
    }

    private void setupView() {
        chartItems = new ArrayList<>();
        chartItems.add(new PieChartItem(generatePieData(myProperty, marker), getActivity()));
        chartItems.add(new BubbleChartItem(generateBubbleata(groupProperty), getActivity()));
        chartItems.add(new CombinedChartItem(generateCombinedData(generateLineData(myScore), generateBarData(groupAverage)), getActivity()));


        ChartDataAdapter cda = new ChartDataAdapter(getActivity(), chartItems);
        lvStats.setAdapter(cda);
    }

    private void getServerData(){
        ApiRequester.getInstance().getStudentsTeachers(Student.getInstance().getId(), new ApiRequester.UserCallback<List<Teacher>>() {
            @Override
            public void onSuccess(List<Teacher> result) {
                if(result != null && !result.isEmpty()){
                    teachers = (ArrayList<Teacher>) result;
                    for(Teacher teacher : teachers){
                        try {
                            ApiRequester.getInstance().getTeachersQuizHistories(teacher.getId(), new ApiRequester.UserCallback<List<QuizHistory>>() {
                                @Override
                                public void onSuccess(List<QuizHistory> result) {
                                    if(result != null && !result.isEmpty()){
                                        quizHistories.addAll(result);
                                        initModels();
                                        setupView();
                                    }
                                }
                                @Override
                                public void onFail() {
                                    TastyToast.makeText(getActivity(), "서버와의 연결이 원활하지 않습니다.", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFail() {
                TastyToast.makeText(getActivity(), "서버와의 연결이 원활하지 않습니다.", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        });
    }
}
