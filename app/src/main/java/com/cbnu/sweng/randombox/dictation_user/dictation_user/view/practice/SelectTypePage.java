package com.cbnu.sweng.randombox.dictation_user.dictation_user.view.practice;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.PracticeTypeAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PracticeTypeModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.view.BaseActivity;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectTypePage extends BaseActivity {

    @BindView(R.id.rvPractice) RecyclerView rvPractice;

    public static final String[] titles = {"유아용", "유아용", "유아용", "초등학생용"};
    public static final String[] practiceTypes = {"자음 / 모음", "자음 + 모음", "낱말카드", "급수표"};
    public static final Integer[] images = {R.drawable.practice1, R.drawable.practice1, R.drawable.practice1, R.drawable.practice4};
    private int numOfCard = 4;
    private ArrayList<PracticeTypeModel> practiceTypeModels = new ArrayList<>();
    private PracticeTypeAdapter practiceTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        ButterKnife.bind(this);

        initModels();
        setAdapter();
    }

    private void setAdapter() {
        StaggeredGridLayoutManager MyLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        MyLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        rvPractice.setHasFixedSize(true);
        rvPractice.setLayoutManager(MyLayoutManager);
        practiceTypeAdapter = new PracticeTypeAdapter(this, practiceTypeModels);
        rvPractice.setAdapter(practiceTypeAdapter);
        practiceTypeAdapter.notifyDataSetChanged();
    }

    private void initModels() {
        practiceTypeModels.clear();
        for (int i = 0; i < numOfCard; i++) {
            PracticeTypeModel practiceTypeModel = new PracticeTypeModel();
            practiceTypeModel.setPraticeType(practiceTypes[i]);
            practiceTypeModel.setTitle(titles[i]);
            practiceTypeModel.setImage(images[i]);
            practiceTypeModels.add(practiceTypeModel);
        }
    }
}
