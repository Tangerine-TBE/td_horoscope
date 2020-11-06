package com.example.td_horoscope.util.top

import kotlin.math.floor

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.util
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 17:45
 * @class describe
 */


fun Int.getStart():Float{
    return if (this >= 20) {
        if (this % 20 <10) {
            floor(this / 20.0).toFloat()
        } else {
            floor(this / 20.0).toFloat() + 0.5f
        }
    } else if (this==0) {
            0f
    } else {
        1f
    }

}