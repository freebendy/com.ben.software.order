package com.ben.software.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "db.DatabaseHelper";

    static final String DB_NAME = "orderdb";
    static final String DB_TARGETS_TABLE = "targets";
    static final String DB_CUISINES_TABLE = "cuisines";
    static final int DB_VERSION = 1;

    private static final String DB_TARGETS_TABLE_CREATE =
        "create table " + DB_TARGETS_TABLE
        + "(_id integer primary key autoincrement, "
        + "name text not null, typeid text not null );";

    private static final String DB_CUISINES_TABLE_CREATE =
        "create table " + DB_CUISINES_TABLE
        + "(_id integer primary key autoincrement, "
        + "name text not null, remark text);";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DB_TARGETS_TABLE_CREATE);
        db.execSQL(DB_CUISINES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TARGETS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_CUISINES_TABLE_CREATE);
        onCreate(db);
    }
}
