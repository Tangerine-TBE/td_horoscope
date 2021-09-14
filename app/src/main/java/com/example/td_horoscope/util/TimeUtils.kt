package com.example.td_horoscope.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    fun getDate(): String{
        val time = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.format(time)
    }
}