package org.sopt.holix.presentation.chatting.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private fun Date.toFormattedString(pattern: String = "yyyy/MM/dd"): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(this)
    }

    private fun Date.toFormattedTimeString(pattern: String = "a h시 m분"): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(this)
    }

    private fun Date.toFormattedTodayString(pattern: String = "yyyy년 M월 d일 E요일"): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(this)
    }

    fun todayFormatted(pattern: String = "yyyy년 M월 d일 E요일"): String {
        return Date().toFormattedTodayString(pattern)
    }

    fun String.toFormattedDto(): String {
        return this.replace("/", "-")
    }

    fun String.toFormattedTimeString(): String {
        return Date().toFormattedTimeString()
    }

    fun String.toFormattedTodayString() : String {
        return Date().toFormattedTodayString()
    }
}