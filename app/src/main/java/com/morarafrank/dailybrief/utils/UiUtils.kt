package com.morarafrank.dailybrief.utils
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Instant

object UiUtils {


    fun toReadableDate(timestamp: Long): String {
        val formatter = SimpleDateFormat(
            "EEE, dd MMM h:mm a",
            Locale.getDefault()
        )
        return formatter.format(timestamp)
    }


//    fun formatLastUpdated(dt: Long?): String {
//        val date = Date(dt?.times(1000) ?: 0L)
//
//        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
//        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
//
//        val timeZone = TimeZone.getTimeZone("Africa/Nairobi")
//        dateFormat.timeZone = timeZone
//        timeFormat.timeZone = timeZone
//
//        val dateStr = dateFormat.format(date)
//        val timeStr = timeFormat.format(date)
//
//        return "Last updated on $dateStr at $timeStr"
//    }
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



    fun formatLastUpdated(dt: String?): String {
        if (dt.isNullOrBlank()) return ""

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC") // keep original time

        val date = parser.parse(dt) ?: return ""

        val calendar = Calendar.getInstance().apply {
            time = date
        }

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val suffix = getDaySuffix(day)

        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

        val timeStr = timeFormat.format(date)
        val monthStr = monthFormat.format(date)

        return "$timeStr $day$suffix $monthStr"
    }

    private fun getDaySuffix(day: Int): String =
        if (day in 11..13) "th"
        else when (day % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }

}

