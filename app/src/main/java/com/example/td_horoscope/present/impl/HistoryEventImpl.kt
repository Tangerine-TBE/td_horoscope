package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.bean.historyevent.HistoryEventBean
import com.example.td_horoscope.present.IEventPresent
import com.example.td_horoscope.view.IEventCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.present.impl
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 14:22
 * @class describe
 */
object HistoryEventImpl:BasePresent<IEventCallback>(),IEventPresent {

    override fun getHistoryEventMsg() {
        mCallbacks.forEach {
            it.onLoading()
        }
        NetDataProvider.getEventInfo(object : Callback<HistoryEventBean>{
            override fun onFailure(call: Call<HistoryEventBean>, t: Throwable) {
                mCallbacks.forEach {
                    it.onError(t.toString())
                }
            }
            override fun onResponse(call: Call<HistoryEventBean>, response: Response<HistoryEventBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    if (body != null) {
                        mCallbacks.forEach {
                            it.onLoadEventSuccess(body)
                        }
                    }
                }
            }
        })

    }

}