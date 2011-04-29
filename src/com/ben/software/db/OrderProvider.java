package com.ben.software.db;

import java.util.HashMap;

import com.ben.software.Order;
import com.ben.software.Order.CuisineColumns;
import com.ben.software.Order.TargetColumns;
import com.ben.software.Order.TargetTypeColumns;
import com.ben.software.Order.TargetStateColumns;
import com.ben.software.Order.OrderColumns;
import com.ben.software.db.DatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class OrderProvider extends ContentProvider {

    private static final String LOG_TAG = "db.OrderProvider";

    private static final String ORDER_TABLES =
        DatabaseHelper.DB_ORDERS_TABLE + ","
        + DatabaseHelper.DB_CUISINES_TABLE + ","
        + DatabaseHelper.DB_TARGETS_TABLE + ","
        + DatabaseHelper.DB_TARGETSTATE_TABLE + ","
        + DatabaseHelper.DB_TARGETTYPE_TABLE;

//    private static final String TARGET_TABLES =
//        DatabaseHelper.DB_TARGETS_TABLE + ","
//        + DatabaseHelper.DB_TARGETSTATE_TABLE + ","
//        + DatabaseHelper.DB_TARGETTYPE_TABLE;
//
//    private static final String TARFER_SELECTION =
//        DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.STATE_ID + "="
//        + DatabaseHelper.DB_TARGETSTATE_TABLE + "." + TargetStateColumns._ID
//        + " AND "
//        + DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.TYPE_ID + "="
//        + DatabaseHelper.DB_TARGETTYPE_TABLE + "." + TargetTypeColumns._ID;

    private static final String TARGET_TABLES =
        DatabaseHelper.DB_TARGETS_TABLE + " INNER JOIN "
        + DatabaseHelper.DB_TARGETSTATE_TABLE + " ON "
        + DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.STATE_ID + "="
        + DatabaseHelper.DB_TARGETSTATE_TABLE + "." + TargetStateColumns._ID + ","
        + DatabaseHelper.DB_TARGETTYPE_TABLE + " ON "
        + DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.TYPE_ID + "="
        + DatabaseHelper.DB_TARGETTYPE_TABLE + "." + TargetTypeColumns._ID;

    private static final int CUISINES = 1;
    private static final int CUISINE_ID = 2;
    private static final int TARGETS = 3;
    private static final int TARGET_ID = 4;
    private static final int TARGETTYPE = 5;
    private static final int TARGETTYPE_ID = 6;
    private static final int TARGETSTATE = 7;
    private static final int TARGETSTATE_ID = 8;
    private static final int ORDERS = 9;
    private static final int ORDER_ID = 10;
    private static final int ORDER_TARGET_ID = 11;

    private static UriMatcher mUriMatcher;

    private static HashMap<String, String> mCuisinesProjectionMap;
    private static HashMap<String, String> mTargetStateProjectionMap;
    private static HashMap<String, String> mTargetTypeProjectionMap;
    private static HashMap<String, String> mTargetsProjectionMap;
    private static HashMap<String, String> mOrdersProjectionMap;

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        Log.v(LOG_TAG, "onCreate");
        mDatabaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri aUri, String[] aProjection, String aSelection,
            String[] aSelectionArgs, String aSortOrder) {
        Log.v(LOG_TAG, "query");

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] projection = aProjection;

        switch (mUriMatcher.match(aUri)) {
        case CUISINES:
            qb.setTables(DatabaseHelper.DB_CUISINES_TABLE);
            qb.setProjectionMap(mCuisinesProjectionMap);
            break;

        case CUISINE_ID:
            qb.setTables(DatabaseHelper.DB_CUISINES_TABLE);
            qb.setProjectionMap(mCuisinesProjectionMap);
            qb.appendWhere(CuisineColumns._ID + "=" + aUri.getPathSegments().get(1));
            break;

        case TARGETS:
            qb.setTables(TARGET_TABLES);
            qb.setProjectionMap(mTargetsProjectionMap);
//            if (aSelection != null && aSelection.length() > 0) {
//                aSelection += " AND " + TARFER_SELECTION;
//            } else {
//                aSelection = TARFER_SELECTION;
//            }
            if (projection == null) {
                projection = mTargetsProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        case TARGET_ID:
            qb.setTables(TARGET_TABLES);
            qb.setProjectionMap(mTargetsProjectionMap);
            qb.appendWhere(TargetColumns._ID + "=" + aUri.getPathSegments().get(1));
            if (projection == null) {
                projection = mTargetsProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        case TARGETSTATE:
            qb.setTables(DatabaseHelper.DB_TARGETSTATE_TABLE);
            qb.setProjectionMap(mTargetStateProjectionMap);
            break;

        case TARGETSTATE_ID:
            qb.setTables(DatabaseHelper.DB_TARGETSTATE_TABLE);
            qb.setProjectionMap(mTargetStateProjectionMap);
            qb.appendWhere(TargetStateColumns._ID + "=" + aUri.getPathSegments().get(1));
            break;

        case TARGETTYPE:
            qb.setTables(DatabaseHelper.DB_TARGETTYPE_TABLE);
            qb.setProjectionMap(mTargetTypeProjectionMap);
            break;

        case TARGETTYPE_ID:
            qb.setTables(DatabaseHelper.DB_TARGETTYPE_TABLE);
            qb.setProjectionMap(mTargetTypeProjectionMap);
            qb.appendWhere(TargetTypeColumns._ID + "=" + aUri.getPathSegments().get(1));
            break;

        case ORDERS:
            qb.setTables(ORDER_TABLES);
            qb.setProjectionMap(mOrdersProjectionMap);
            if (projection == null) {
                projection = mOrdersProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        case ORDER_ID:
            qb.setTables(ORDER_TABLES);
            qb.setProjectionMap(mOrdersProjectionMap);
            qb.appendWhere(OrderColumns._ID + "=" + aUri.getPathSegments().get(1));
            if (projection == null) {
                projection = mOrdersProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        case ORDER_TARGET_ID:
            qb.setTables(ORDER_TABLES);
            qb.setProjectionMap(mOrdersProjectionMap);
            qb.appendWhere(OrderColumns.TARGET_ID + "=" + aUri.getPathSegments().get(1));
            if (projection == null) {
                projection = mOrdersProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + aUri);
        }

        // Get the database and run the query
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor cursor = qb.query(db, projection, aSelection, aSelectionArgs, null, null, aSortOrder);

        // Tell the cursor what uri to watch, so it knows when its source data changes
        cursor.setNotificationUri(getContext().getContentResolver(), aUri);
        return cursor;
    }

    @Override
    public String getType(Uri aUri) {
        Log.v(LOG_TAG, "getType");
        String type = null;
        switch (mUriMatcher.match(aUri)) {
        case CUISINES:
            type = CuisineColumns.CONTENT_TYPE;
            break;

        case CUISINE_ID:
            type = CuisineColumns.CONTENT_ITEM_TYPE;
            break;

        case TARGETS:
            type = TargetColumns.CONTENT_TYPE;
            break;

        case TARGET_ID:
            type = TargetColumns.CONTENT_ITEM_TYPE;
            break;

        case TARGETSTATE:
            type = TargetStateColumns.CONTENT_TYPE;
            break;

        case TARGETSTATE_ID:
            type = TargetStateColumns.CONTENT_ITEM_TYPE;
            break;

        case TARGETTYPE:
            type = TargetTypeColumns.CONTENT_TYPE;
            break;

        case TARGETTYPE_ID:
            type = TargetTypeColumns.CONTENT_ITEM_TYPE;
            break;

        case ORDERS:
        case ORDER_TARGET_ID:
            type = OrderColumns.CONTENT_TYPE;
            break;

        case ORDER_ID:
            type = OrderColumns.CONTENT_ITEM_TYPE;
            break;

        default:
            throw new IllegalArgumentException("Unknown URI " + aUri);
        }
        return type;
    }

    @Override
    public Uri insert(Uri aUri, ContentValues aValues) {
        Log.v(LOG_TAG, "insert");
        // The local data is only readable
        return null;
    }

    @Override
    public int delete(Uri aUri, String aSelection, String[] aSelectionArgs) {
        Log.v(LOG_TAG, "delete");
        // The local data is only readable
        return 0;
    }

    @Override
    public int update(Uri aUri, ContentValues aValues, String aSelection,
            String[] aSelectionArgs) {
        Log.v(LOG_TAG, "update");
        // The local data is only readable
        return 0;
    }

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_CUISINES_TABLE, CUISINES);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_CUISINES_TABLE + "/#", CUISINE_ID);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_TARGETS_TABLE, TARGETS);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_TARGETS_TABLE + "/#", TARGET_ID);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_TARGETSTATE_TABLE, TARGETSTATE);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_TARGETSTATE_TABLE + "/#", TARGETSTATE_ID);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_TARGETTYPE_TABLE, TARGETTYPE);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_TARGETTYPE_TABLE + "/#", TARGETTYPE_ID);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_ORDERS_TABLE, ORDERS);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_ORDERS_TABLE + "/#", ORDER_ID);
        mUriMatcher.addURI(Order.AUTHORITY, DatabaseHelper.DB_ORDERS_TABLE + "/" + OrderColumns.TARGET_ID + "/#", ORDER_TARGET_ID);

        mCuisinesProjectionMap = new HashMap<String, String>();
        mCuisinesProjectionMap.put(CuisineColumns._ID, CuisineColumns._ID);
        mCuisinesProjectionMap.put(CuisineColumns.NAME, CuisineColumns.NAME);
        mCuisinesProjectionMap.put(CuisineColumns.CODE, CuisineColumns.CODE);
        mCuisinesProjectionMap.put(CuisineColumns.PRICE, CuisineColumns.PRICE);
        mCuisinesProjectionMap.put(CuisineColumns.DISCOUNT, CuisineColumns.DISCOUNT);
        mCuisinesProjectionMap.put(CuisineColumns.REMARK, CuisineColumns.REMARK);

        mTargetStateProjectionMap = new HashMap<String, String>();
        mTargetStateProjectionMap.put(TargetStateColumns._ID, TargetStateColumns._ID);
        mTargetStateProjectionMap.put(TargetStateColumns.NAME, TargetStateColumns.NAME);

        mTargetTypeProjectionMap = new HashMap<String, String>();
        mTargetTypeProjectionMap.put(TargetTypeColumns._ID, TargetTypeColumns._ID);
        mTargetTypeProjectionMap.put(TargetTypeColumns.NAME, TargetTypeColumns.NAME);

        mTargetsProjectionMap = new HashMap<String, String>();
        mTargetsProjectionMap.put(TargetColumns._ID, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns._ID);
        mTargetsProjectionMap.put(TargetColumns.NAME, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.NAME);
        mTargetsProjectionMap.put(TargetColumns.TYPE_ID, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.TYPE_ID);
        mTargetsProjectionMap.put(TargetColumns.STATE_ID, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.STATE_ID);
        mTargetsProjectionMap.put(TargetColumns.CUSTOMER_COUNT, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.CUSTOMER_COUNT);
        mTargetsProjectionMap.put(TargetColumns.TYPE_NAME, DatabaseHelper.DB_TARGETTYPE_TABLE + "." + TargetTypeColumns.NAME + " AS " + TargetColumns.TYPE_NAME);
        mTargetsProjectionMap.put(TargetColumns.STATE_NAME, DatabaseHelper.DB_TARGETSTATE_TABLE + "." + TargetStateColumns.NAME + " AS " + TargetColumns.STATE_NAME);
        mTargetsProjectionMap.put(TargetColumns.FULL_NAME, DatabaseHelper.DB_TARGETTYPE_TABLE + "." + TargetTypeColumns.NAME + " || " + DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.NAME + " AS " + TargetColumns.FULL_NAME);

        mOrdersProjectionMap = new HashMap<String, String>();
        mOrdersProjectionMap.put(OrderColumns._ID, DatabaseHelper.DB_ORDERS_TABLE + "." + OrderColumns._ID);
        mOrdersProjectionMap.put(OrderColumns.TARGET_ID, DatabaseHelper.DB_ORDERS_TABLE + "." + OrderColumns.TARGET_ID);
        mOrdersProjectionMap.put(OrderColumns.CUISINE_ID, DatabaseHelper.DB_ORDERS_TABLE + "." + OrderColumns.CUISINE_ID);
        mOrdersProjectionMap.put(OrderColumns.COUNT, DatabaseHelper.DB_ORDERS_TABLE + "." + OrderColumns.COUNT);
        mOrdersProjectionMap.put(OrderColumns.REMARK, DatabaseHelper.DB_ORDERS_TABLE + "." + OrderColumns.REMARK);
        mOrdersProjectionMap.put(OrderColumns.TARGET_NAME, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.NAME + " AS " + OrderColumns.TARGET_NAME);
        mOrdersProjectionMap.put(OrderColumns.CUISINE_NAME, DatabaseHelper.DB_CUISINES_TABLE + "." + CuisineColumns.NAME + " AS " + OrderColumns.CUISINE_NAME);
        mOrdersProjectionMap.put(OrderColumns.TARGET_TYPE_NAME, DatabaseHelper.DB_TARGETTYPE_TABLE + "." + TargetTypeColumns.NAME + " AS " + OrderColumns.TARGET_TYPE_NAME);
        mOrdersProjectionMap.put(OrderColumns.TARGET_STATE_NAME, DatabaseHelper.DB_TARGETSTATE_TABLE + "." + TargetStateColumns.NAME + " AS " + OrderColumns.TARGET_STATE_NAME);
    }

}
