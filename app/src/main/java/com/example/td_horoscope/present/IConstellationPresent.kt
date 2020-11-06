package com.example.td_horoscope.present

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.present
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 16:13
 * @class describe
 */
interface IConstellationPresent {
    fun getConsDayMsg(name:String,type:String)
    fun getConsTomorrowMsg(name:String,type:String)
    fun getConsWeekMsg(name:String,type:String)
    fun getConsMonthMsg(name:String,type:String)
    fun getConsYearMsg(name:String,type:String)
}