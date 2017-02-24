package com.ces.team.recorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MemoDetail extends AppCompatActivity implements View.OnClickListener{
    Button btnSave,btnDelete,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        btnSave= (Button) findViewById(R.id.btn_save_edit_memo);
        btnSave.setOnClickListener(this);
        btnDelete= (Button) findViewById(R.id.btn_delete_memo);
        btnDelete.setOnClickListener(this);
        btnBack= (Button) findViewById(R.id.btn_back_activity_main);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_activity_main:
                finish();
                break;
            case R.id.btn_save_edit_memo:
                //这里添加修改数据库的操作,可以用DB的update方法
                if (isMemoChanged())
                    finish();
                else{
                    Toast.makeText(MemoDetail.this,"Database has updated,from MemoDetail.java",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete_memo:
                //数据库delete操作
                Toast.makeText(MemoDetail.this,"Delete completed",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    private boolean isMemoChanged(){
        double t=Math.random();
        return t>=0.5;
    }
}
