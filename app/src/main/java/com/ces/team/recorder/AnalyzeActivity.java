package com.ces.team.recorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AnalyzeActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    EditText etDate;
    Button btnQuery, btnQueryMonth, btnBack, btnQueryIn;
    PieChart pieChart;
    Toolbar toolbarAnalyze;
    CommonDB billDB;
    SQLiteDatabase dbReader;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
        etDate = (EditText) findViewById(R.id.et_day_of_month);
        btnQuery = (Button) findViewById(R.id.btn_query_confirm);
        btnQueryIn = (Button) findViewById(R.id.btn_query_in);
        btnQueryIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalyzeActivity.this, AnalyzeActivityIn.class);
                finish();
                startActivity(intent);
            }
        });
        btnBack = (Button) findViewById(R.id.btn_back_bill_list);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnQueryMonth = (Button) findViewById(R.id.btn_query_of_month);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate != null) {
                    String strInput = etDate.getText().toString();
                    if (strInput.length() > 0) {
                        int dataInput = Integer.parseInt(strInput);
                        if (dataInput >= 1 && dataInput <= getMaxDayOfMonth()) {
                            ArrayList<PieEntry> entries=new ArrayList<>();
                            if (strInput.length()>1)
                                str=getMonthFormat()+"-"+strInput;
                            else
                                str=getMonthFormat()+"-0"+strInput;
                            setEntries(entries,"day",str);
                            setData(entries);
                            init();
                        } else {
                            Toast.makeText(AnalyzeActivity.this, "数据不合法", Toast.LENGTH_SHORT).show();
                        }
                    } else if (strInput.length() == 0) {
                        Toast.makeText(AnalyzeActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnQueryMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDate.setVisibility(View.INVISIBLE);
                btnQuery.setVisibility(View.INVISIBLE);
                btnQueryMonth.setVisibility(View.INVISIBLE);
                ArrayList<PieEntry> entries = new ArrayList<>();
                setEntries(entries,"month",getMonthFormat());
                setData(entries);
                init();
            }
        });
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        toolbarAnalyze = (Toolbar) findViewById(R.id.toolbar_analyze_out);
        setSupportActionBar(toolbarAnalyze);
        toolbarAnalyze.setTitle("");

        billDB = new CommonDB(this);
        dbReader = billDB.getReadableDatabase();
        ArrayList<PieEntry> entries = new ArrayList<>();

        setEntries(entries,"day",getDayFormat());
        setData(entries);
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
        SpannableString spannableString = new SpannableString("轻松分析");
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

    private void setEntries(ArrayList<PieEntry> entries,String date,String selection) {
        float sumCloth = 0, sumFood = 0, sumHousing = 0, sumTraffic = 0, sumMedical = 0, sumOthers = 0;
        float sum = 0;
        DecimalFormat format = new DecimalFormat("0.00");
        //查询支出总和
        Cursor cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and bool=?", new String[]{selection, "2"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sum += result;
            }
        }

        //查询衣服支出总和
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and type=?", new String[]{selection, "服饰"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sumCloth += result;
            }
        }

        //查询饮食支出总和
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and type=?", new String[]{selection, "饮食"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sumFood += result;
            }
        }

        //查询住房支出总和
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and type=?", new String[]{selection, "住房"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sumHousing += result;
            }
        }

        //查询交通支出总和
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and type=?", new String[]{selection, "交通"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sumTraffic += result;
            }
        }

        //查询医疗支出总和
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and type=?", new String[]{selection, "医疗"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sumMedical += result;
            }
        }

        //查询其他支出总和
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, date+"=? and type=? and bool=?", new String[]{selection, "其他", "2"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                float result = cursor.getFloat(cursor.getColumnIndex("value"));
                sumOthers += result;
            }
        }

        Float result = Float.parseFloat(format.format((sumCloth / sum) * 100));
        entries.add(new PieEntry(result, "服饰"));
        result = Float.parseFloat(format.format((sumFood / sum) * 100));
        entries.add(new PieEntry(result, "饮食"));
        result = Float.parseFloat(format.format((sumHousing / sum) * 100));
        entries.add(new PieEntry(result, "住房"));
        result = Float.parseFloat(format.format((sumTraffic / sum) * 100));
        entries.add(new PieEntry(result, "交通"));
        result = Float.parseFloat(format.format((sumMedical / sum) * 100));
        entries.add(new PieEntry(result, "医疗"));
        result = Float.parseFloat(format.format((sumOthers / sum) * 100));
        entries.add(new PieEntry(result, "其他"));
    }

