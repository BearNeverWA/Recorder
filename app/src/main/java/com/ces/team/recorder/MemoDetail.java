package com.ces.team.recorder;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemoDetail extends AppCompatActivity implements View.OnClickListener{
    EditText etMemoDetail;
    Button btnSave,btnDelete,btnBack;
    String beforeChange;
    boolean isChanged;
    MemoDB memeDB;
    SQLiteDatabase dbWriter;
    private static final String TAG = "MemoDetail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        isChanged=false;
        btnSave= (Button) findViewById(R.id.btn_save_edit_memo);
        btnSave.setOnClickListener(this);
        btnDelete= (Button) findViewById(R.id.btn_delete_memo);
        btnDelete.setOnClickListener(this);
        btnBack= (Button) findViewById(R.id.btn_back_memo_list);
        btnBack.setOnClickListener(this);
        beforeChange=getIntent().getStringExtra(MemoDB.CONTENT);
        etMemoDetail= (EditText) findViewById(R.id.et_memo_detail);
        etMemoDetail.setText(beforeChange);
        etMemoDetail.requestFocus();
        etMemoDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isChanged=true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String afterChange=etMemoDetail.getText().toString();
                if (afterChange.equals(beforeChange))
                    isChanged=false;
                else {
                    isChanged=true;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_memo_list:
                finish();
                break;
            case R.id.btn_save_edit_memo:
                //这里添加修改数据库的操作,可以用DB的update方法
                if (isChanged)

                break;
            case R.id.btn_delete_memo:
                //数据库delete操作
                Toast.makeText(MemoDetail.this,"Delete completed",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    public void updateDB(){

    }
    
}
