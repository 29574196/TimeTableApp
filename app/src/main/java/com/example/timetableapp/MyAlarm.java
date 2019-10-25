package com.example.timetableapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context,Appointment.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("TimeTable App Notification")
                .setContentText("New Appointment From TimeTable App")
                .setTicker("Gym Time")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);


        Intent intent1 = new Intent(context,Appointment.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);




        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
    }
}
