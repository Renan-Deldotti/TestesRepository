package com.example.customnotificationexample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_ID = "exampleChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationsChannels();
    }

    private void createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Example Channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Example Description");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
