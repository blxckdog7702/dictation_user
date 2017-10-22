package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.RecordAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecordModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordFragment extends Fragment {

    private RecordAdapter recordAdapter;
    private ArrayList<RecordModel> recordModels = new ArrayList<>();
    private ArrayList<Teacher> teachers;
    private List<QuizHistory> quizHistories = Collections.synchronizedList(new ArrayList<QuizHistory>());

    @BindView(R.id.rvRecord) RecyclerView rvRecord;
    @BindView(R.id.tvRecord) TextView tvRecord;
    @BindView(R.id.progress) ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);
        TastyToast.makeText(getActivity(), "데이터를 불러오고 있습니다.", TastyToast.LENGTH_LONG, TastyToast.WARNING);
        getServerData();
        return view;
    }

    private void setupView() {
        rvRecord.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecord.setLayoutManager(MyLayoutManager);

        recordAdapter = new RecordAdapter(getActivity(), recordModels);
        rvRecord.setAdapter(recordAdapter);
        recordAdapter.notifyDataSetChanged();
    }

    private void getServerData(){
        ApiRequester.getInstance().getStudentsTeachers(Student.getInstance().getId(), new ApiRequester.UserCallback<List<Teacher>>() {
            @Override
            public void onSuccess(List<Teacher> result) {
                teachers = (ArrayList<Teacher>) result;
                if(teachers != null && !teachers.isEmpty()){
                    for(Teacher teacher : teachers){
                        try {
                            ApiRequester.getInstance().getTeachersQuizHistories(teacher.getId(), new ApiRequester.UserCallback<List<QuizHistory>>() {
                                @Override
                                public void onSuccess(List<QuizHistory> result) {
                                    if(result != null && !result.isEmpty()){
                                        quizHistories.addAll(result);
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
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initModels();
                            setupView();
                        }
                    }, 1500);
                }
                else{
                    TastyToast.makeText(getActivity(), "등록된 선생님이 없습니다.", TastyToast.LENGTH_LONG, TastyToast.INFO);
                }
            }

            @Override
            public void onFail() {
                TastyToast.makeText(getActivity(), "서버와의 연결이 원활하지 않습니다.", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        });
    }

    private void initModels() {
        recordModels.clear();
        if(quizHistories != null && !quizHistories.isEmpty()){
            for(QuizHistory quizHistory : quizHistories){
                RecordModel recordModel = new RecordModel();
                if(quizHistory.getQuizResults() != null && !quizHistory.getQuizResults().isEmpty()){
                    for(QuizResult quizResult :quizHistory.getQuizResults()){
                        if(Student.getInstance().getName().equals(quizResult.getStudentName())){
                            recordModel.setRank(quizResult.getRank());
                            recordModel.setScore(quizResult.getScore());
                            if(recordModel.getScore() >= 80){
                                recordModel.setComment("참 잘했어요!");
                            }
                            else if(recordModel.getScore() >= 50){
                                recordModel.setComment("조금 더 힘내세요!");
                            }
                            else{
                                recordModel.setComment("많이 분발해야겠어요!");
                            }
                            break;
                        }
                    }
                    recordModel.setDate(quizHistory.getDate());
                    recordModel.setQuizhistoryId(quizHistory.getId());
                    if(recordModel.getRank() != 0){
                        recordModels.add(recordModel);
                    }
                }
                else{
                    tvRecord.setText("시험 결과가 없습니다!.");
                    Log.v("RecordFragment", "quizHistory NULL");
                }
            }
        }
        else{
            tvRecord.setText("시험 결과가 없습니다!.");
            Log.v("RecordFragment", "quizHistories NULL");
        }
    }
}
