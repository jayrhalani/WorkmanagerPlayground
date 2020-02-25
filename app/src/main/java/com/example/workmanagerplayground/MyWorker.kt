package com.example.workmanagerplayground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO (2) -> Create MyWorker Class and override doWork Method
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        displayNotification("Some title", "Some Description")
        return Result.success()
    }

    private fun displayNotification(task: String, desc: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "ArvaanTechnolab",
                "ArvaanTechnolab",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, "ArvaanTechnolab")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher)

        notificationManager.notify(1, builder.build())
    }
}