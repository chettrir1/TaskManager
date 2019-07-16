package com.techsales.taskmanager.auth.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.dashboard.container.DashboardActivity;

import java.util.Objects;

class NotificationHelper {
    private static final String NOTIFICATION_CHANNEL_ID = "101";
    private static final CharSequence NOTIFICATION_CHANNEL_NAME = "channel Name";
    private static final int REQUEST_CODE = 0;


    private Context context;

    NotificationHelper(Context context) {
        this.context = context;
    }

    void createNotification(RemoteMessage remoteMessage) {
        long[] vibrationPattern = new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400};

        Intent resultIntent = new Intent(context, DashboardActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
                REQUEST_CODE, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri notificationSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notification);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(Objects.requireNonNull(remoteMessage.getNotification()).getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent)
                .setSound(notificationSound)
                .setVibrate(vibrationPattern)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String description = "Channel Description";

            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            notificationChannel.setDescription(description);

            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(context);
        notificationManagerCompat.notify((int) System.currentTimeMillis(), mBuilder.build());
    }
}
