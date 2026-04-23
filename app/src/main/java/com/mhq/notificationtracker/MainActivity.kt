package com.mhq.notificationtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.mhq.notificationtracker.data.alarm.NotificationHelper
import com.mhq.notificationtracker.ui.screens.NotificationsList
import com.mhq.notificationtracker.ui.theme.NotificationTrackerTheme
import com.mhq.notificationtracker.ui.viewmodel.NotificationViewModel
import com.mhq.notificationtracker.ui.viewmodel.NotificationViewModelFactory
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val container = (this.application as TrackerApplication).container
        val viewModel: NotificationViewModel by viewModels {
            NotificationViewModelFactory(
                container.insertNotificationUseCase,
                container.fetchNotificationsUseCase
            )
        }

        fun getNextAlarmTime(hour: Int, minute: Int): Long {
            val now = Calendar.getInstance()
            val target = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            if (target.timeInMillis <= now.timeInMillis) {
                target.add(Calendar.DAY_OF_YEAR, 1)
            }
            return target.timeInMillis
        }

        NotificationHelper.scheduleNotification(
            this,
            requestCode = 1,
            hour = 8,
            getNextAlarmTime(8, 0)
        )
        NotificationHelper.scheduleNotification(
            this,
            requestCode = 2,
            hour = 10,
            getNextAlarmTime(10, 0)
        )
        NotificationHelper.scheduleNotification(
            this,
            requestCode = 3,
            hour = 12,
            getNextAlarmTime(12, 0)
        )

        setContent {
            NotificationTrackerTheme {
                val notifications = viewModel.notifications.collectAsLazyPagingItems()
                NotificationsList(notifications = notifications)
            }
        }
    }
}