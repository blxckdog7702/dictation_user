
package com.cbnu.sweng.randombox.dictation_user.dictation_user.model.ChartItem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class CombinedChartItem extends ChartItem {

    private Typeface mTf;

    public CombinedChartItem(ChartData<?> cd, Context c) {
        super(cd);

        mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return TYPE_COMBINEDCHART;
    }

    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_combinedchart, null);
            holder.chart = (CombinedChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        // holder.chart.setValueTypeface(mTf);

        holder.chart.getDescription().setEnabled(false);
        holder.chart.setBackgroundColor(Color.WHITE);
        holder.chart.setDrawGridBackground(false);
        holder.chart.setDrawBarShadow(false);
        holder.chart.setHighlightFullBarEnabled(false);

        // Draw bars behind lines
        holder.chart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE,
                CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE,
                CombinedChart.DrawOrder.SCATTER
        });

        Legend l = holder.chart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = holder.chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = holder.chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return mMonths[(int) value % mMonths.length];
//            }
//        });

        xAxis.setAxisMaximum(mChartData.getXMax() + 0.25f);

        // set data
        holder.chart.setData((CombinedData) mChartData);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart.animateX(750);
        holder.chart.animateY(700);

        return convertView;
    }

    private static class ViewHolder {
        CombinedChart chart;
    }
}
