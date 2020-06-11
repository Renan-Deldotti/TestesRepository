package com.example.jobschedulerexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService {

    private static final String TAG = ExampleJobService.class.getSimpleName();
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "Job started");
        doBackgroundWork(params);
        // For fast operations should use false
        // for operations that take longer use true
        return true;
    }

    private void doBackgroundWork(final JobParameters params){
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i< 10; i++){
                    Log.e(TAG, "run: " +i);
                    if (jobCancelled){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.e(TAG, "Job finished");
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "Job cancelled before completion");
        jobCancelled = true;
        // Whether to reschedule the job or not
        return true;
    }
}
