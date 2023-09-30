package com.example.random_music_alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, ringtoneUri)
        Log.d("RingTOne","RING")
        ringtone.play()
        Toast.makeText(context,"Its times up", Toast.LENGTH_LONG).show()
        val notificationManager = context?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        // Notification Channel 아이디, 이름, 설명, 중요도 설정
        val channelId = "channel_one"
        val channelName = "첫 번째 채널"
        val channelDescription = "첫 번째 채널에 대한 설명입니다."
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        // NotificationChannel 객체 생성
        val notificationChannel = NotificationChannel(channelId, channelName, importance)
        // 설명 설정
        notificationChannel.description = channelDescription

        // 채널에 대한 각종 설정(불빛, 진동 등)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern = longArrayOf(100L, 200L, 300L)
        // 시스템에 notificationChannel 등록
        notificationManager.createNotificationChannel(notificationChannel)
        // Notification Channel 아이디, 이름, 설명, 중요도 설정

        // 채널 생성 코드...

        // API Level 26(O) 이상에서는 Builder 생성자에 NotificationChannel의 아이디값을 설정
        val notificationCompatBuilder = NotificationCompat.Builder(context, channelId)

        notificationCompatBuilder.let {
            Log.d(it.toString(),it.toString())

            // 작은 아이콘 설정
            it.setSmallIcon(android.R.drawable.ic_notification_overlay)
            // 시간 설정
            it.setWhen(System.currentTimeMillis())
            // 알림 메시지 설정
            it.setContentTitle("Content Title")
            // 알림 내용 설정
            it.setContentText("Content Message")
            // 알림과 동시에 진동 설정(권한 필요(
            it.setDefaults(Notification.DEFAULT_VIBRATE)
            // 클릭 시 알림이 삭제되도록 설정
            it.setAutoCancel(true)
        }
        val notification = notificationCompatBuilder.build()
        notificationManager.notify(0, notification)
        // Notification 식별자 값, Notification 객체

    }
}