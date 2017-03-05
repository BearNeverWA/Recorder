package com.ces.team.recorder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Call Me Bear on 2017/2/27.
 */

public class FragmentIn extends Fragment {
    Button btnInSalary, btnInPartTime, btnInOthers, btnSaveIn;
    EditText etInValue;
    View view;
    String type = "工资";
    SQLiteDatabase dbWriter;
    CommonDB billDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_in, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView() {
        btnInSalary = (Button) getActivity().findViewById(R.id.btn_in_salary);
        btnInSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "工资";
                btnInSalary.setBackgroundResource(R.drawable.shape_round_textview_green);
                btnInSalary.setTextColor(Color.WHITE);
                btnInPartTime.setBackgroundResource(R.drawable.shape_round_textview_gwhite);
                btnInPartTime.setTextColor(Color.rgb(51, 204, 102));
                btnInOthers.setBackgroundResource(R.drawable.shape_round_textview_gwhite);
                btnInOthers.setTextColor(Color.rgb(51, 204, 102));
            }
        });
        btnInPartTime = (Button) getActivity().findViewById(R.id.btn_in_part_time);
        btnInPartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "兼职";
                btnInPartTime.setBackgroundResource(R.drawable.shape_round_textview_green);
                btnInPartTime.setTextColor(Color.WHITE);
                btnInSalary.setBackgroundResource(R.drawable.shape_round_textview_gwhite);
                btnInSalary.setTextColor(Color.rgb(51, 204, 102));
                btnInOthers.setBackgroundResource(R.drawable.shape_round_textview_gwhite);
                btnInOthers.setTextColor(Color.rgb(51, 204, 102));
            }
        });
        btnInOthers = (Button) getActivity().findViewById(R.id.btn_in_others);
        btnInOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "其他";
                btnInOthers.setBackgroundResource(R.drawable.shape_round_textview_green);
                btnInOthers.setTextColor(Color.WHITE);
                btnInPartTime.setBackgroundResource(R.drawable.shape_round_textview_gwhite);
                btnInPartTime.setTextColor(Color.rgb(51, 204, 102));
                btnInSalary.setBackgroundResource(R.drawable.shape_round_textview_gwhite);
                btnInSalary.setTextColor(Color.rgb(51, 204, 102));
            }
        });

        etInValue = (EditText) getActivity().findViewById(R.id.et_value_in);
        etInValue.requestFocus();

        btnSaveIn = (Button) getActivity().findViewById(R.id.btn_confirm_add_in);
        btnSaveIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etInValue.getText().toString();
                if (!str.equals("")) {
                    if (isInvalid(str)) {
                        String handleResult=HandleData(str);
                        addDB(handleResult);
                        getActivity().finish();

                    } else {
                        Toast.makeText(getActivity(), "数据不合法", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        billDB=new CommonDB(getActivity());
        dbWriter=billDB.getWritableDatabase();
    }

    private void addDB(String s){
        ContentValues cv=new ContentValues();
        cv.put(CommonDB.BILL_TYPE,type);
        cv.put(CommonDB.BILL_BOOL,"1");
        cv.put(CommonDB.BILL_VALUE,s);
        cv.put(CommonDB.BILL_TIME,getTime());
        cv.put(CommonDB.BILL_TIME_MONTH,getMonth());
        cv.put(CommonDB.BILL_TIME_DAY,getDay());
        dbWriter.insert(CommonDB.BILL_TABLE_NAME,null,cv);
    }

    private String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date=new Date();
        return format.format(date);
    }

    private String getMonth(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
        Date date=new Date();
        return format.format(date);
    }

    private String getDay(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        return format.format(date);
    }

    private String HandleData(String s) {
        int position = s.indexOf(".");
        if (position == 0)
            return "0" + s;
        if (position == s.length() - 1)
            return s.substring(0, s.length() - 1);
        return s;
    }

    private boolean isInvalid(String s) {
        if (s.equals("0"))
            return false;
        int num = 0;
        char c = '.';
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == c)
                num++;
        }
        if (num > 1)
            return false;
        return true;
    }
}
