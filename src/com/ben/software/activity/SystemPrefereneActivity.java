package com.ben.software.activity;

import com.ben.software.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SystemPrefereneActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.system_preference);
    }

}
