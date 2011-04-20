package com.ben.software;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Order extends Activity {

    private static final int TEXT_SIZE= 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        List<Map> myData = new ArrayList<Map>();

        Map<String, String> map = new HashMap<String,String>();
        map.put("id", "A101");
        map.put("name", "π¨±£º¶∂°");
        map.put("count", "1");
        map.put("remark", "Œ¢¿±,÷Ó»Á÷Æ¿‡£¨‡Ë¿Ô≈æ¿≤");

        myData.add(map);

        ListView orderList = (ListView) findViewById(R.id.orderList);

        orderList.addHeaderView(createListHeader());
        orderList.setAdapter(new SimpleAdapter(this, (List) myData,
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
//        LinearLayout layout = new LinearLayout(this);
//
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//
//        TextView idHeader = new TextView(this);
//        idHeader.setLayoutParams(new LayoutParams(60,
//                LayoutParams.MATCH_PARENT));
//        idHeader.setTextSize(TEXT_SIZE);
//        idHeader.setText(R.string.cuisine_id);
//
//        TextView nameHeader = new TextView(this);
//        nameHeader.setLayoutParams(new LayoutParams(100,
//                LayoutParams.MATCH_PARENT));
//        nameHeader.setTextSize(TEXT_SIZE);
//        idHeader.setText(R.string.cuisine_name);
//
//        TextView countHeader = new TextView(this);
//        countHeader.setLayoutParams(new LayoutParams(40,
//                LayoutParams.MATCH_PARENT));
//        countHeader.setTextSize(TEXT_SIZE);
//        idHeader.setText(R.string.cuisine_count);
//
//        TextView remarkHeader = new TextView(this);
//        remarkHeader.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT));
//        remarkHeader.setTextSize(TEXT_SIZE);
//        idHeader.setText(R.string.cuisine_remark);
//
//        layout.addView(idHeader);
//        layout.addView(nameHeader);
//        layout.addView(countHeader);
//        layout.addView(remarkHeader);

//        return null;
    }
}
