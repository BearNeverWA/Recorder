package com.ces.team.recorder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

    ListView listMemo;
    Toolbar toolbarMemo;
    MemoAdapter memoAdapter;
    MemoDB memoDB;
    SQLiteDatabase dbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        listMemo = (ListView) findViewById(R.id.list_memo);
        toolbarMemo = (Toolbar) findViewById(R.id.toolbar_memo);
        setSupportActionBar(toolbarMemo);
        toolbarMemo.setTitle("");
        toolbarMemo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoActivity.this.finish();
            }
        });

        memoDB = new MemoDB(this);
        dbReader = memoDB.getReadableDatabase();
    }


    //创建toolbar中的按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_memo, menu);
        return true;
    }

    //toolbar中的按钮监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_memo:
                //执行新建备忘的操作
                Dialog dialogNewMemo = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("新建备忘")
                        .setMessage("想要新建哪种备忘?")
                        .setPositiveButton("图文", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MemoActivity.this, AddNewMemo.class);
                                intent.putExtra("flag", 2);
                                startActivity(intent);

                            }
                        }).setNeutralButton("文字", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MemoActivity.this, AddNewMemo.class);
                                intent.putExtra("flag", 1);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MemoActivity.this, "操作取消", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialogNewMemo.show();
                break;
            default:
        }
        return true;
    }

    public void selectDB() {
        Cursor cursor = dbReader.query(MemoDB.TABLE_NAME, null, null, null, null, null, null);
        memoAdapter = new MemoAdapter(this, cursor);
        listMemo.setAdapter(memoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }
}
