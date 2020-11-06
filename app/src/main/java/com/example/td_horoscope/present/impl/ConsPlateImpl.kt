package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.bean.consplate.ConsPlateBean
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.present.IConsPlatePresent
import com.example.td_horoscope.view.IPlateCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
object ConsPlateImpl:BasePresent<IPlateCallback>(),
    IConsPlatePresent {

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

    override fun getPlateMsg(man:String,women:String) {
        netLoading()
        NetDataProvider.getPlateInfo(man,women,object :Callback<ConsPlateBean>{
            override fun onFailure(call: Call<ConsPlateBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<ConsPlateBean>, response: Response<ConsPlateBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    body?.let {
                            mCallbacks.forEach {
                                it.onLoadPlateSuccess(body)
                            }
                        }
                }
            }

        })
    }
}