package com.mhq.notificationtracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotificationEntity::class], version = 1)
abstract class NotificationsDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}