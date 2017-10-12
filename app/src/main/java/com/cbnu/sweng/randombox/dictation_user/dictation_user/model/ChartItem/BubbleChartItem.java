package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.ChartData;

public class BubbleChartItem extends ChartItem {

    private Typeface mTf;

    public BubbleChartItem(ChartData<?> cd, Context c) {
        super(cd);

        mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return TYPE_BUBBLECHART;
    }

    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_bubblechart, null);
            holder.chart = (BubbleChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);
        holder.chart.setTouchEnabled(true);

        // enable scaling and dragging
        holder.chart.setDragEnabled(true);
        holder.chart.setScaleEnabled(true);

        holder.chart.setMaxVisibleValueCount(200);
        holder.chart.setPinchZoom(true);

        Legend l = holder.chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(mTf);

        YAxis yl = holder.chart.getAxisLeft();
        yl.setTypeface(mTf);
        yl.setSpaceTop(30f);
        yl.setSpaceBottom(30f);
        yl.setDrawZeroLine(false);

        holder.chart.getAxisRight().setEnabled(false);

        XAxis xl = holder.chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(mTf);
        
        // set data
        holder.chart.setData((BubbleData) mChartData);
        
        // do not forget to refresh the chart
//        holder.chart.invalidate();
        holder.chart.animateX(750);
        holder.chart.animateY(700);

        return convertView;
    }
    
    private static class ViewHolder {
        BubbleChart chart;
    }
}
