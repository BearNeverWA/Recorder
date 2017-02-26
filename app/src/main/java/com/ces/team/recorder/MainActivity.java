package com.ces.team.recorder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCheckCount, btnJumpMemo,btnNewBill;
    ListView billList;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCheckCount = (Button) findViewById(R.id.btn_check_count);
        btnCheckCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnalyzeActivity.class);
                startActivity(intent);
            }
        });
        btnJumpMemo = (Button) findViewById(R.id.btn_jump_memo);
        btnJumpMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                startActivity(intent);
            }
        });
        btnNewBill= (Button) findViewById(R.id.btn_new_bill);
        btnNewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"New",Toast.LENGTH_SHORT).show();
            }
        });

        //list的绑定和数据传输
        billList = (ListView) findViewById(R.id.list_bill);


        //Toolbar的设置
        Toolbar toolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        toolbarMain.setTitle("");
        setSupportActionBar(toolbarMain);

        //DrawerLayout的设置
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        //向右滑动屏幕也可以打开隐藏菜单
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_18dp);
        }

        //隐藏菜单的内容设置
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.test_one);
        //处理隐藏菜单的点击响应
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.test_one:
                        drawerLayout.closeDrawers();
                        Toast.makeText(MainActivity.this, "You Clicked TestOne", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.test_two:
                        drawerLayout.closeDrawers();
                        Toast.makeText(MainActivity.this, "You Clicked TestTwo", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.test_three:
                        drawerLayout.closeDrawers();
                        Toast.makeText(MainActivity.this, "You Clicked TestThree", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

}
