package com.ces.team.recorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Call Me Bear on 2017/2/20.
 */

public class CommonDB extends SQLiteOpenHelper {
    public static final String MEMO_TABLE_NAME = "memo";
    public static final String MEMO_CONTENT = "content";
    public static final String MEMO_ID = "_id";
    public static final String MEMO_TIME = "time_memo";
    public static final String BILL_TABLE_NAME = "bill";
    public static final String BILL_VALUE = "value";
    public static final String BILL_ID = "_id";
    public static final String BILL_BOOL = "bool";
    public static final String BILL_TYPE = "type";
    public static final String BILL_TIME = "time_bill";
    public static final String BILL_TIME_DAY ="day";
    public static final String BILL_TIME_MONTH="month";

    public CommonDB(Context context) {
        super(context, "memo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MEMO_TABLE_NAME + " ("
                + MEMO_ID + " integer primary key autoincrement,"
                + MEMO_CONTENT + " text not null,"
                + MEMO_TIME + " text not null)");

        db.execSQL("create table " + BILL_TABLE_NAME + " ("
                + BILL_ID + " integer primary key autoincrement,"
                + BILL_BOOL + " text not null,"
                + BILL_TYPE + " text not null,"
                + BILL_VALUE + " text not null,"
                + BILL_TIME + " text not null,"
                + BILL_TIME_MONTH + " text not null,"
                + BILL_TIME_DAY + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
