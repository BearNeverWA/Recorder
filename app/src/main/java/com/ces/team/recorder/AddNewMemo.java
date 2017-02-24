package com.ces.team.recorder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

public class AddNewMemo extends AppCompatActivity implements View.OnClickListener {
    String flagGet;
    EditText etAddNewMemo;
    Button btnCancelAddNewMemo, btnConfirmAddNewMemo;
    Toolbar toolbar;
    MemoDB memoDB;
    SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_memo);
        flagGet = getIntent().getStringExtra("flag");
        etAddNewMemo = (EditText) findViewById(R.id.et_add_new_memo);
        btnCancelAddNewMemo = (Button) findViewById(R.id.btn_cancel_add_new_memo);
        btnCancelAddNewMemo.setOnClickListener(this);
        btnConfirmAddNewMemo = (Button) findViewById(R.id.btn_confirm_add_new_memo);
        btnConfirmAddNewMemo.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_add_new_memo);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        memoDB = new MemoDB(this);
        dbWriter = memoDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_add_new_memo:
                finish();
                break;
            case R.id.btn_confirm_add_new_memo:
                if (etAddNewMemo.getText().toString().length() == 0)
                    Toast.makeText(AddNewMemo.this, "备忘不能为空,请您输入备忘内容", Toast.LENGTH_SHORT).show();
                else {
                    addDB();
                    finish();
                }
                break;
        }
    }

    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(MemoDB.CONTENT, etAddNewMemo.getText().toString());
        cv.put(MemoDB.TIME, getTime());
        dbWriter.insert(MemoDB.TABLE_NAME, null, cv);
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = new Date();
        return format.format(date);
    }
}
