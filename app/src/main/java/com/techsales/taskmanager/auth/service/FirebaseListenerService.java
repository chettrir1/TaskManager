package com.techsales.taskmanager.auth.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;

import java.util.Objects;

public class FirebaseListenerService extends FirebaseMessagingService {
    private static final String NOTIFICATION_CHANNEL_ID = "101";
    private static final String NOTIFICATION_CHANNEL_NAME = "taskManagerChannel";
    private Uri NOTIFICATION_SOUND_URI = Uri.parse("android.resource://" + this.getPackageName() + "/raw/notification");
    private long[] VIBRATE_PATTERN = {0, 500};

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification(remoteMessage);
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /*for android oreo and greater*/
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (NOTIFICATION_SOUND_URI != null) {
                // Creating an Audio Attribute
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                        .build();

                //Configure Notification Channel
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription(Objects.requireNonNull(remoteMessage.getNotification()).getBody());
                channel.setSound(NOTIFICATION_SOUND_URI, audioAttributes);
                channel.enableLights(true);
                channel.setVibrationPattern(VIBRATE_PATTERN);
                channel.enableVibration(true);

                if (notificationManager != null)
                    notificationManager.createNotificationChannel(channel);
            }
        }

        /*for android < oreo*/
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(Objects.requireNonNull(remoteMessage.getNotification()).getBody())
                .setAutoCancel(true)
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setSound(NOTIFICATION_SOUND_URI)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_HIGH);

        if (notificationManager != null)
            notificationManager.notify(101, notificationBuilder.build());
    }

}

