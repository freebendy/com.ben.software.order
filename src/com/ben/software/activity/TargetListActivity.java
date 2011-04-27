package com.ben.software.activity;

import com.ben.software.R;
import com.ben.software.Order.OrderColumns;

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
        OrderColumns._ID, OrderColumns.TARGETID, "targetname"
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
        Cursor cursor = managedQuery(OrderColumns.CONTENT_URI, PROJECTION, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            setListAdapter(new SimpleCursorAdapter(
                    this,android.R.layout.simple_list_item_1, cursor,
                    new String[] {"targetname"},
                    new int[] {android.R.id.text1}));
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(LOG_TAG, "onListItemClick - position: " + position + " id: " + id);
    }
}
