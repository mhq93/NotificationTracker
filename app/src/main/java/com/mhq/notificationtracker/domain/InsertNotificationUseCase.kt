package com.mhq.notificationtracker.domain

import com.mhq.notificationtracker.data.database.NotificationEntity

class InsertNotificationUseCase(private val repository: NotificationsRepository) {
    suspend operator fun invoke(notification: NotificationEntity) {
        repository.saveToDatabase(notification)
    }
}