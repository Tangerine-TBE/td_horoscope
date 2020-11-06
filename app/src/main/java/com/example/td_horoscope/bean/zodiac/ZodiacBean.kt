package com.example.td_horoscope.bean.zodiac

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean.zodiac
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/2 14:30
 * @class describe
 */
data class ZodiacBean(
    val error_code: Int,
    val reason: String,
    val result: Result
)

data class Result(
    val `data`: String,
    val men: String,
    val women: String
)