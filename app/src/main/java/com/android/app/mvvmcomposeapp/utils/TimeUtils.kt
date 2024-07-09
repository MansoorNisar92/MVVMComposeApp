package com.android.app.mvvmcomposeapp.utils

import java.util.Calendar

object TimeUtils {
    fun getGreetingMessage(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..20 -> "Good Evening"
            else -> "Good Night"
        }
    }
}