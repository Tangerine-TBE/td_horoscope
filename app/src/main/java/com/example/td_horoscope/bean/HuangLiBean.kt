package com.example.td_horoscope.bean

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 14:45
 * @class describe
 */
data class HuangLiBean(
    val msg: String,
    val result: Result,
    val status: String
)

data class Result(
    val caishen: String,
    val chong: String,
    val day: String,
    val emonth: String,
    val eweek: String,
    val fushen: String,
    val ji: List<String>,
    val jiri: String,
    val jishenyiqu: String,
    val month: String,
    val nongli: String,
    val sha: String,
    val shengxiao: String,
    val star: String,
    val suici: List<String>,
    val taishen: String,
    val week: String,
    val wuxing: String,
    val xiongshen: String,
    val xishen: String,
    val yangli: String,
    val year: String,
    val yi: List<String>,
    val zhiri: String
)