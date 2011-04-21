package com.ben.software.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.Filter;
import android.widget.SimpleAdapter;

public class AutoCompleteAdapter extends SimpleAdapter {

    private ArrayList<Map<String, String>> mAllData, mDataShown;

    private String mIdKey;

    private String mContentKey;

    public AutoCompleteAdapter(Context aContext,
            List<Map<String, String>> aData, int aResource,
            String[] aFrom, int[] aTo, String aIdKey, String aContentKey) {
        super(aContext, aData, aResource, aFrom, aTo);
        mAllData = (ArrayList<Map<String, String>>) aData;
        mIdKey = aIdKey;
        mContentKey = aContentKey;
//        mDataShown = (ArrayList<Map<String, String>>) mAllData.clone();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getItem(int aPosition) {
        Map<String,String> item = (Map<String, String>) super.getItem(aPosition);
        String content = item.get(mContentKey);
        return content;
    }

    @SuppressWarnings("unchecked")
    @Override
    public long getItemId(int aPosition) {
        Map<String,String> item = (Map<String, String>) super.getItem(aPosition);
        String idStr = item.get(mIdKey);
        long id = Long.parseLong(idStr) ;
        return id;
    }

//    @Override
//    public Filter getFilter() {
//        Filter nameFilter = new Filter() {
//
//            @SuppressWarnings("unchecked")
//            @Override
//            public String convertResultToString(Object aResultValue) {
//                return ((Map<String, String>) aResultValue).get("cuisine");
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence aConstraint) {
//                FilterResults filterResults = new FilterResults();
//
//                if (mDataShown == null) {
//                    mDataShown = new ArrayList<Map<String, String>>(mAllData);
//                }
//
//                if (aConstraint == null || aConstraint.length() ==0) {
//                    filterResults.values = mDataShown;
//                    filterResults.count = mDataShown.size();
//                } else {
//                    String constraintLC = aConstraint.toString().toLowerCase();
//                    int count = mDataShown.size();
//
//                    ArrayList<Map<String, String>> newValues =
//                        new ArrayList<Map<String, String>>(count);
//
//                    for (int i = 0; i < count; ++i) {
//                        Map<String, String> map = mDataShown.get(i);
//                        if (map != null) {
//                            String content = map.get(mContentKey);
//                            if (content.toLowerCase().contains(constraintLC)) {
//                                newValues.add(map);
//                            }
//                        }
//                    }
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence aConstraint,
//                    FilterResults aResults) {
//                if (aResults != null && aResults.count > 0) {
//                    notifyDataSetChanged();
//                } else {
//                    notifyDataSetInvalidated();
//                }
//            }
//
//        };
//
//        return nameFilter;
//    }

}
