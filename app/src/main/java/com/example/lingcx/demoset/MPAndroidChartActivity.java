package com.example.lingcx.demoset;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lcx on 2018/11/10.
 */
public class MPAndroidChartActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.horizontal_bar_chart)
    HorizontalBarChart mHorizontalBarChart;
    @BindView(R.id.chart1)
    LineChart mChart1;
    private List<ChartData> listData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_cahrt);
        ButterKnife.bind(this);
        listData.add(new ChartData("福州", 8));
        listData.add(new ChartData("厦门", 7));
        listData.add(new ChartData("泉州", 6));
        listData.add(new ChartData("漳州", 5));
        listData.add(new ChartData("宁德", 4));
        listData.add(new ChartData("龙岩", 3));
        listData.add(new ChartData("三明", 2));
        listData.add(new ChartData("莆田", 1));
        listData.add(new ChartData("漳州", 15));
        listData.add(new ChartData("衡阳", 13));
//        listData.add(new ChartData("2018-01", 8));
//        listData.add(new ChartData("2018-02", 65));
//        listData.add(new ChartData("2018-03", 31));
//        listData.add(new ChartData("2018-04", 168));
//        listData.add(new ChartData("2018-05", 68));
//        listData.add(new ChartData("2018-06", 65));
//        listData.add(new ChartData("2018-07", 999));
//        listData.add(new ChartData("2018-08", 45));
        initViewBarChart();
        initDataBarChart();
        //showBarChartAlong();
        //Test001();
//        initViewLineChart();
//        initDataLineChart();
    }

    private void initViewLineChart() {
        {   // // Chart Style // //

            // background color
            mChart1.setBackgroundColor(Color.WHITE);

            // disable description text
            mChart1.getDescription().setEnabled(false);

            // enable touch gestures
            mChart1.setTouchEnabled(true);

            // set listeners
            mChart1.setDrawGridBackground(false);

            // create marker to display box when values are selected
            //MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
            //mv.setChartView(chart);
            //chart.setMarker(mv);

            // enable scaling and dragging
            mChart1.setDragEnabled(true);
            mChart1.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            mChart1.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = mChart1.getXAxis();
            xAxis.setAxisMinimum(0f);
            xAxis.setTextSize(18);
            xAxis.setLabelRotationAngle(-30);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return listData.get((int) value).getName();
                }
            });
            // vertical grid lines
            //xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = mChart1.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            mChart1.getAxisRight().setEnabled(false);

            // horizontal grid lines
            //yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            //yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(0f);
        }


        {   // // Create Limit Lines // //
//            LimitLine llXAxis = new LimitLine(9f, "Index 10");
//            llXAxis.setLineWidth(4f);
//            llXAxis.enableDashedLine(10f, 10f, 0f);
//            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            llXAxis.setTextSize(10f);
//
//            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
//            ll1.setLineWidth(4f);
//            ll1.enableDashedLine(10f, 10f, 0f);
//            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//            ll1.setTextSize(10f);
//
//            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
//            ll2.setLineWidth(4f);
//            ll2.enableDashedLine(10f, 10f, 0f);
//            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            ll2.setTextSize(10f);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            //yAxis.addLimitLine(ll1);
            //yAxis.addLimitLine(ll2);
            //xAxis.addLimitLine(llXAxis);
        }
        mChart1.getLegend().setEnabled(false);
    }

    private void initDataLineChart() {
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < listData.size(); i++) {

            values.add(new Entry(i, listData.get(i).getCount()));
        }

        LineDataSet set1;

        if (mChart1.getData() != null &&
                mChart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart1.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            mChart1.getData().notifyDataChanged();
            mChart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            //set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLUE);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            //set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            //set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return mChart1.getAxisLeft().getAxisMinimum();
                }
            });

//            // set color of filled area
//            if (Utils.getSDKInt() >= 18) {
//                // drawables only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            mChart1.setData(data);
        }
    }

    private void initViewBarChart() {
        mHorizontalBarChart.setDrawBarShadow(false);

        mHorizontalBarChart.setDrawValueAboveBar(true);

        mHorizontalBarChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the mHorizontalBarChart, no values will be
        // drawn
        mHorizontalBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mHorizontalBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        mHorizontalBarChart.setDrawGridBackground(false);

        XAxis xl = mHorizontalBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setAxisMinimum(-1f);
        xl.setAxisLineWidth(1f);
        xl.setLabelCount(listData.size());
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d(TAG, "getFormattedValue: " + value);
                //return listData.get((int) value % listData.size()).getName();
                int index = (int) value;
                if(index < 0 || index >= listData.size()){
                    return "";
                }else{
                    return listData.get(index).getName() + "("+index+")";
                }
            }
        });

        YAxis yl = mHorizontalBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = mHorizontalBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        mHorizontalBarChart.setFitBars(true);
        mHorizontalBarChart.animateY(2500);

        mHorizontalBarChart.getLegend().setEnabled(false);
    }

    private void initDataBarChart() {
        float barWidth = 0.45f;
        // (0.45 + 0.03) * 2 + 0.04 = 1，即一个间隔为一组，包含两个柱图 -> interval per "group"
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < listData.size(); i++) {
            values.add(new BarEntry(i , listData.get(i).getCount()));
        }

        BarDataSet set1;
        if (mHorizontalBarChart.getData() != null &&
                mHorizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mHorizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mHorizontalBarChart.getData().notifyDataChanged();
            mHorizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return (int) value + "个";
                }
            });
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);

            mHorizontalBarChart.setData(data);
        }
    }

    //    /**
//     * 显示单条柱状图
//     */
    private void showBarChartAlong() {
        BarChartManager barChartManager = new BarChartManager(mHorizontalBarChart);

        List<BarEntry> yVals = new ArrayList<>();
        yVals.add(new BarEntry(1f, 80f));
        yVals.add(new BarEntry(2f, 50f));
        yVals.add(new BarEntry(3f, 60f));
        yVals.add(new BarEntry(4f, 60f));
        yVals.add(new BarEntry(5f, 70f));
        yVals.add(new BarEntry(6f, 80f));
        String label = "";
        List<String> xValues = new ArrayList<>();
        for (int i = 0; i > listData.size(); i++) {
            xValues.add(listData.get(i).getName());
        }
        barChartManager.showBarChart(yVals, xValues, label, Color.parseColor("#233454"));
    }
}
