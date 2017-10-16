
package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseDrawerActivity;
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

/**
 * Baseclass of all Activities of the Demo Application.
 * 
 * @author Philipp Jahoda
 */
public abstract class BaseChartActivity extends BaseActivity {

    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    public LineData generateLineData() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d = new LineDataSet(entries, "New DataSet " +  ", (1)");
        d.setLineWidth(2.5f);
        d.setCircleRadius(4.5f);
        d.setHighLightColor(Color.rgb(244, 117, 117));
        d.setDrawValues(false);

        LineData cd = new LineData(d);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    public BarData generateBarData() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet ");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    public PieData generatePieData() {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(10f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setValueLinePart1OffsetPercentage(80.f);
        d.setValueLinePart1Length(3.2f);
        d.setValueLinePart2Length(1.4f);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        d.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData cd = new PieData(d);
        return cd;
    }

    public BubbleData generateBubbleata() {

        ArrayList<BubbleEntry> entries = new ArrayList<BubbleEntry>();

        for (int i = 0; i < 4; i++) {
            float val = (float) (Math.random() * 70);
            float size = (float) (Math.random() * 70);

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

    public CombinedData generateCombinedData() {

        CombinedData d = new CombinedData();

        d.setData(generateLineData());
        d.setData(generateBarData());
        d.setValueTypeface(mTfLight);

        return d;
    }
}
