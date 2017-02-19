package com.ces.team.recorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MemoActivity extends AppCompatActivity {
    ListView listMemo;
    private String[] dataListMemo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    Toolbar toolbarMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        listMemo = (ListView) findViewById(R.id.list_memo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemoActivity.this, android.R.layout.simple_list_item_1, dataListMemo);
        listMemo.setAdapter(adapter);
        toolbarMemo = (Toolbar) findViewById(R.id.toolbar_memo);
        setSupportActionBar(toolbarMemo);
        toolbarMemo.setTitle("");
        toolbarMemo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_memo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_memo:
                //执行新建备忘的操作
                Toast.makeText(MemoActivity.this, "You clicked New Memo", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
