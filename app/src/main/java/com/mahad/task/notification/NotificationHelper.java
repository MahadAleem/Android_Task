package com.mahad.task.notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.mahad.task.MainActivity;
import com.mahad.task.R;
import com.mahad.task.model.Fav_Model;
import com.mahad.task.roomdb.DataBase_Main;
import com.mahad.task.roomdb.Fav_Entities;

import java.util.List;

public class NotificationHelper {

    private final Context mContext;
    private static final String NOTIFICATION_CHANNEL_ID = "10001";

    DataBase_Main dataBase_main;

    NotificationHelper(Context context) {
        mContext = context;
    }

    @SuppressLint("RemoteViewLayout")
    void createNotification() {

        Intent intent = new Intent(mContext, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        dataBase_main= Room.databaseBuilder(mContext,DataBase_Main.class,"TASK").allowMainThreadQueries().build();
        Fav_Entities fetchRandom = dataBase_main.dao().fetchRandom();


        //Remote View
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_view);
        remoteViews.setImageViewUri(R.id.drink_image_notificatio, Uri.parse(fetchRandom.getImageURL_e()));
        remoteViews.setTextViewText(R.id.glass_name_notifcaion,fetchRandom.getGlassName_e());
        remoteViews.setTextViewText(R.id.drink_detail_notification,fetchRandom.getDrinkDetail_e());


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
        mBuilder.setAutoCancel(false)
                .setCustomBigContentView(remoteViews)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
    }
}

