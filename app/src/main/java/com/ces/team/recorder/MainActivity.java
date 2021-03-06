package com.ces.team.recorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCheckCount, btnJumpMemo, btnNewBill;
    ListView billList;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    SQLiteDatabase dbReader;
    CommonDB billDB;
    Cursor cursor;
    BillAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billDB = new CommonDB(this);
        dbReader = billDB.getReadableDatabase();
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
        btnNewBill = (Button) findViewById(R.id.btn_new_bill);
        btnNewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewBill.class);
                startActivity(intent);
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
        navigationView.setCheckedItem(R.id.about_application);
        //处理隐藏菜单的点击响应
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about_application:
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(MainActivity.this, AboutApplicationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.about_us:
                        drawerLayout.closeDrawers();
                        Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(i);
                        break;
                    default:
                }
                return true;
            }
        });
    }

    private void selectDB() {
        cursor = dbReader.query(CommonDB.BILL_TABLE_NAME, null, null, null, null, null, CommonDB.BILL_ID + " desc");
        billAdapter = new BillAdapter(this, cursor);
        billList.setAdapter(billAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
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
