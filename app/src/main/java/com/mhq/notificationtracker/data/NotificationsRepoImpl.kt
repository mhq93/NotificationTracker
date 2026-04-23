package com.mhq.notificationtracker.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mhq.notificationtracker.data.database.NotificationDao
import com.mhq.notificationtracker.data.database.NotificationEntity
import com.mhq.notificationtracker.domain.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow


class NotificationsRepoImpl(private val notificationDao: NotificationDao) : NotificationsRepository{

    override suspend fun saveToDatabase(notification: NotificationEntity) {
        notificationDao.insertNotification(notification)
    }

    override fun fetchNotifications(): Flow<PagingData<NotificationEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { notificationDao.getAllNotifications() }
        ).flow
    }
}