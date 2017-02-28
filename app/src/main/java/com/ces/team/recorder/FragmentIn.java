package com.ces.team.recorder;

import android.content.Intent;
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

/**
 * Created by Call Me Bear on 2017/2/27.
 */

public class FragmentIn extends Fragment {
    Button btnInSalary, btnInPartTime, btnInOthers,btnSaveIn;
    EditText etInValue;
    View view;

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

        btnSaveIn= (Button) getActivity().findViewById(R.id.btn_confirm_add_in);
        btnSaveIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=etInValue.getText().toString();
                if (!str.equals("")){
                    Toast.makeText(getActivity(),"Your data is "+str,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public int whichChecked() {
        return 0;
    }


}
