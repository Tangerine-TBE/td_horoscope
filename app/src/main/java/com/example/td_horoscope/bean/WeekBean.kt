package com.example.td_horoscope.bean

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 15:46
 * @class describe
 */
data class WeekBean(
    val date: String,
    val error_code: Int,
    val health: String,
    val job: String,
    val love: String,
    val money: String,
    val name: String,
    val resultcode: String,
    val weekth: Int,
    val work: String
)