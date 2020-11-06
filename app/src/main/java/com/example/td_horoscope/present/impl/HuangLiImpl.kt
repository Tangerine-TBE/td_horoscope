package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.bean.HuangLiBean
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.present.IHuangLiPresent
import com.example.td_horoscope.view.IHuangLiCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
object HuangLiImpl:BasePresent<IHuangLiCallback>(),IHuangLiPresent{

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
    override fun getHuangLiMsg() {
        netLoading()
        NetDataProvider.getHuangLiInfo(object :Callback<HuangLiBean>{
            override fun onFailure(call: Call<HuangLiBean>, t: Throwable) {
                netError(t)
            }

            override fun onResponse(call: Call<HuangLiBean>, response: Response<HuangLiBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    body?.let {
                            mCallbacks.forEach {
                                it.onLoadHuangLiSuccess(body)
                            }
                        }
                }
            }

        })
    }


}