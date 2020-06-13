package com.example.notificationsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;
    private EditText editTextTitle, editTextMessage;

    private MediaSessionCompat mediaSessionCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        editTextMessage = findViewById(R.id.edit_text_message);
        editTextTitle = findViewById(R.id.edit_text_title);

        mediaSessionCompat = new MediaSessionCompat(this,"tag");
    }

    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        /*Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        */

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.dog_placeholder);

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_adb)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent) // Intent to open this activity
                .setAutoCancel(true) // Auto dismiss when clicked
                //.addAction(R.drawable.ic_adb, "Toast", actionIntent) // Button (max3)
                .setOnlyAlertOnce(true) // Ony alert once then don't make any sound
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.dog_placeholder);

        // If using channel use CATEGORY_TRANSPORT

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_adb)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_thumb_down, "Dislike", null) // Should pass a pending intent
                .addAction(R.drawable.ic_baseline_arrow_back_ios_24, "Previous", null)
                .addAction(R.drawable.ic_baseline_play_arrow_24, "Play", null)
                .addAction(R.drawable.ic_baseline_navigate_next_24, "Pause", null)
                .addAction(R.drawable.ic_thumb_up, "Like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                .setMediaSession(mediaSessionCompat.getSessionToken()))
                .setSubText("Sub text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        notificationManagerCompat.notify(2, notification);
    }
}