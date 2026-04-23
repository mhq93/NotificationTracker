package com.mhq.notificationtracker

import android.content.Context
import androidx.room.Room
import com.mhq.notificationtracker.data.NotificationsRepoImpl
import com.mhq.notificationtracker.data.database.NotificationsDatabase
import com.mhq.notificationtracker.domain.FetchNotificationsUseCase
import com.mhq.notificationtracker.domain.InsertNotificationUseCase
import com.mhq.notificationtracker.domain.NotificationsRepository

interface AppContainer {
    val notificationsRepository: NotificationsRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {
    private val database = Room.databaseBuilder(
        context,
        NotificationsDatabase::class.java,
        "notifications_db"
    ).build()

    private val dao = database.notificationDao()
    private val repository = NotificationsRepoImpl(dao)
    val insertNotificationUseCase = InsertNotificationUseCase(repository)
    val fetchNotificationsUseCase = FetchNotificationsUseCase(repository)

    override val notificationsRepository: NotificationsRepository by lazy {
        NotificationsRepoImpl(database.notificationDao())
    }
}