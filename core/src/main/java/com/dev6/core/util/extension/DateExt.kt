package com.dev6.core.util.extension


import java.util.*

fun makeTimeString(year: Char, mounth: Char, day: Char): String {
    return "$year.$mounth.$day"
}

//날짜차이 구하기
fun fewDay(year: String, mounth: String, day: String): String {

    val beginDay = Calendar.getInstance().apply {
        set(Calendar.YEAR, year.toInt())
        set(Calendar.MONTH, mounth.toInt())
        set(Calendar.DAY_OF_MONTH, day.toInt())
    }.timeInMillis

    var nowTime = Calendar.getInstance()

    val lastDay = Calendar.getInstance().apply {
        set(Calendar.YEAR, nowTime.get(Calendar.YEAR))
        set(Calendar.MONTH, nowTime.get(Calendar.MONTH))
        set(Calendar.DAY_OF_MONTH, nowTime.get(Calendar.DAY_OF_MONTH))
    }.timeInMillis

    val fewDay = getIgnoredTimeDays(lastDay) - getIgnoredTimeDays(beginDay)

    return fewDayMakeString(fewDay / (24 * 60 * 60 * 1000))
}

fun fewDayMakeString(result: Long): String {
    if (result < 7) {
        return result.toString() + "일전"
    }
    if (result > 7) {
        return "1주전"
    }
    if (result > 14) {
        return "2주전"
    }
    if (result > 21) {
        return "3주전"
    }
    if (result > 30) {
        return "1달전"
    }
    if (result > 60) {
        return "2달전"
    }
    if (result > 90) {
        return "3달전"
    }
    if (result > 120) {
        return "4달전"
    }
    if (result > 150) {
        return "5달전"
    }
    if (result > 180) {
        return "오래전"
    }
    return ""
}

// 시간, 분, 초, 밀리초 제외 시키기
fun getIgnoredTimeDays(time: Long): Long {
    return Calendar.getInstance().apply {
        timeInMillis = time

        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}