package com.example.td_horoscope.module

import com.example.td_horoscope.bean.*
import com.example.td_horoscope.bean.consplate.ConsPlateBean
import com.example.td_horoscope.bean.dream.DreamBean
import com.example.td_horoscope.bean.historyevent.HistoryEventBean
import com.example.td_horoscope.bean.qq.QQBean
import com.example.td_horoscope.bean.zodiac.ZodiacBean
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.RetrofitManager
import retrofit2.Callback
import java.util.*


/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.module
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 15:51
 * @class describe
 */
object NetDataProvider {

    private val mCreateApi by lazy {
        RetrofitManager.createConstellation<Api>()
    }

    private val mHuangLiApi by lazy {
        RetrofitManager.createHuangLi<Api>()
    }

    private val mEventApi by lazy {
        RetrofitManager.createHistoryEvent<Api>()
    }

    private val mQQApi by lazy {
        RetrofitManager.createQQ<Api>()
    }

    private val mDreamApi by lazy {
        RetrofitManager.createDream<Api>()
    }

    private val mZodiacApi by lazy {
        RetrofitManager.createZodiac<Api>()
    }

    private val mConsPlateApi by lazy {
        RetrofitManager.createPlate<Api>()
    }



    private val mCalender by lazy {
        Calendar.getInstance()
    }



    fun getTodayConsInfo(name: String, type: String, callback: retrofit2.Callback<ToDayBean>) {
        mCreateApi.getTodayInfo(name, type, Contents.COS_KEY).enqueue(callback)
    }

    fun getTomorrowConsInfo(name: String, type: String, callback: retrofit2.Callback<ToDayBean>) {
        mCreateApi.getTomorrowInfo(name, type, Contents.COS_KEY).enqueue(callback)
    }

    fun getWeekConsInfo(name: String, type: String, callback: retrofit2.Callback<WeekBean>) {
        mCreateApi.getWeekInfo(name, type, Contents.COS_KEY).enqueue(callback)
    }

    fun getMonthConsInfo(name: String, type: String, callback: retrofit2.Callback<MonthBean>) {
        mCreateApi.getMonthInfo(name, type, Contents.COS_KEY).enqueue(callback)
    }

    fun getYearConsInfo(name: String, type: String, callback: retrofit2.Callback<YearBean>) {
        mCreateApi.getYearInfo(name, type, Contents.COS_KEY).enqueue(callback)
    }

    fun getHuangLiInfo(callback: Callback<HuangLiBean>) {
        mHuangLiApi.getHuangLi(
            Contents.HUANG_LI_KEY,
            mCalender[Calendar.DAY_OF_MONTH].toString(),
            (mCalender[Calendar.MONTH] + 1).toString(),
            mCalender[Calendar.YEAR].toString()
        ).enqueue(callback)
    }

    fun getEventInfo(callback: Callback<HistoryEventBean>) {
        mEventApi.getHistoryEventInfo(Contents.EVENT_KEY,
            Contents.EVENT_VERSION,(mCalender[Calendar.MONTH] + 1).toString(), mCalender[Calendar.DAY_OF_MONTH].toString())
            .enqueue(callback)
    }

    fun getQQInfo(qq:String,callback: Callback<QQBean>) {
        mQQApi.getQQInfo(Contents.QQ_KEY,qq).enqueue(callback)
    }

    fun getDreamInfo(dream:String,callback: Callback<DreamBean>) {
        mDreamApi.getDreamInfo(Contents.ZG_KEY, dream).enqueue(callback)
    }


    fun getZodiacInfo(man:String,women:String,callback: Callback<ZodiacBean>) {
        mZodiacApi.getZodiacInfo(Contents.ZODIAC_KEY, man,women).enqueue(callback)
    }


    fun getPlateInfo(man:String,women:String,callback: Callback<ConsPlateBean>) {
        mConsPlateApi.getCosPlateInfo(Contents.COS_PLATE_KEY, man,women).enqueue(callback)
    }


}