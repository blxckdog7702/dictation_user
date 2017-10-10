package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.ChartDataAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.BarChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.BubbleChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.ChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.CombinedChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.LineChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem.PieChartItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseChartFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Demonstrates the use of charts inside a ListView. IMPORTANT: provide a
 * specific height attribute for the chart inside your listview-item
 * 
 * @author Philipp Jahoda
 */
public class StatsFragment extends BaseChartFragment {

    @BindView(R.id.lvStats) ListView lvStats;
    ArrayList<ChartItem> chartItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this, view);

        initModels();
        setupView();

        return view;
    }

    private void initModels() {
        chartItems = new ArrayList<>();
        chartItems.add(new PieChartItem(generatePieData(), getActivity()));
        chartItems.add(new CombinedChartItem(generateCombinedData(), getActivity()));
        chartItems.add(new BubbleChartItem(generateBubbleata(), getActivity()));
    }

    private void setupView() {
        ChartDataAdapter cda = new ChartDataAdapter(getActivity(), chartItems);
        lvStats.setAdapter(cda);
    }

}