//    private void setEntriesMonth(ArrayList<PieEntry> entries) {
//        float sumCloth = 0, sumFood = 0, sumHousing = 0, sumTraffic = 0, sumMedical = 0, sumOthers = 0;
//        float sum = 0;
//        DecimalFormat format = new DecimalFormat("0.00");
//        //查询支出总和
//        Cursor cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and bool=?", new String[]{getMonthFormat(), "2"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sum += result;
//            }
//        }
//
//        //查询衣服支出总和
//        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and type=?", new String[]{getMonthFormat(), "服饰"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sumCloth += result;
//            }
//        }
//
//        //查询饮食支出总和
//        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and type=?", new String[]{getMonthFormat(), "饮食"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sumFood += result;
//            }
//        }
//
//        //查询住房支出总和
//        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and type=?", new String[]{getMonthFormat(), "住房"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sumHousing += result;
//            }
//        }
//
//        //查询交通支出总和
//        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and type=?", new String[]{getMonthFormat(), "交通"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sumTraffic += result;
//            }
//        }
//
//        //查询医疗支出总和
//        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and type=?", new String[]{getMonthFormat(), "医疗"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sumMedical += result;
//            }
//        }
//
//        //查询其他支出总和
//        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, new String[]{CommonDB.BILL_VALUE}, "month=? and type=? and bool=?", new String[]{getMonthFormat(), "其他", "2"}, null, null, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                float result = cursor.getFloat(cursor.getColumnIndex("value"));
//                sumOthers += result;
//            }
//        }
//
//        Float result = Float.parseFloat(format.format((sumCloth / sum) * 100));
//        entries.add(new PieEntry(result, "服饰"));
//        result = Float.parseFloat(format.format((sumFood / sum) * 100));
//        entries.add(new PieEntry(result, "饮食"));
//        result = Float.parseFloat(format.format((sumHousing / sum) * 100));
//        entries.add(new PieEntry(result, "住房"));
//        result = Float.parseFloat(format.format((sumTraffic / sum) * 100));
//        entries.add(new PieEntry(result, "交通"));
//        result = Float.parseFloat(format.format((sumMedical / sum) * 100));
//        entries.add(new PieEntry(result, "医疗"));
//        result = Float.parseFloat(format.format((sumOthers / sum) * 100));
//        entries.add(new PieEntry(result, "其他"));
//    }

    private String getDayFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    private String getMonthFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        return format.format(date);
    }

    private String getMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Date date = new Date();
        String monthResult;
        if (format.format(date).charAt(0) == '0') {
            monthResult = format.format(date).substring(1, 2);
        } else {
            monthResult = format.format(date);
        }
        return monthResult;
    }

    private int getMaxDayOfMonth() {
        int maxDay = 0;
        switch (Integer.valueOf(getMonth())) {
            case 1:
                maxDay = 31;
                break;
            case 2:
                if (isBigYear())
                    maxDay = 28;
                else {
                    maxDay = 27;
                }
                break;
            case 3:
                maxDay = 31;
                break;
            case 4:
                maxDay = 30;
                break;
            case 5:
                maxDay = 31;
                break;
            case 6:
                maxDay = 30;
                break;
            case 7:
                maxDay = 31;
                break;
            case 8:
                maxDay = 31;
                break;
            case 9:
                maxDay = 30;
                break;
            case 10:
                maxDay = 31;
                break;
            case 11:
                maxDay = 30;
                break;
            case 12:
                maxDay = 31;
                break;
        }
        return maxDay;
    }

    private boolean isBigYear() {
        SimpleDateFormat format = new SimpleDateFormat("YYYY");
        Date date = new Date();
        int year = Integer.valueOf(format.format(date));
        if ((year % 4 == 0 && year % 1000 != 0) || (year % 400 == 0)) {
            return true;
        } else
            return false;
    }
}
