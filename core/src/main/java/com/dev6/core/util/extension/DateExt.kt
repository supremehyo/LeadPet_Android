package com.dev6.core.util.extension


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private object TimeMaximum {
    const val MIN = 60
    const val HOUR = 24
    const val DAY = 30
    const val MONTH = 12
}

//기존의 fewDay 함수를 이용
//공고 마감시간만 받아와서 현재 날짜와 비교해서 계산
//공고 마감시간과 현재날짜와 비교해서 현재날짜가 이전이면 공고중이라고 판단
fun makeProceedingString(endList:List<String>) : String{
    return fewDay(endList[0],endList[1],endList[2],endList[3],endList[4],endList[5]).toString()
}

//날짜차이 구하기
@RequiresApi(Build.VERSION_CODES.O)
fun fewDay(
    year: String, mounth: String, day: String, hour: String, minite: String, second: String
): String? {

    val beginDay = Calendar.getInstance().apply {
        set(Calendar.YEAR, year.toInt())
        set(Calendar.MONTH, mounth.toInt())
        set(Calendar.DAY_OF_MONTH, day.toInt())
        set(Calendar.HOUR, hour.toInt())
        set(Calendar.MINUTE, minite.toInt())
        set(Calendar.SECOND, second.toInt())
    }

    var nowTime = Calendar.getInstance()

    val lastDay = Calendar.getInstance().apply {
        set(Calendar.YEAR, nowTime.get(Calendar.YEAR))
        set(Calendar.MONTH, nowTime.get(Calendar.MONTH) + 1)
        set(Calendar.DAY_OF_MONTH, nowTime.get(Calendar.DAY_OF_MONTH))
        set(Calendar.HOUR, nowTime.get(Calendar.HOUR))
        set(Calendar.MINUTE, nowTime.get(Calendar.MINUTE))
        set(Calendar.SECOND, nowTime.get(Calendar.SECOND))
    }

    return formatTimeString(year, mounth, day, hour, minite, second)
}

fun getCurrentTime() : List<String>{
    var nowTime = Calendar.getInstance()
    return listOf(nowTime.get(Calendar.YEAR).toString(),
        (nowTime.get(Calendar.MONTH) + 1).toString(),
        nowTime.get(Calendar.DAY_OF_MONTH).toString(),
        nowTime.get(Calendar.HOUR_OF_DAY).toString(),
        nowTime.get(Calendar.MINUTE).toString(),
        nowTime.get(Calendar.SECOND).toString()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeString(
    year: String, mounth: String, day: String, hour: String, minite: String, second: String
): String? {

    var diffTime = Duration.between(
        LocalDateTime.of(year.toInt(), mounth.toInt(), day.toInt(), hour.toInt(), minite.toInt()),
        LocalDateTime.now()
    ).toMinutes()
    var msg: String? = null
    if(diffTime >0){
        when {
            diffTime < TimeMaximum.MIN -> {
                msg = diffTime.toString() + "분 전"
            }
            TimeMaximum.MIN.let { diffTime /= it; diffTime } < TimeMaximum.HOUR -> {
                msg = diffTime.toString() + "시간 전"
            }
            TimeMaximum.HOUR.let { diffTime /= it; diffTime } < TimeMaximum.DAY -> {
                msg = diffTime.toString() + "일 전"
            }
            TimeMaximum.DAY.let { diffTime /= it; diffTime } < TimeMaximum.MONTH -> {
                msg = diffTime.toString() + "달 전"
            }
            else -> {
                msg = diffTime.toString() + "년 전"
            }
        }
    }else{
        msg = "공고중"
    }

    return msg
}
