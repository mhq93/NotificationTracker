package com.mhq.notificationtracker.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimestampFormatter {
    fun formatTime(timestamp: Long): String =
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(timestamp))

    fun formatDate(timestamp: Long): String =
        SimpleDateFormat("d MMM, yyyy", Locale.getDefault()).format(Date(timestamp))
}