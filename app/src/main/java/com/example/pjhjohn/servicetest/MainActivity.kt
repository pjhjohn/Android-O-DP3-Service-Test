package com.example.pjhjohn.servicetest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        startService(Intent(this, MainService::class.java))
        sendBroadcast(Intent(this, MainReceiver::class.java).putExtra("count", 20))
    }
}
