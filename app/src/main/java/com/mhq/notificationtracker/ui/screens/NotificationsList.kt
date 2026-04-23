package com.mhq.notificationtracker.ui.screens

import android.Manifest
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.mhq.notificationtracker.data.database.NotificationEntity
import com.mhq.notificationtracker.ui.components.NotificationCard
import com.mhq.notificationtracker.ui.components.RuntimePermissionsDialog

@Composable
fun NotificationsList(
    modifier: Modifier = Modifier,
    notifications: LazyPagingItems<NotificationEntity>
) {

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        RuntimePermissionsDialog(
            Manifest.permission.POST_NOTIFICATIONS,
            onPermissionDenied = {},
            onPermissionGranted = {},
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        items(notifications.itemCount) { index ->
            notifications[index]?.let {
                NotificationCard(notification = it)
            }
        }
    }
}