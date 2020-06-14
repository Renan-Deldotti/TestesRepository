package com.example.foregroundserviceexample;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class TestMusicService extends Service {

    private static final String TAG = TestMusicService.class.getSimpleName();
    private MediaSessionCompat mediaSessionCompat;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaSessionCompat = new MediaSessionCompat(this, TAG);
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

        // Works on ui thread
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
