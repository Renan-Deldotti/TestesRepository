package com.example.foregroundserviceexample;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class TestMusicService extends Service {

    public static final String CHANGE_STRING = "com.example.foregroundserviceexample.CHANGE_STRING";
    public static final String STRING_EXTRA = "com.example.foregroundserviceexample.VALUE";
    private static final String TAG = TestMusicService.class.getSimpleName();
    private MediaSessionCompat mediaSessionCompat;
    private ViewModel viewModel;
    private String fromViewModel = "";
    private String fromReceiver = "";
    private Thread runBackground = null;
    private Observer<String> stringObserver = null;
    private boolean shouldStopRunBackground = false;

    private BroadcastReceiver changeStringReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            fromReceiver = intent.getStringExtra(STRING_EXTRA);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mediaSessionCompat = new MediaSessionCompat(this, TAG);
        viewModel = new ViewModel();
        stringObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fromViewModel = s;
            }
        };

        IntentFilter intentFilter = new IntentFilter(CHANGE_STRING);
        registerReceiver(changeStringReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.dog_placeholder);

        // If using channel use CATEGORY_TRANSPORT
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle("Titulo da musica")
                .setContentText("Cantor")
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_thumb_down, "Dislike", null) // Should pass a pending intent
                .addAction(R.drawable.ic_arrow_left, "Previous", null)
                .addAction(R.drawable.ic_play, "Play", null)
                .addAction(R.drawable.ic_arrow_right, "Pause", null)
                .addAction(R.drawable.ic_thumb_up, "Like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        startForeground(1, notification);


        viewModel.getStringLiveData().observeForever(stringObserver);

        runBackground = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (shouldStopRunBackground){
                        Log.e(TAG, "Stopping thread...");
                        return;
                    }
                    SystemClock.sleep(1000);
                    Log.e(TAG, fromViewModel+" "+fromReceiver+" "+i+" running...");
                }
            }
        });

        runBackground.start();

        // Works on ui thread
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel.getStringLiveData().hasObservers()){
            viewModel.getStringLiveData().removeObserver(stringObserver);
            Log.e(TAG, "Observer removed.");
        }
        shouldStopRunBackground = true;
        unregisterReceiver(changeStringReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
