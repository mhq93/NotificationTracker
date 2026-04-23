package com.mhq.notificationtracker.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhq.notificationtracker.data.database.NotificationEntity
import com.mhq.notificationtracker.util.TimestampFormatter

@Composable
fun NotificationCard(
    modifier: Modifier = Modifier,
    notification: NotificationEntity
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, color = Color.Black)
    ) {
        Text(
            text = "Local Notification",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "Time: ${TimestampFormatter.formatTime(notification.timestamp)}",
            fontSize = 32.sp,
            modifier = modifier.padding(16.dp)

        )
        Text(
            text = "Date: ${TimestampFormatter.formatDate(notification.timestamp)}",
            fontSize = 32.sp,
            modifier = modifier.padding(16.dp)

        )
        Text(
            text = "Battery Percentage: ${notification.batteryLevel}%",
            fontSize = 32.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
