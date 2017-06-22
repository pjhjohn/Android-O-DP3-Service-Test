package com.example.pjhjohn.servicetest

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import timber.log.Timber

class MainService : Service() {

    /* Lifecycle */
    override fun onCreate() {
        super.onCreate()
        Timber.d("[onCreate]")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("[onStartCommand] (intent:%s), (flags:%d), (startId:%d)", intent, flags, startId)

        /* Periodic Action for Client */
        this.reschedule(this)

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("[onDestroy]")
        this.reschedule(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        Timber.d("[onBind] (intent:%s)", intent)
        return null
    }

    override fun onUnbind(intent: Intent): Boolean {
        Timber.d("[onUnbind] (intent:%s)", intent)
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent) {
        Timber.d("[onRebind] (intent:%s)", intent)
        super.onRebind(intent)
    }

    private fun reschedule(context: Context) {
        Timber.d("reschedule")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MainService::class.java)
        val now = System.currentTimeMillis()
        val pendingIntent = PendingIntent.getService(context, 0x100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, now + 10_000, pendingIntent)
    }
}
