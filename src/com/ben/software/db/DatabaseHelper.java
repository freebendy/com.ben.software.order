package com.ben.software.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "db.DatabaseHelper";

    static final String DB_NAME = "orderdb";
    static final String DB_TARGETS_TABLE = "targets";
    static final String DB_TARGETTYPE_TABLE = "targettype";
    static final String DB_TARGETSTATE_TABLE = "targetstate";
    static final String DB_CUISINES_TABLE = "cuisines";
    static final String DB_ORDERS_TABLE = "orders";
    static final int DB_VERSION = 1;

    private static final String DB_TARGETS_TABLE_CREATE =
        "create table " + DB_TARGETS_TABLE
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
        + "name TEXT NOT NULL, type_id INTEGER NOT NULL, "
        + "state_id INTEGER NOT NULL, customer_count INTEGER DEFAULT 0);";

    private static final String DB_TARGETTYPE_TABLE_CREATE =
        "create table " + DB_TARGETTYPE_TABLE
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
        + "name TEXT NOT NULL UNIQUE );";

    private static final String DB_TARGETSTATE_TABLE_CREATE =
        "create table " + DB_TARGETSTATE_TABLE
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
        + "name TEXT NOT NULL UNIQUE );";

    private static final String DB_CUISINES_TABLE_CREATE =
        "create table " + DB_CUISINES_TABLE
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
        + "name TEXT NOT NULL UNIQUE, code TEXT UNIQUE, "
        + "price REAL NOT NULL DEFAULT 1, discount REAL NOT NULL DEFAULT 1, "
        + "remark TEXT);";

    private static final String DB_ORDERS_TABLE_CREATE =
        "create table " + DB_ORDERS_TABLE
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
        + "target_id INTEGER NOT NULL, cuisine_id INTEGER NOT NULL, "
        + "count INTEGER NOT NULL DEFAULT 1, remark TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_TARGETS_TABLE_CREATE);
        db.execSQL(DB_TARGETTYPE_TABLE_CREATE);
        db.execSQL(DB_TARGETSTATE_TABLE_CREATE);
        db.execSQL(DB_CUISINES_TABLE_CREATE);
        db.execSQL(DB_ORDERS_TABLE_CREATE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            // Enable foreign key constraints
//            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TARGETS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TARGETTYPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TARGETSTATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_CUISINES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_ORDERS_TABLE_CREATE);
        onCreate(db);
    }

    // Just for test data.
    public void addTestData() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        int codeBegin = 1000;
        ArrayList<Long> cuisineIds = new ArrayList<Long>();
        for (String cuisine : CUISINESNAME) {
            ContentValues values = new ContentValues();
            values.put("name", cuisine);
            values.put("code", Integer.toString(codeBegin++));
            values.put("price", 18);
            values.put("discount", 1);
            values.put("remark", "Just for test.");
            cuisineIds.add(db.insert(DB_CUISINES_TABLE, null, values));
        }

        ArrayList<Long> typeIds = new ArrayList<Long>();
        for (String type : TARGETTYPE) {
            ContentValues values = new ContentValues();
            values.put("name", type);
            typeIds.add(db.insert(DB_TARGETTYPE_TABLE, null, values));
        }

        ArrayList<Long> stateIds = new ArrayList<Long>();
        for (String state : TARGETSTATE) {
            ContentValues values = new ContentValues();
            values.put("name", state);
            stateIds.add(db.insert(DB_TARGETSTATE_TABLE, null, values));
        }

        for (int i = 1; i < 4; ++i) {
            ContentValues values1 = new ContentValues();
            values1.put("type_id", typeIds.get(0));
            values1.put("state_id", stateIds.get(2));
            values1.put("name", Integer.toString(i));
            values1.put("customer_count", 3);
            long rowid = db.insert(DB_TARGETS_TABLE, null, values1);
            if (rowid != -1) {
                ContentValues valuesOrder = new ContentValues();
                valuesOrder.put("target_id", rowid);
                valuesOrder.put("cuisine_id", cuisineIds.get(i - 1));
                db.insert(DB_ORDERS_TABLE, null, valuesOrder);
            }

            ContentValues values2 = new ContentValues();
            values2.put("type_id", typeIds.get(1));
            values2.put("state_id", stateIds.get(0));
            values2.put("name", Integer.toString(i));
            db.insert(DB_TARGETS_TABLE, null, values2);

            ContentValues values3 = new ContentValues();
            values3.put("type_id", typeIds.get(2));
            values3.put("state_id", stateIds.get(0));
            values3.put("name", Integer.toString(i));
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
        db.execSQL("DELETE FROM " + DB_TARGETSTATE_TABLE);
        db.execSQL("DELETE FROM " + DB_ORDERS_TABLE);
        close();
    }

    static final String[] CUISINESNAME = new String[] {
        "宫保鸡丁","鱼香肉丝","水煮鱼","蜀国烤鱼","上汤娃娃菜"
    };

    static final String[] TARGETTYPE = new String[] {
        "包间","桌子","其他"
    };

    static final String[] TARGETSTATE = new String[] {
        "空闲","开台","点菜","结帐"
    };

}
