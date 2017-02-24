package com.ces.team.recorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Call Me Bear on 2017/2/24.
 */

public class BillDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME="bill";
    public static final String ID="_id";
    public static final String VALUE="value";
    public static final String TIME="time";
    public BillDB(Context context) {
        super(context, "bill", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + ID + " integer primary key autoincrement,"
                + VALUE + " text not null,"
                + TIME + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
