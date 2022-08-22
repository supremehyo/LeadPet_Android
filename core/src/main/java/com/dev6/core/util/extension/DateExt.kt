package com.dev6.core.util.extension


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

private object TimeMaximum {
    const val MIN = 60
    const val HOUR = 24
    const val DAY = 30
    const val MONTH = 12
}

fun makeProceedingString(startList :String , endList:String){
    val d1 = startList[0]+"."+startList[1]+"."+startList[2]
    val d2 = endList[0]+"."+endList[1]+"."+endList[2]

    val sdf = SimpleDateFormat("yyyy.MM.DD")

    val firstDate: Date = sdf.parse(d1)
    val secondDate: Date = sdf.parse(d2)

    val cmp = firstDate.compareTo(secondDate)
    when {
        cmp > 0 -> {
            System.out.printf("%s is after %s", d1, d2)
        }
        cmp < 0 -> {
            System.out.printf("%s is before %s", d1, d2)
        }
        else -> {
            print("Both dates are equal")
        }
    }

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

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeString(
    year: String, mounth: String, day: String, hour: String, minite: String, second: String
): String? {

    var diffTime = Duration.between(
        LocalDateTime.of(year.toInt(), mounth.toInt(), day.toInt(), hour.toInt(), minite.toInt()),
        LocalDateTime.now()
    ).toMinutes()
    var msg: String? = null
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
    return msg
}
