package com.techsales.taskmanager.auth.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.techsales.taskmanager.R;

import java.util.Objects;

public class FirebaseListenerService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Uri customSoundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification);
        new NotificationHelper(this).createNotification(remoteMessage, customSoundUri);
    }

    private void sendNotificationMessageNormal(RemoteMessage remoteMessage) {
        int NOTIFY_TAG = 999;
        String CHANNEL_ID = "channcel_id";
        String clicked = Objects.requireNonNull(remoteMessage.getNotification()).getClickAction();
        Intent intent = new Intent(clicked);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), NOTIFY_TAG, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri customSoundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification);

        NotificationManager manager = (NotificationManager) getBaseContext().getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(Objects.requireNonNull(remoteMessage.getNotification()).getTitle())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(remoteMessage.getNotification().getBody()))
                .setAutoCancel(true)
                .setSound(customSoundUri)
                .setVibrate(new long[]{1000, 2000})
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentIntent(pendingIntent).build();
        if (manager != null)
            manager.notify(NOTIFY_TAG, notification);
    }

}
