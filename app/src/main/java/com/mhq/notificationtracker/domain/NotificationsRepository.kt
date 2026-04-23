package com.mhq.notificationtracker.domain

import androidx.paging.PagingData
import com.mhq.notificationtracker.data.database.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    suspend fun saveToDatabase(notification: NotificationEntity)
    fun fetchNotifications(): Flow<PagingData<NotificationEntity>>
}