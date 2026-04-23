package com.mhq.notificationtracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhq.notificationtracker.domain.FetchNotificationsUseCase
import com.mhq.notificationtracker.domain.InsertNotificationUseCase

class NotificationViewModelFactory(
    private val insertNotificationUseCase: InsertNotificationUseCase,
    private val fetchNotificationsUseCase: FetchNotificationsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationViewModel(insertNotificationUseCase, fetchNotificationsUseCase) as T
    }
}