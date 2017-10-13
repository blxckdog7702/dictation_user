
package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public abstract class BaseChartFragment extends Fragment {

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
    }

    public LineData generateLineData(ArrayList<Integer> values) {

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            entries.add(new Entry(i, values.get(i)));
        }

        LineDataSet d = new LineDataSet(entries, "");
        d.setLineWidth(2.5f);
        d.setCircleRadius(4.5f);
        d.setHighLightColor(Color.rgb(244, 117, 117));
        d.setDrawValues(false);

        LineData cd = new LineData(d);
        return cd;
    }

    public BarData generateBarData(ArrayList<Integer> values) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            entries.add(new BarEntry(i, values.get(i)));
        }

        BarDataSet d = new BarDataSet(entries, "");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    public PieData generatePieData(ArrayList<Integer> values, String[] marker) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            entries.add(new PieEntry(values.get(i), marker[i]));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(10f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        d.setValueLinePart1OffsetPercentage(80.f);
        d.setValueLinePart1Length(0.2f);
        d.setValueLinePart2Length(0.4f);
        d.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData cd = new PieData(d);
        return cd;
    }

    public BubbleData generateBubbleata(ArrayList<Integer> values) {

        ArrayList<BubbleEntry> entries = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            float val = values.get(i);
            float size = values.get(i);

            entries.add(new BubbleEntry(i, val, size));
        }

        BubbleDataSet d = new BubbleDataSet(entries, "Bubble DataSet");

        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setValueTextSize(10f);
        d.setValueTextColor(Color.WHITE);
        d.setHighlightCircleWidth(1.5f);
        d.setDrawValues(true);

        BubbleData bd = new BubbleData(d);
        return bd;
    }

    public CombinedData generateCombinedData(LineData lineData, BarData barData) {

        CombinedData d = new CombinedData();

        d.setData(lineData);
        d.setData(barData);
        d.setValueTypeface(mTfLight);

        return d;
    }
}
