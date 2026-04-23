package com.mhq.notificationtracker.domain

import androidx.paging.PagingData
import com.mhq.notificationtracker.data.database.NotificationEntity
import kotlinx.coroutines.flow.Flow

class FetchNotificationsUseCase(private val repository: NotificationsRepository) {
    operator fun invoke(): Flow<PagingData<NotificationEntity>> {
        return repository.fetchNotifications()
    }
}