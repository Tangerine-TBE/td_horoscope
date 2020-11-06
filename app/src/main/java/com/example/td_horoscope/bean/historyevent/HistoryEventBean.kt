package com.example.td_horoscope.bean.historyevent

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 14:00
 * @class describe
 */
data class HistoryEventBean(
    val error_code: Int,
    val reason: String,
    val result: List<Result>
)

data class Result(
    val _id: String,
    val day: Int,
    val des: String,
    val lunar: String,
    val month: Int,
    val pic: String,
    val title: String,
    val year: Int
)