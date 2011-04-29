package com.ben.software.activity;

import com.ben.software.Order.TargetColumns;
import com.ben.software.R;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TargetListActivity extends ListActivity {

    private static final String LOG_TAG = "activity.TargetList";

    private static final String[] PROJECTION = new String[] {
        TargetColumns._ID, TargetColumns.NAME, TargetColumns.STATE_ID,
        TargetColumns.STATE_NAME, TargetColumns.TYPE_ID, TargetColumns.TYPE_NAME,
        TargetColumns.CUSTOMER_COUNT,TargetColumns.FULL_NAME
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_list);
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG,"onResume");
        super.onResume();
        Cursor cursor = managedQuery(TargetColumns.CONTENT_URI, PROJECTION, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            setListAdapter(new SimpleCursorAdapter(
                    this,android.R.layout.simple_list_item_1, cursor,
                    new String[] {TargetColumns.FULL_NAME},
                    new int[] {android.R.id.text1}));
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(LOG_TAG, "onListItemClick - position: " + position + " id: " + id);
    }
}
