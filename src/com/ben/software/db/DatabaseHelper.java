package com.ben.software.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "db.DatabaseHelper";

    static final String DB_NAME = "orderdb";
    static final String DB_TARGETTYPE_TABLE = "targettype";
    static final String DB_TARGETS_TABLE = "targets";
    static final String DB_CUISINES_TABLE = "cuisines";
    static final int DB_VERSION = 1;

    private static final String DB_TARGETTYPE_TABLE_CREATE =
        "create table " + DB_TARGETTYPE_TABLE
        + "(_id integer primary key autoincrement, "
        + "name text not null );";

    private static final String DB_TARGETS_TABLE_CREATE =
        "create table " + DB_TARGETS_TABLE
        + "(_id integer primary key autoincrement, "
        + "name text not null, typeid text not null );";

    private static final String DB_CUISINES_TABLE_CREATE =
        "create table " + DB_CUISINES_TABLE
        + "(_id integer primary key autoincrement, "
        + "name text not null, remark text);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_TARGETTYPE_TABLE_CREATE);
        db.execSQL(DB_TARGETS_TABLE_CREATE);
        db.execSQL(DB_CUISINES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TARGETTYPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TARGETS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_CUISINES_TABLE);
        onCreate(db);
    }

    // Just for test data.
    public void addTestData() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        for (String cuisine : CUISINESNAME) {
            ContentValues values = new ContentValues();
            values.put("name", cuisine);
            values.put("remark", "Just for test.");
            db.insert(DB_CUISINES_TABLE, null, values);
        }

        for (String targetType : TARGETYPENAME) {
            ContentValues values = new ContentValues();
            values.put("name", targetType);
            db.insert(DB_TARGETTYPE_TABLE, null, values);
        }

        for (int i = 1; i < 6; ++i) {
            ContentValues values1 = new ContentValues();
            values1.put("typeid", 1);
            values1.put("name", "包" + Integer.toString(i));
            db.insert(DB_TARGETS_TABLE, null, values1);

            ContentValues values2 = new ContentValues();
            values1.put("typeid", 2);
            values1.put("name", "桌" + Integer.toString(i));
            db.insert(DB_TARGETS_TABLE, null, values2);

            ContentValues values3 = new ContentValues();
            values1.put("typeid", 3);
            values1.put("name", "临时" + Integer.toString(i));
            db.insert(DB_TARGETS_TABLE, null, values3);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        close();
    }

    public void clearTestData() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + DB_CUISINES_TABLE);
        db.execSQL("DELETE FROM " + DB_TARGETS_TABLE);
        db.execSQL("DELETE FROM " + DB_TARGETTYPE_TABLE);
        close();
    }

    static final String[] CUISINESNAME = new String[] {
        "宫保鸡丁","鱼香肉丝","水煮鱼"
    };

    static final String[] TARGETYPENAME = new String[] {
        "包间","桌子","其他"
    };
}
