package com.softorea.schoolsen.lists;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.softorea.schoolsen.models.FormModel;

/* Background service to handle the form data */
public class FormService extends Service {

    public static FormModel objform;
    final int notificationId = 1;
    Notification foreGroundNotification;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        StartInForeGround();
        objform = new FormModel();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void StartInForeGround() {

		/*int notificationIcon = R.drawable.ic_stat_icon_noti;

		long timeStamp = System.currentTimeMillis();
		foreGroundNotification = new Notification(notificationIcon, Globals.TickerText,timeStamp);

		Intent notificationIntent = new Intent(this, FormActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_icon_noti)
                .setContentTitle(Globals.notificationTitleText)
                .setContentText(Globals.notificationBodyText)
                .setContentIntent(contentIntent).build();
		
		startForeground(notificationId, notification);*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

}
