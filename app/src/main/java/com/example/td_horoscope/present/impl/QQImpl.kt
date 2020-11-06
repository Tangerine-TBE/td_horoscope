package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.bean.qq.QQBean
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.present.IQQiPresent
import com.example.td_horoscope.view.IQQCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
object QQImpl:BasePresent<IQQCallback>(),IQQiPresent{

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

    override fun getQQMsg(qq:String) {
        netLoading()
        NetDataProvider.getQQInfo(qq,object :Callback<QQBean>{
            override fun onFailure(call: Call<QQBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<QQBean>, response: Response<QQBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    body?.let {
                            mCallbacks.forEach {
                                it.onLoadQQSuccess(body)
                            }
                        }
                }
            }

        })
    }
}