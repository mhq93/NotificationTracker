package com.mhq.notificationtracker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.WorkManager
import com.mhq.notificationtracker.data.database.NotificationEntity
import com.mhq.notificationtracker.data.database.NotificationsDatabase
import com.mhq.notificationtracker.domain.FetchNotificationsUseCase
import com.mhq.notificationtracker.domain.InsertNotificationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val insertNotificationUseCase: InsertNotificationUseCase,
    private val fetchNotificationsUseCase: FetchNotificationsUseCase
) : ViewModel() {

    val notifications: Flow<PagingData<NotificationEntity>> = fetchNotificationsUseCase()

    fun insertNotification(notification: NotificationEntity) {
        viewModelScope.launch {
            insertNotificationUseCase(notification = notification)
        }
    }
}