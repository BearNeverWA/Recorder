package com.ces.team.recorder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoDetail extends AppCompatActivity implements View.OnClickListener {
    EditText etMemoDetail;
    Button btnSave, btnDelete, btnBack;
    String beforeChange;
    CommonDB memoDB;
    SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        memoDB = new CommonDB(this);
        dbWriter = memoDB.getWritableDatabase();
        btnSave = (Button) findViewById(R.id.btn_save_edit_memo);
        btnSave.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btn_delete_memo);
        btnDelete.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btn_back_memo_list);
        btnBack.setOnClickListener(this);
        beforeChange = getIntent().getStringExtra(CommonDB.MEMO_CONTENT);
        etMemoDetail = (EditText) findViewById(R.id.et_memo_detail);
        etMemoDetail.setText(beforeChange);
        etMemoDetail.requestFocus();
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_memo_list:
                finish();
                break;
            case R.id.btn_save_edit_memo:
                updateDB();
                finish();
                break;
            case R.id.btn_delete_memo:
                deleteDB();
                finish();
                break;
            default:
        }
    }

    public void updateDB() {
        ContentValues cv = new ContentValues();
        cv.put(CommonDB.MEMO_CONTENT, etMemoDetail.getText().toString());
        cv.put(CommonDB.MEMO_TIME, getTime());
        String[] args = {String.valueOf(getIntent().getIntExtra("_id",0))};
        dbWriter.update(CommonDB.MEMO_TABLE_NAME, cv, "_id=?", args);
    }

    public void deleteDB() {
        String[] args = {String.valueOf(getIntent().getIntExtra("_id",0))};
        dbWriter.delete(CommonDB.MEMO_TABLE_NAME, "_id=?", args);
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = new Date();
        return format.format(date);
    }

}
