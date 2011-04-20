package com.ben.software.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service {

    private static final String LOG_TAG = "service.DownloadService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent aIntent) {
        Log.v(LOG_TAG, "onBind - ");
        return null;
    }

}
