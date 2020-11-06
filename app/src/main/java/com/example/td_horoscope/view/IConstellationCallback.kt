package com.example.td_horoscope.view

import com.example.module_base.base.IBaseCallback
import com.example.td_horoscope.bean.MonthBean
import com.example.td_horoscope.bean.ToDayBean
import com.example.td_horoscope.bean.WeekBean
import com.example.td_horoscope.bean.YearBean

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.view
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 16:16
 * @class describe
 */
interface IConstellationCallback:IBaseCallback {

    fun onLoadToadySuccess(today:ToDayBean)

    fun onLoadTomorrowSuccess(tomorrow:ToDayBean)

    fun onLoadWeekSuccess(week:WeekBean)

    fun onLoadMonthSuccess(month:MonthBean)

    fun onLoadYearSuccess(year:YearBean)

}