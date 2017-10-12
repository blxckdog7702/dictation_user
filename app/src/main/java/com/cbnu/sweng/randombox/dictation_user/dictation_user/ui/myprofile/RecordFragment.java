package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.RecordAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecordModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordFragment extends Fragment {

    private RecordAdapter recordAdapter;
    private ArrayList<RecordModel> recordModels = new ArrayList<>();
    private Integer rank[] = {1,2,3,4,5,6,7,8,9,10};
    private Integer score[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
    private String comment[] = {"참 잘했어요!","참 잘했어요!", "참 잘했어요!", "조금 더 힘내세요!", "조금 더 힘내세요!",
                                "조금 더 힘내세요!", "많이 분발해야겠어요!", "많이 분발해야겠어요!", "많이 분발해야겠어요!", "많이 분발해야겠어요!"};
    private String date[] = {"2017-10-17 금요일","2017-10-16 목요일","2017-10-15 수요일","2017-10-14 화요일","2017-10-13 월요일",
                                "2017-10-12 금요일","2017-10-11 목요일","2017-10-10 수요일","2017-10-9 화요일","2017-10-8 월요일"};
    private int dataNum = 10;

    @BindView(R.id.rvRecord) RecyclerView rvRecord;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);

        initModels();
        setupView();

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

    private void initModels() {
        recordModels.clear();
        for (int i = 0; i < dataNum; i++) {
            RecordModel recordModel = new RecordModel();
            recordModel.setDate(date[i]);
            recordModel.setRank(rank[i]);
            recordModel.setScore(score[i]);
            if(score[i] >= 80){
                recordModel.setComment("참 잘했어요!");
            }
            else if(score[i] >= 50){
                recordModel.setComment("조금 더 힘내세요!");
            }
            else{
                recordModel.setComment("많이 분발해야겠어요!");
            }
            recordModels.add(recordModel);
        }
    }

}
