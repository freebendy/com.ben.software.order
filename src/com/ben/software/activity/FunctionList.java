package com.ben.software.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FunctionList extends ListActivity {

    private static final String LOG_TAG = "activity.FunctionList";

    private static String[] mStrings = new String[] {"点菜", "查桌", "系统菜单", "更新数据"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(LOG_TAG, "onListItemClick - position: " + position + " id: " + id);

        if (id == 0) {
            Intent intent = new Intent(this, Order.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Function is not implemented yet!", Toast.LENGTH_SHORT);
        }
    }
}
