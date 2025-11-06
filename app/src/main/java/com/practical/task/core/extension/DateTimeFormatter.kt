package com.practical.task.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


data class LocalSessionTime(
    val dateLabel: String, // "04 Friday"
    val timeLabel: String  // "10:00 PM"
)

fun formatSessionTime(epochSeconds: Long): LocalSessionTime {
    // Convert seconds → milliseconds
    val date = Date(epochSeconds * 1000)

    // Get device local time zone
    val locale = Locale.getDefault()
    val timeZone = TimeZone.getDefault()

    // Format date like "04 Friday"
    val dateFormat = SimpleDateFormat("dd EEEE", locale)
    dateFormat.timeZone = timeZone

    // Format time like "10:00 PM"
    val timeFormat = SimpleDateFormat("hh:mm a", locale)
    timeFormat.timeZone = timeZone

    return LocalSessionTime(
        dateLabel = dateFormat.format(date),
        timeLabel = timeFormat.format(date)
    )
}