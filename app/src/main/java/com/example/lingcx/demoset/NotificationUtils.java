package com.example.lingcx.demoset;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/6/2 下午9:02
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class NotificationUtils {
    private static NotificationManager mNotificationManager;
    private static Notification notification;

    /**
     * 创建通知，  * 请在调用此方法时开启子线程
     *
     * @param context    上下文
     * @param icon       通知图片
     * @param tickerText 通知未拉开的内容
     * @param title      通知标题
     * @param content    通知主内容
     * @param intent     意图
     * @param id
     */
    public static void createNotification(Context context, int icon, String tickerText, String title, String content, Intent intent, int id) {
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setAction(Intent.ACTION_MAIN);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setTicker(tickerText)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(icon);

        notification = mBuilder.build();

        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        mNotificationManager.notify(id, notification);
    }
}
