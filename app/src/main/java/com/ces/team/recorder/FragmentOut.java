package com.ces.team.recorder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class FragmentOut extends Fragment {
    Button btnOutCloth, btnOutFood, btnOutHousing, btnOutTraffic, btnOutMedical, btnOutOthers,btnSaveOut;
    EditText etOutValue;
    View view;
    String type="服饰";
    SQLiteDatabase dbWriter;
    CommonDB billDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_out, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView() {
        etOutValue = (EditText) getActivity().findViewById(R.id.et_value_out);

        btnSaveOut= (Button) getActivity().findViewById(R.id.btn_confirm_add_out);
        btnSaveOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=etOutValue.getText().toString();
                if (!str.equals("")){
                    if (isInvalid(str)){
                        String handleResult=HandleData(str);
                        addDB(handleResult);
                        getActivity().finish();
                    }else {
                        Toast.makeText(getActivity(),"数据不合法",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnOutCloth = (Button) getActivity().findViewById(R.id.btn_out_cloth);
        btnOutCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="服饰";
                btnOutCloth.setBackgroundResource(R.drawable.shape_round_textview_red);
                btnOutCloth.setTextColor(Color.WHITE);
                btnOutFood.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutFood.setTextColor(Color.rgb(255, 64, 64));
                btnOutHousing.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutHousing.setTextColor(Color.rgb(255, 64, 64));
                btnOutTraffic.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutTraffic.setTextColor(Color.rgb(255, 64, 64));
                btnOutMedical.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutMedical.setTextColor(Color.rgb(255, 64, 64));
                btnOutOthers.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutOthers.setTextColor(Color.rgb(255, 64, 64));
            }
        });

        btnOutFood = (Button) getActivity().findViewById(R.id.btn_out_food);
        btnOutFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="饮食";
                btnOutFood.setBackgroundResource(R.drawable.shape_round_textview_red);
                btnOutFood.setTextColor(Color.WHITE);
                btnOutCloth.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutCloth.setTextColor(Color.rgb(255, 64, 64));
                btnOutHousing.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutHousing.setTextColor(Color.rgb(255, 64, 64));
                btnOutTraffic.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutTraffic.setTextColor(Color.rgb(255, 64, 64));
                btnOutMedical.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutMedical.setTextColor(Color.rgb(255, 64, 64));
                btnOutOthers.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutOthers.setTextColor(Color.rgb(255, 64, 64));
            }
        });

        btnOutHousing = (Button) getActivity().findViewById(R.id.btn_out_housing);
        btnOutHousing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="住房";
                btnOutHousing.setBackgroundResource(R.drawable.shape_round_textview_red);
                btnOutHousing.setTextColor(Color.WHITE);
                btnOutFood.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutFood.setTextColor(Color.rgb(255, 64, 64));
                btnOutCloth.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutCloth.setTextColor(Color.rgb(255, 64, 64));
                btnOutTraffic.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutTraffic.setTextColor(Color.rgb(255, 64, 64));
                btnOutMedical.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutMedical.setTextColor(Color.rgb(255, 64, 64));
                btnOutOthers.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutOthers.setTextColor(Color.rgb(255, 64, 64));
            }
        });

        btnOutTraffic = (Button) getActivity().findViewById(R.id.btn_out_traffic);
        btnOutTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="交通";
                btnOutTraffic.setBackgroundResource(R.drawable.shape_round_textview_red);
                btnOutTraffic.setTextColor(Color.WHITE);
                btnOutFood.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutFood.setTextColor(Color.rgb(255, 64, 64));
                btnOutHousing.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutHousing.setTextColor(Color.rgb(255, 64, 64));
                btnOutCloth.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutCloth.setTextColor(Color.rgb(255, 64, 64));
                btnOutMedical.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutMedical.setTextColor(Color.rgb(255, 64, 64));
                btnOutOthers.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutOthers.setTextColor(Color.rgb(255, 64, 64));
            }
        });

        btnOutMedical = (Button) getActivity().findViewById(R.id.btn_out_medical);
        btnOutMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="医疗";
                btnOutMedical.setBackgroundResource(R.drawable.shape_round_textview_red);
                btnOutMedical.setTextColor(Color.WHITE);
                btnOutFood.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutFood.setTextColor(Color.rgb(255, 64, 64));
                btnOutHousing.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutHousing.setTextColor(Color.rgb(255, 64, 64));
                btnOutTraffic.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutTraffic.setTextColor(Color.rgb(255, 64, 64));
                btnOutCloth.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutCloth.setTextColor(Color.rgb(255, 64, 64));
                btnOutOthers.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutOthers.setTextColor(Color.rgb(255, 64, 64));
            }
        });

        btnOutOthers = (Button) getActivity().findViewById(R.id.btn_out_others);
        btnOutOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="其他";
                btnOutOthers.setBackgroundResource(R.drawable.shape_round_textview_red);
                btnOutOthers.setTextColor(Color.WHITE);
                btnOutFood.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutFood.setTextColor(Color.rgb(255, 64, 64));
                btnOutHousing.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutHousing.setTextColor(Color.rgb(255, 64, 64));
                btnOutTraffic.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutTraffic.setTextColor(Color.rgb(255, 64, 64));
                btnOutMedical.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutMedical.setTextColor(Color.rgb(255, 64, 64));
                btnOutCloth.setBackgroundResource(R.drawable.shape_round_textview_rwhite);
                btnOutCloth.setTextColor(Color.rgb(255, 64, 64));
            }
        });
        billDB=new CommonDB(getActivity());
        dbWriter=billDB.getWritableDatabase();
    }

    private void addDB(String s){
        ContentValues cv=new ContentValues();
        cv.put(CommonDB.BILL_TYPE,type);
        cv.put(CommonDB.BILL_BOOL,"2");
        cv.put(CommonDB.BILL_VALUE,s);
        cv.put(CommonDB.BILL_TIME,getTime());
        dbWriter.insert(CommonDB.BILL_TABLE_NAME,null,cv);
    }

    private String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
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
