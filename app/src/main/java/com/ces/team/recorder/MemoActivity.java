package com.ces.team.recorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MemoActivity extends AppCompatActivity {
    Button btnReturn;
    ListView listMemo;
    private String[] dataListMemo={"1","2","3","4","5","6","7","8","9","10","11","12"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        listMemo= (ListView) findViewById(R.id.list_memo);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MemoActivity.this,android.R.layout.simple_list_item_1,dataListMemo);
        listMemo.setAdapter(adapter);
        btnReturn= (Button) findViewById(R.id.return_last_ac);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
