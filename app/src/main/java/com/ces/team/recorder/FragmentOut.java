package com.ces.team.recorder;

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

/**
 * Created by Call Me Bear on 2017/2/27.
 */

public class FragmentOut extends Fragment {
    Button btnOutCloth, btnOutFood, btnOutHousing, btnOutTraffic, btnOutMedical, btnOutOthers,btnSaveOut;
    EditText etOutValue;
    View view;

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
                Toast.makeText(getActivity(),"Your data is "+ str,Toast.LENGTH_SHORT).show();
            }
        });

        btnOutCloth = (Button) getActivity().findViewById(R.id.btn_out_cloth);
        btnOutCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }

    public int whichChecked() {
        return 0;
    }
}
