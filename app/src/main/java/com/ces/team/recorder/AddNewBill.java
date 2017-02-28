package com.ces.team.recorder;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewBill extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarAddNewBill;
    Button btnCancel, btnSave;
    RadioGroup radioGroupAddNewBill;
    RadioButton rbIn, rbOut;
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);
        initView();
        initViewPager();
    }

    private void initView() {
        toolbarAddNewBill = (Toolbar) findViewById(R.id.toolbar_add_new_bill);
        setSupportActionBar(toolbarAddNewBill);
        toolbarAddNewBill.setTitle("");

        btnCancel = (Button) findViewById(R.id.btn_cancel_add_new_bill);
        btnCancel.setOnClickListener(this);
//        btnSave = (Button) findViewById(R.id.btn_confirm_add_new_bill);
//        btnSave.setOnClickListener(this);

        radioGroupAddNewBill = (RadioGroup) findViewById(R.id.radio_group);
        rbIn = (RadioButton) findViewById(R.id.rb_in);
        rbOut = (RadioButton) findViewById(R.id.rb_out);
        radioGroupAddNewBill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_in:
                        viewPager.setCurrentItem(0, false);
                        rbIn.setTextColor(Color.rgb(142, 229, 238));
                        rbOut.setTextColor(Color.rgb(193, 193, 193));
                        break;
                    case R.id.rb_out:
                        viewPager.setCurrentItem(1, false);
                        rbIn.setTextColor(Color.rgb(193, 193, 193));
                        rbOut.setTextColor(Color.rgb(142, 229, 238));
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void initViewPager() {
        FragmentIn fragmentIn = new FragmentIn();
        FragmentOut fragmentOut = new FragmentOut();

        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(fragmentIn);
        fragmentArrayList.add(fragmentOut);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentArrayList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroupAddNewBill.check(R.id.rb_in);
                        rbIn.setTextColor(Color.rgb(142, 229, 238));
                        rbOut.setTextColor(Color.rgb(193, 193, 193));
                        break;
                    case 1:
                        radioGroupAddNewBill.check(R.id.rb_out);
                        rbIn.setTextColor(Color.rgb(193, 193, 193));
                        rbOut.setTextColor(Color.rgb(142, 229, 238));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_add_new_bill:
                finish();
                break;
//            case R.id.btn_confirm_add_new_bill:
//                switch (viewPager.getCurrentItem()){
//                    case 0:
//
//                        break;
//                    case 1:
//
//                        break;
//                }
//                break;
            default:
        }
    }
}
