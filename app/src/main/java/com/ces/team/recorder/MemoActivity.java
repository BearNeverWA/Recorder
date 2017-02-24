package com.ces.team.recorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MemoActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listMemo;
    Toolbar toolbarMemo;
    MemoAdapter memoAdapter;
    MemoDB memoDB;
    SQLiteDatabase dbReader;
    Cursor cursor;
    Button btnBackMain,btnAddNewMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        btnBackMain= (Button) findViewById(R.id.btn_back_activity_main);
        btnBackMain.setOnClickListener(this);
        btnAddNewMemo= (Button) findViewById(R.id.btn_new_memo);
        btnAddNewMemo.setOnClickListener(this);
        listMemo = (ListView) findViewById(R.id.list_memo);
        listMemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent=new Intent(MemoActivity.this,MemoDetail.class);
                intent.putExtra(MemoDB.ID,cursor.getInt(cursor.getColumnIndex(MemoDB.ID)));
                intent.putExtra(MemoDB.CONTENT,cursor.getString(cursor.getColumnIndex(MemoDB.CONTENT)));
                startActivity(intent);
            }
        });
        toolbarMemo = (Toolbar) findViewById(R.id.toolbar_memo);
        setSupportActionBar(toolbarMemo);
        toolbarMemo.setTitle("");
        memoDB = new MemoDB(this);
        dbReader = memoDB.getReadableDatabase();
    }



    public void selectDB() {
        cursor = dbReader.query(MemoDB.TABLE_NAME, null, null, null, null, null, MemoDB.ID+" desc");
        memoAdapter = new MemoAdapter(this, cursor);
        listMemo.setAdapter(memoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_activity_main:
                finish();
                break;
            case R.id.btn_new_memo:
                Intent intent=new Intent(MemoActivity.this,AddNewMemo.class);
                startActivity(intent);
        }
    }
}
