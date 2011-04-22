package com.ben.software.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ben.software.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends Activity {

    private static final String LOG_TAG = "activity.Order";
    private List<Map<String, String>> mAutoCompleteList;
    private long mCurrentCuisineId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getText(R.string.order_title) + " - 17桌");
        setContentView(R.layout.order);

        mAutoCompleteList = createAutoCompleteList();
        SimpleAdapter adapter = new AutoCompleteAdapter(this, mAutoCompleteList,
                android.R.layout.simple_dropdown_item_1line,
                new String[] {"cuisine"}, new int[] {android.R.id.text1}, "id", "cuisine");

        AutoCompleteTextView input = (AutoCompleteTextView) findViewById(R.id.orderText);
        input.setThreshold(1);
        input.setAdapter(adapter);

        input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Log.v(LOG_TAG, "AdapterView.OnItemClickListener - onItemClick position: " + position + " id: " + id);
            }
        });

        input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                Log.v(LOG_TAG, "AdapterView.OnItemSelectedListener - onItemSelected position: " + position + " id: " + id);
                mCurrentCuisineId = id;
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.v(LOG_TAG, "AdapterView.OnItemSelectedListener - onNothingSelected");
            }
        });

        input.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence aS, int aStart, int aBefore,
                    int aCount) {
                Log.v(LOG_TAG, "TextWatcher.onNothingSelected");
                mCurrentCuisineId = -1;
            }

            public void beforeTextChanged(CharSequence aS, int aStart, int aCount,
                    int aAfter) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable aS) {
                // TODO Auto-generated method stub

            }
        });

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView aV, int aActionId,
                    KeyEvent aEvent) {
                Log.v(LOG_TAG, "TextView.OnEditorActionListener - onEditorAction");
                return false;
            }
        } );

        Button addButton = (Button) findViewById(R.id.addCuisine);
        addButton.setOnClickListener( new View.OnClickListener() {

            public void onClick(View aView) {
                if (mCurrentCuisineId != -1) {
                    // TODO: 根据ID查找，判断是否存在，添加
                } else {
                 // TODO: 根据名字查找，判断是否存在，添加
                }
            }
        });

        List<Map<String, String>> myData = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1001");
        map.put("name", "宫保鸡丁");
        map.put("count", "1");
        map.put("remark", "微辣");
        myData.add(map);

        ListView orderList = (ListView) findViewById(R.id.orderList);

        orderList.addHeaderView(createListHeader());
        orderList.setAdapter(new SimpleAdapter(this, (List<Map<String, String>>) myData,
                R.layout.orderlist_row, new String[] {"id", "name", "count", "remark"},
                new int[] {R.id.cuisineIdText, R.id.cuisineNameText,
                            R.id.cuisineCountText, R.id.cuisineRemarkText}));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu from XML resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.order_commit:
            Toast.makeText(this, "提交成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, FunctionListActivity.class);
            startActivity(intent);
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private View createListHeader() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View headerView = inflater.inflate(R.layout.orderlist_row, null);
        TextView idHeader = (TextView) headerView.findViewById(R.id.cuisineIdText);
        idHeader.setText(R.string.cuisine_id);
        TextView nameHeader = (TextView) headerView.findViewById(R.id.cuisineNameText);
        nameHeader.setText(R.string.cuisine_name);
        TextView countHeader = (TextView) headerView.findViewById(R.id.cuisineCountText);
        countHeader.setText(R.string.cuisine_count);
        TextView remarkHeader = (TextView) headerView.findViewById(R.id.cuisineRemarkText);
        remarkHeader.setText(R.string.cuisine_remark);
        remarkHeader.setGravity(Gravity.CENTER);
        return headerView;
    }

    private static List<Map<String, String>> createAutoCompleteList() {
        ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
        int beginId = 1001;
        for (String cuisine : CUISINES) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("cuisine", cuisine);
            map.put("id", Integer.toString(beginId++));
            list.add(map);
        }
        return list;
    }

    static final String[] CUISINES = new String[] {
        "1001 宫保鸡丁","1002 鱼香肉丝","1003 水煮鱼"
    };

}
