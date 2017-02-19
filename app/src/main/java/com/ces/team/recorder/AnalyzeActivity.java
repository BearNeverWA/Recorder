package com.ces.team.recorder;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AnalyzeActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    EditText etDate;
    Button btnQuery, btnQueryMonth;
    PieChart pieChart;
    int dataInput;
    Toolbar toolbarAnalyze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
        etDate = (EditText) findViewById(R.id.et_day_of_month);
        btnQuery = (Button) findViewById(R.id.btn_query_confirm);
        btnQueryMonth = (Button) findViewById(R.id.btn_query_of_month);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate != null) {
                    String strInput = etDate.getText().toString();
                    if (strInput.length() > 0) {
                        dataInput = Integer.parseInt(strInput);
                        if (dataInput > 31 || dataInput < 1)
                            Toast.makeText(AnalyzeActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                            // 在这里执行查询数据库的操作
                            // 最好使用新线程操作
                        else if (dataInput <= 31 && dataInput >= 1)
                            Toast.makeText(AnalyzeActivity.this, dataInput + "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnQueryMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取月份然后查询数据库
                //一样最好使用新线程操作
            }
        });
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        toolbarAnalyze= (Toolbar) findViewById(R.id.toolbar_analyze);
        setSupportActionBar(toolbarAnalyze);
        toolbarAnalyze.setTitle("");
        toolbarAnalyze.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnalyzeActivity.this.finish();
            }
        });
        init();
    }


    private void init() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        //设置中间显示的内容
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        //触摸旋转
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        //创建时绕XY轴旋转
        pieChart.animateXY(1400, 1400);

        //监听变化
        pieChart.setOnChartValueSelectedListener(this);

        //数据模拟，在这里传入数据
        ArrayList<PieEntry> entries = new ArrayList<>();
        //new PieEntry里传入两个参数,一个是百分比(int),一个是内容(String)
        entries.add(new PieEntry(40, "优秀"));
        entries.add(new PieEntry(30, "良好"));
        entries.add(new PieEntry(20, "及格"));
        entries.add(new PieEntry(10, "不及格"));

        setData(entries);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);

        //输入标签样式
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
    }

    @Override
    public void onNothingSelected() {
    }

    //设置中间显示的文字
    private SpannableString generateCenterSpannableText() {
        SpannableString spannableString = new SpannableString("Java_艾敏组\n倾情呈现");
        //spannableString.setSpan(new StyleSpan(Typeface.NORMAL), spannableString.length(), spannableString.length() - 15, 0);
        return spannableString;
    }

    //设置数据获取
    public void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        //刷新
        pieChart.invalidate();
    }
}
