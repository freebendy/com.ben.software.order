package com.ben.software.db;

import java.util.HashMap;

import com.ben.software.Order;
import com.ben.software.Order.CuisineColumns;
import com.ben.software.Order.TargetColumns;
import com.ben.software.Order.TargetTypeColumns;
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

    private static final int CUISINES = 1;
    private static final int CUISINE_ID = 2;
    private static final int TARGETS = 3;
    private static final int TARGET_ID = 4;
    private static final int TARGETTYPES = 5;
    private static final int TARGETTYPE_ID = 6;

    private static UriMatcher mUriMatcher;

    private static HashMap<String, String> mCuisinesProjectionMap;
    private static HashMap<String, String> mTargetsProjectionMap;
    private static HashMap<String, String> mTargetTypeProjectionMap;

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
            qb.setTables(DatabaseHelper.DB_TARGETS_TABLE + " INNER JOIN "
                    + DatabaseHelper.DB_TARGETTYPE_TABLE);
            qb.setProjectionMap(mTargetsProjectionMap);
            if (projection == null ) {
                projection = mTargetsProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        case TARGET_ID:
            qb.setTables(DatabaseHelper.DB_TARGETS_TABLE + " INNER JOIN "
                    + DatabaseHelper.DB_TARGETTYPE_TABLE);
            qb.setProjectionMap(mTargetsProjectionMap);
            qb.appendWhere(TargetColumns._ID + "=" + aUri.getPathSegments().get(1));
            if (projection == null ) {
                projection = mTargetsProjectionMap.keySet().toArray(new String[] {});
            }
            break;

        case TARGETTYPES:
            qb.setTables(DatabaseHelper.DB_TARGETTYPE_TABLE);
            qb.setProjectionMap(mTargetTypeProjectionMap);
            break;

        case TARGETTYPE_ID:
            qb.setTables(DatabaseHelper.DB_TARGETTYPE_TABLE);
            qb.setProjectionMap(mTargetTypeProjectionMap);
            qb.appendWhere(TargetTypeColumns._ID + "=" + aUri.getPathSegments().get(1));
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

        case TARGETTYPES:
            type = TargetTypeColumns.CONTENT_TYPE;
            break;

        case TARGETTYPE_ID:
            type = TargetTypeColumns.CONTENT_ITEM_TYPE;
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
        mUriMatcher.addURI(Order.AUTHORITY, "cuisines", CUISINES);
        mUriMatcher.addURI(Order.AUTHORITY, "cuisines/#", CUISINE_ID);
        mUriMatcher.addURI(Order.AUTHORITY, "targets", TARGETS);
        mUriMatcher.addURI(Order.AUTHORITY, "targets/#", TARGET_ID);
        mUriMatcher.addURI(Order.AUTHORITY, "targettype", TARGETTYPES);
        mUriMatcher.addURI(Order.AUTHORITY, "targettype/#", TARGETTYPE_ID);

        mCuisinesProjectionMap = new HashMap<String, String>();
        mCuisinesProjectionMap.put(CuisineColumns._ID, CuisineColumns._ID);
        mCuisinesProjectionMap.put(CuisineColumns.NAME, CuisineColumns.NAME);
        mCuisinesProjectionMap.put(CuisineColumns.REMARK, CuisineColumns.REMARK);

        mTargetTypeProjectionMap = new HashMap<String, String>();
        mTargetTypeProjectionMap.put(TargetTypeColumns._ID, TargetTypeColumns._ID);
        mTargetTypeProjectionMap.put(TargetTypeColumns.NAME, TargetTypeColumns.NAME);

        mTargetsProjectionMap = new HashMap<String, String>();
        mTargetsProjectionMap.put(TargetColumns._ID, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns._ID);
        mTargetsProjectionMap.put(TargetColumns.NAME, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.NAME);
        mTargetsProjectionMap.put(TargetColumns.TYPEID, DatabaseHelper.DB_TARGETS_TABLE + "." + TargetColumns.TYPEID);
        mTargetsProjectionMap.put("typename", DatabaseHelper.DB_TARGETTYPE_TABLE + ".name as typename");
    }

}