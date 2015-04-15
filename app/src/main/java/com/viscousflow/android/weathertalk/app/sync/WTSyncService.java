package com.viscousflow.android.weathertalk.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WTSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WTSyncAdapter sWTSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("WTSyncService", "onCreate - WTSyncService");
        synchronized (sSyncAdapterLock) {
            if (sWTSyncAdapter == null) {
                sWTSyncAdapter = new WTSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sWTSyncAdapter.getSyncAdapterBinder();
    }
}