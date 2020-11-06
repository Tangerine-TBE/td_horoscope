package com.example.td_horoscope.bean.qq


/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 16:18
 * @class describe
 */
data class QQBean(
    val error_code: Int,
    val reason: String,
    val result: Result
)

data class Result(
    val `data`: Data
)

data class Data(
    val analysis: String,
    val conclusion: String
)