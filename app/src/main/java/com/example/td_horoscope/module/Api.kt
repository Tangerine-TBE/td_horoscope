package com.example.td_horoscope.module

import com.example.td_horoscope.bean.*
import com.example.td_horoscope.bean.consplate.ConsPlateBean
import com.example.td_horoscope.bean.dream.DreamBean
import com.example.td_horoscope.bean.historyevent.HistoryEventBean
import com.example.td_horoscope.bean.qq.QQBean
import com.example.td_horoscope.bean.zodiac.ZodiacBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.module
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 15:40
 * @class describe
 */
interface Api {
    //星座运势
    @GET("getAll")
    fun getTodayInfo(@Query("consName") name:String,@Query("type") type:String,@Query("key") key:String): Call<ToDayBean>
    @GET("getAll")
    fun getTomorrowInfo(@Query("consName") name:String,@Query("type") type:String,@Query("key") key:String): Call<ToDayBean>
    @GET("getAll")
    fun getWeekInfo(@Query("consName") name:String,@Query("type") type:String,@Query("key") key:String): Call<WeekBean>
    @GET("getAll")
    fun getMonthInfo(@Query("consName") name:String,@Query("type") type:String,@Query("key") key:String): Call<MonthBean>
    @GET("getAll")
    fun getYearInfo(@Query("consName") name:String,@Query("type") type:String,@Query("key") key:String): Call<YearBean>

    //黄历
    @GET("huangli/date")
    open fun getHuangLi(
        @Header("Authorization") key: String?,
        @Query("day") day: String?,
        @Query("month") month: String?,
        @Query("year") year: String?): Call<HuangLiBean>
    //往事
    @GET("toh")
    fun getHistoryEventInfo(@Query("key") key:String,@Query("v") version:String,@Query("month") month:String,@Query("day") day:String): Call<HistoryEventBean>
    //QQ测吉凶
    @GET("qq")
    fun getQQInfo(@Query("key") key:String,@Query("qq") day:String): Call<QQBean>
    //周公解梦
    @GET("query")
    fun getDreamInfo(@Query("key") key:String,@Query("q") day:String): Call<DreamBean>
    //生肖配对
    @GET("query")
    fun getZodiacInfo(@Query("key") key:String,@Query("men") man:String,@Query("women") women:String): Call<ZodiacBean>
    //星座配对
    @GET("query")
    fun getCosPlateInfo(@Query("key") key:String,@Query("men") man:String,@Query("women") women:String): Call<ConsPlateBean>



}