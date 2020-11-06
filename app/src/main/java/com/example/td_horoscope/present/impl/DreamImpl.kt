package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.bean.dream.DreamBean
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.present.IDreamPresent
import com.example.td_horoscope.view.IDreamCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
object DreamImpl:BasePresent<IDreamCallback>(),IDreamPresent{

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

    override fun getDreamMsg(qq:String) {
        netLoading()
        NetDataProvider.getDreamInfo(qq,object :Callback<DreamBean>{
            override fun onFailure(call: Call<DreamBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<DreamBean>, response: Response<DreamBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    body?.let {
                            mCallbacks.forEach {
                                it.onLoadDreamSuccess(body)
                            }
                        }
                }
            }

        })
    }
}