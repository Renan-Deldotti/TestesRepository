package com.example.jobintentserviceexample;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService {

    private static final String TAG = "ExampleJobIntentService";

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ExampleJobIntentService.class, 123, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.e(TAG, "onHandleWork");

        String input = intent.getStringExtra("inputExtra");

        for (int i = 0; i < 10; i++) {
            Log.e(TAG, input + " - " + i);

            if (isStopped()) {
                return;
            }

            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.e(TAG, "onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
