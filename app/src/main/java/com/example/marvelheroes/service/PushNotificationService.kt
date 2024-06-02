package com.example.marvelheroes.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.marvelheroes.MainActivity
import com.example.marvelheroes.R
import com.example.marvelheroes.navigation.NAV_URL
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.e("token", token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

        val remoteMessage = message.notification
        val context: Context = applicationContext
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "$NAV_URL/Detailed/${remoteMessage?.body}".toUri(),
            context,
            MainActivity::class.java
        )

        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(context, "fcm_channel")
            .setSmallIcon(R.drawable.logo_marvel)
            .setContentTitle(remoteMessage?.title)
            .setContentText(remoteMessage?.body)
            .setContentIntent(clickPendingIntent)
            .build()

        notificationManager.notify(1, notification)

    }


}