package com.ben.software.activity;

import com.ben.software.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FunctionListActivity extends ListActivity {

    private static final String LOG_TAG = "activity.FunctionList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] itemStrings = getResources().getStringArray(R.array.function_list_items);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemStrings));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(LOG_TAG, "onListItemClick - position: " + position + " id: " + id);

        if (id == 0) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        } else if (id == 1) {
//            Intent intent = new Intent(this, TargetListActivity.class);
            Intent intent = new Intent(this, TargetGridActivity.class);
            startActivity(intent);
        } else if (id == 2) {
            Intent intent = new Intent(this, SystemPrefereneActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Function is not implemented yet!", Toast.LENGTH_LONG).show();
        }
    }
}
