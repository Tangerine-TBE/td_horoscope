package com.example.td_horoscope.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.util
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 15:26
 * @class describe
 */
object RetrofitManager {

    var cosRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Contents.CONSTELLATION_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    var hlRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Contents.HUANG_LI_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var wsRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Contents.HISTORY_EVENT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var qqRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Contents.QQ_TEST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var zgRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Contents.ZG_DREAM_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var xsRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(Contents.ZODIAC_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var pdRetrofit:Retrofit = Retrofit.Builder()
            .baseUrl(Contents.CONSTELLATION_PLATE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


   inline fun <reified T> createConstellation(): T = cosRetrofit.create(T::class.java)

   inline fun <reified T> createHuangLi(): T = hlRetrofit.create(T::class.java)

   inline fun <reified T> createHistoryEvent(): T = wsRetrofit.create(T::class.java)

   inline fun <reified T> createQQ(): T = qqRetrofit.create(T::class.java)

   inline fun <reified T> createDream(): T = zgRetrofit.create(T::class.java)

   inline fun <reified T> createZodiac(): T = xsRetrofit.create(T::class.java)

   inline fun <reified T> createPlate(): T = pdRetrofit.create(T::class.java)




}