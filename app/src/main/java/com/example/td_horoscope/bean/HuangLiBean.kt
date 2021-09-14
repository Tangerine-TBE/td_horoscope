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
    val reason: String,
    val result: Result,
    val error_code: String
)

data class Result(
    val id: String,
    val yangli: String,
    val yinli: String,
    val wuxing: String,
    val chongsha: String,
    val baiji: String,
    val jishen: String,
    val yi: String,
    val xiongshen: String,
    val ji: String,
)