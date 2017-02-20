package com.ces.team.recorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Call Me Bear on 2017/2/20.
 */

public class MemoDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "memo";
    public static final String CONTENT = "content_memo";
    public static final String PATH = "path";
    public static final String ID = "_id_memo";
    public static final String TIME = "time_memo";

    public MemoDB(Context context) {
        super(context, "memo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement,"
                + CONTENT + " text not null,"
                + TIME + " text not null,"
                + PATH + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
