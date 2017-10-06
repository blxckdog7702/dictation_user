package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordFragment extends Fragment {

    private int rank;
    private String data;


    @BindView(R.id.rvRecord) RecyclerView rvRecord;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, view);

        setupView();

        return view;
    }

    private void setupView() {
        rvRecord.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecord.setLayoutManager(MyLayoutManager);

//        practiceTypeAdapter = new PracticeTypeAdapter(this, practiceTypeModels);
//        rvPractice.setAdapter(practiceTypeAdapter);
//        practiceTypeAdapter.notifyDataSetChanged();
    }

    private void initModels() {
//        practiceTypeModels.clear();
//        for (int i = 0; i < numOfCard; i++) {
//            PracticeTypeModel practiceTypeModel = new PracticeTypeModel();
//            practiceTypeModel.setPraticeType(practiceTypes[i]);
//            practiceTypeModel.setTitle(titles[i]);
//            practiceTypeModel.setImage(images[i]);
//            practiceTypeModels.add(practiceTypeModel);
//        }
    }

}
