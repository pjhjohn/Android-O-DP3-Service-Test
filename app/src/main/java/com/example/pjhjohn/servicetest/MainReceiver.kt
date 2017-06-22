package com.example.pjhjohn.servicetest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber


class MainReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val count = intent.getIntExtra("count", 0)
        Timber.d("[onStartCommand] (intent:%s) (count:%d)", intent, count)
        if (count > 0) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val newIntent = Intent(context, MainReceiver::class.java).putExtra("count", count - 1)
            val pendingIntent = PendingIntent.getBroadcast(context, 0x200, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10_000, pendingIntent)
        }
    }
}
