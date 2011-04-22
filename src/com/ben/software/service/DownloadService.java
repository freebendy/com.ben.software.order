package com.ben.software.service;

import com.ben.software.Order;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;

public class DownloadService extends Service {

    private static final String LOG_TAG = "service.DownloadService";

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {
        Log.v(LOG_TAG, "onCreate");
    }

    @Override
    public void onStart(Intent aIntent, int aStartId) {
        Log.v(LOG_TAG, "onStart");
    }

    @Override
    public int onStartCommand(Intent aIntent, int aFlags, int aStartId) {
        Log.v(LOG_TAG, "onStartCommand");
        return super.onStartCommand(aIntent, aFlags, aStartId);
    }

    @Override
    public IBinder onBind(Intent aIntent) {
        Log.v(LOG_TAG, "onBind - ");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent aIntent) {
        Log.v(LOG_TAG, "onUnbind");
        return false;
    }
    @Override
    public void onRebind(Intent aIntent) {
        Log.v(LOG_TAG, "onRebind");
    }

    @Override
    public void onDestroy() {
        Log.v(LOG_TAG, "onDestroy");
    }

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.v("BenTag.MessagerService", "handleMessage - Pid: " + Process.myPid() + " thread id: " + Thread.currentThread().getName());
            switch (msg.what) {
                case Order.MSG_SEND_ORDER:
                    break;
                case Order.MSG_QUERY_ORDERS:
                    break;
                case Order.MSG_UPDATE_DATA:
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

}
