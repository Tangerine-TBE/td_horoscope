package com.example.td_horoscope.bean.dream

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.bean.dream
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 17:07
 * @class describe
 */
data class DreamBean(
    val error_code: Int,
    val reason: String,
    val result: List<Result>
)

data class Result(
    val des: String,
    val id: String,
    val title: String
)