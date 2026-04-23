package com.mhq.notificationtracker.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.mhq.notificationtracker.TrackerApplication
import com.mhq.notificationtracker.data.database.NotificationEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        getBatteryLevel(context)

        when (intent.action) {
            "android.intent.action.BOOT_COMPLETED" -> {
                rescheduleAlarms(context)
            }

            else -> {
                val batteryLevel = getBatteryLevel(context)
                val timestamp = System.currentTimeMillis()
                val container = (context.applicationContext as TrackerApplication).container
                GlobalScope.launch {
                    container.insertNotificationUseCase(
                        NotificationEntity(timestamp = timestamp, batteryLevel = batteryLevel)
                    )
                }

                NotificationHelper.showNotification(context)
                val requestCode = intent.getIntExtra("request_code", 0)
                val hour = intent.getIntExtra("hour", 8)
                rescheduleAlarm(context, requestCode, hour)
            }
        }
    }

    private fun rescheduleAlarm(context: Context, requestCode: Int, hour: Int) {
        val nextAlarm = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        NotificationHelper.scheduleNotification(context, requestCode, hour, nextAlarm)
    }

    private fun rescheduleAlarms(context: Context) {
        val schedule = listOf(
            Triple(1, 8, getNextAlarmTime(8)),
            Triple(2, 10, getNextAlarmTime(10)),
            Triple(3, 12, getNextAlarmTime(12))
        )

        schedule.forEach { (requestCode, hour, time) ->
            NotificationHelper.scheduleNotification(context, requestCode, hour, time)
        }
    }

    private fun getNextAlarmTime(hour: Int): Long {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (target.timeInMillis <= now.timeInMillis) {
            target.add(Calendar.DAY_OF_YEAR, 1)
        }

        return target.timeInMillis
    }

    fun getBatteryLevel(context: Context): Int {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus: Intent? = context.registerReceiver(null, intentFilter)

        return batteryStatus?.let { intent ->
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            (level * 100 / scale.toFloat()).toInt()
        } ?: -1
    }
}