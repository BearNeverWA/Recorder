package com.ces.team.recorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AboutUsActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar= (Toolbar) findViewById(R.id.toolbar_about_us);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        btnBack= (Button) findViewById(R.id.btn_back_from_about_us);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
