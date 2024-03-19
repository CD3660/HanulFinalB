package com.hanul.mysmarthome;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("FCM Log", "Refreshed token:" + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        if(message.getData().get("click_action").equals("119")){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:119"));
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(getResources().getString(R.string.default_notification_channel_id), "notice", NotificationManager.IMPORTANCE_HIGH);
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.enableVibration(true);
                channel.setDescription("notification");
                notificationManager.createNotificationChannel(channel);
                Notification notification = getNotificationBuilder(message.getNotification().getTitle(),message.getNotification().getBody(),pendingIntent).build();
                notificationManager.notify(10, notification);
            }
        } else {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(getResources().getString(R.string.default_notification_channel_id), "notice", NotificationManager.IMPORTANCE_HIGH);
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.enableVibration(true);
                channel.setDescription("notification");
                notificationManager.createNotificationChannel(channel);
                Notification notification = getNotificationBuilder(message.getNotification().getTitle(),message.getNotification().getBody(),pendingIntent).build();
                notificationManager.notify(10, notification);
            }
        }


    }

    private NotificationCompat.Builder getNotificationBuilder(String title, String content, PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(this, getResources().getString(R.string.default_notification_channel_id))
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setGroupSummary(true)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.splash_logo)
                .setShowWhen(true);
    }


}
