package com.example.td_horoscope.bean

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 15:48
 * @class describe
 */
data class YearBean(
    val career: List<String>,
    val date: String,
    val error_code: Int,
    val finance: List<String>,
    val future: String,
    val health: List<String>,
    val love: List<String>,
    val luckeyStone: String,
    val mima: Mima,
    val name: String,
    val resultcode: String,
    val year: Int
)

data class Mima(
    val info: String,
    val text: List<String>
)