package com.example.jobschedulerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int FAKE_JOBSCHEDULER_ID = 123;
    private static final long PERIODIC = 15 * 60 * 1000; // 15 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scheduleJob(View v){
        ComponentName componentName = new ComponentName(this,ExampleJobService.class);
        JobInfo info = new JobInfo.Builder(FAKE_JOBSCHEDULER_ID,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(PERIODIC)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS){
            Log.e(TAG, "Job scheduled");
        }else {
            Log.e(TAG, "Job scheduling failed");
        }
    }

    public void cancelJob(View v){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(FAKE_JOBSCHEDULER_ID);
        Log.e(TAG, "Job cancelled");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}