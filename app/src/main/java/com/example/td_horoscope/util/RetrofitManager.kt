package com.example.td_horoscope.util

import com.example.module_base.util.LogUtils
import com.example.td_horoscope.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

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
        .client(getClient()?.build()!!)
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


    private fun getClient(): OkHttpClient.Builder? {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
        //add log record
        if (BuildConfig.DEBUG) {
            //打印网络请求日志
            val httpLoggingInterceptor = LoggingInterceptor()
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return httpClientBuilder
    }

    class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            val request = chain.request()
            val t1 = System.nanoTime() //请求发起的时间
            LogUtils.i(
                String.format(
                    "LoggingInterceptor 发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers(), request.body()
                )
            )
            val response = chain.proceed(request)
            val t2 = System.nanoTime() //收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            val responseBody = response.peekBody((1024 * 1024).toLong())
            LogUtils.i(
                String.format(
                    "LoggingInterceptor 接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6,
                    response.headers()
                )
            )
            return response
        }
    }

}