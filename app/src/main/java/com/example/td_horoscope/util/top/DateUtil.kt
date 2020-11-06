package com.example.td_horoscope.util.top

import java.text.SimpleDateFormat
import java.util.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.util.top
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 11:14
 * @class describe
 */


//获取时间
fun getCurrentDate(): String? {
    val ft = SimpleDateFormat("yyyy-MM-dd")
    val date = Date()
    return ft.format(date)
}

//获取星座
fun getAstro(month: Int, day: Int): String {
    val starArr = arrayOf("摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座")
    val DayArr = intArrayOf(22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22) // 两个星座分割日
    if (month <= 0 || day <= 0) {
        return "猴年马月座"
    } else if (month > 12 || day > 31) {
        return "猴年马月座"
    }
    var index = month
    // 所查询日期在分割日之前，索引-1，否则不变
    if (day < DayArr[month - 1]) {
        index = index - 1
    }
    // 返回索引指向的星座string
    return starArr[index % 12]
}