package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.bean.zodiac.ZodiacBean
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.present.IZodiacPresent
import com.example.td_horoscope.view.IZodiacCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
object ZodiacImpl:BasePresent<IZodiacCallback>(),IZodiacPresent{

    private fun netLoading() {
        mCallbacks.forEach {
            it.onLoading()
        }
    }

    private fun netError(t: Throwable) {
        mCallbacks.forEach {
            it.onError(t.toString())
        }
    }

    override fun getZodiacMsg(man:String,women:String) {
        netLoading()
        NetDataProvider.getZodiacInfo(man,women,object :Callback<ZodiacBean>{
            override fun onFailure(call: Call<ZodiacBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<ZodiacBean>, response: Response<ZodiacBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    body?.let {
                            mCallbacks.forEach {
                                it.onLoadZodiacSuccess(body)
                            }
                        }
                }
            }

        })
    }
}