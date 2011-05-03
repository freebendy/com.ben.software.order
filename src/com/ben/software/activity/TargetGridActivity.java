package com.ben.software.activity;

import com.ben.software.R;
import com.ben.software.Order.TargetColumns;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

public class TargetGridActivity extends Activity {
    private static final String LOG_TAG = "activity.TargetGrid";

    private GridView mGridView = null;

    private static final String[] PROJECTION = new String[] {
        TargetColumns._ID, TargetColumns.NAME, TargetColumns.STATE_ID,
        TargetColumns.STATE_NAME, TargetColumns.TYPE_ID, TargetColumns.TYPE_NAME,
        TargetColumns.CUSTOMER_COUNT,TargetColumns.FULL_NAME
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_grid);

        mGridView = (GridView) findViewById(R.id.targetGrid);
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG,"onResume");
        super.onResume();
        Cursor cursor = managedQuery(TargetColumns.CONTENT_URI, PROJECTION, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            mGridView.setAdapter(new SimpleCursorAdapter(
                    this, R.layout.target_grid_item, cursor,
                    new String[] {TargetColumns.FULL_NAME, TargetColumns.STATE_NAME},
                    new int[] {R.id.gridItemNameText, R.id.gridItemStateText}));
        }
    }
}
