package com.morarafrank.skycast.utils
import java.text.SimpleDateFormat
import java.util.*

object UiUtils {


    fun toReadableDate(timestamp: Long): String {
        val formatter = java.text.SimpleDateFormat(
            "EEE, dd MMM h:mm a",
            java.util.Locale.getDefault()
        )
        return formatter.format(timestamp)
    }


    fun formatLastUpdated(dt: Long?): String {
        val date = Date(dt?.times(1000) ?: 0L)

        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        dateFormat.timeZone = timeZone
        timeFormat.timeZone = timeZone

        val dateStr = dateFormat.format(date)
        val timeStr = timeFormat.format(date)

        return "Last updated on $dateStr at $timeStr"
    }
    fun formatDate(dt: Long?): String {
        val date = Date(dt?.times(1000) ?: 0L)
        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        return dateFormat.format(date)
    }

    fun formatTime(dt: Long?): String {
        val date = Date(dt?.times(1000) ?: 0L)
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        timeFormat.timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        return timeFormat.format(date)
    }

    fun toCapitation(sentence: String): String {
        return sentence
            .split(" ")
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercaseChar() } }
    }


}