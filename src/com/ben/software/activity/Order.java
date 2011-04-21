package com.ben.software.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ben.software.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Order extends Activity {

    private static final String LOG_TAG = "activity.Order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

//        String[] cuisines = CUISINES_MAP.keySet().toArray(new String[] {});
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, cuisines);

        List<Map<String, String>> list = createAutoCompleteList();
        SimpleAdapter adapter = new AutoCompleteAdapter(this, list,
                android.R.layout.simple_dropdown_item_1line,
                new String[] {"cuisine"}, new int[] {android.R.id.text1}, "id", "cuisine");

        AutoCompleteTextView input = (AutoCompleteTextView) findViewById(R.id.orderText);
        input.setThreshold(1);
        input.setAdapter(adapter);

        input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(LOG_TAG, "AdapterView.OnItemClickListener - onItemClick position: " + position + " id: " + id);
            }
        });

        input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v(LOG_TAG, "AdapterView.OnItemSelectedListener - onItemSelected position: " + position + " id: " + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.v(LOG_TAG, "AdapterView.OnItemSelectedListener - onNothingSelected");
            }
        });



        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView aV, int aActionId,
                    KeyEvent aEvent) {
                // TODO Auto-generated method stub
                return false;
            }

        } );

        List<Map<String, String>> myData = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String,String>();
        map.put("id", "A101");
        map.put("name", "π¨±£º¶∂°");
        map.put("count", "1");
        map.put("remark", "Œ¢¿±,÷Ó»Á÷Æ¿‡£¨‡Ë¿Ô≈æ¿≤");

        myData.add(map);

        ListView orderList = (ListView) findViewById(R.id.orderList);

        orderList.addHeaderView(createListHeader());
        orderList.setAdapter(new SimpleAdapter(this, (List<Map<String, String>>) myData,
                R.layout.orderlist_row, new String[] {"id", "name", "count", "remark"},
                new int[] {R.id.cuisineIdText, R.id.cuisineNameText,
                            R.id.cuisineCountText, R.id.cuisineRemarkText}));
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
        int beginId = 11;
        for (String cuisine : CUISINES) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("cuisine", cuisine);
            map.put("id", Integer.toString(beginId++));
            list.add(map);
        }
        return list;
    }

    private static final Map<String,Integer> CUISINES_MAP  =
        new HashMap<String, Integer>() {
        {
            put("A0001 π¨±£º¶∂°", 12);
            put("A0002 ”„œ„»‚Àø", 15);
            put("A0003 ÀÆ÷Û”„", 17);
        }
    };

    static final String[] CUISINES = new String[] {
        "A0001 π¨±£º¶∂°","A0002 ”„œ„»‚Àø","A0003 ÀÆ÷Û”„"
    };

    static final int[] CUISINES_ID = new int[] {12, 15, 17};

}
