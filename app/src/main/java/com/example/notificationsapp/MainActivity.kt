package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    lateinit var builder:Notification.Builder
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "myapp.notifications"
        val description = "My Notification"
        val message = findViewById<EditText>(R.id.etMessage)
        findViewById<Button>(R.id.btSubmit).setOnClickListener{
            if(message.text.isNotBlank()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    var notificationChannel = NotificationChannel(
                        channelId, description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationManager.createNotificationChannel(notificationChannel)
                    builder = Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.notifications)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.notifications
                            )
                        )
                        .setContentTitle(description)
                        .setContentText(message.text.toString())
                } else {
                    builder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.notifications)
                        .setContentTitle(description)
                        .setContentText(message.text.toString())
                }
                notificationManager.notify(1234, builder.build())
            }else
                Toast.makeText(this,"write a notification to be displayed",Toast.LENGTH_SHORT).show()
        }
    }
}