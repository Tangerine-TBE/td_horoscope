package com.example.td_horoscope.bean.consplate

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean.consplate
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/2 17:32
 * @class describe
 */
data class ConsPlateBean(
    val error_code: Int,
    val reason: String,
    val result: Result
)

data class Result(
    val bizhong: String,
    val jieguo: String,
    val lianai: String,
    val men: String,
    val tcdj: String,
    val women: String,
    val xiangyue: String,
    val zhishu: String,
    val zhuyi: String
)