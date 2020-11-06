package com.example.td_horoscope.present.impl

import com.example.module_base.base.BasePresent
import com.example.td_horoscope.bean.MonthBean
import com.example.td_horoscope.bean.ToDayBean
import com.example.td_horoscope.bean.WeekBean
import com.example.td_horoscope.bean.YearBean
import com.example.td_horoscope.module.NetDataProvider
import com.example.td_horoscope.present.IConstellationPresent
import com.example.td_horoscope.view.IConstellationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.present.impl
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 16:15
 * @class describe
 */
class  ConstellationPresentImpl: BasePresent<IConstellationCallback>(),IConstellationPresent{



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

    override fun getConsDayMsg(name: String, type: String) {
        netLoading()
        NetDataProvider.getTodayConsInfo(name,type,object:Callback<ToDayBean>{
            override fun onFailure(call: Call<ToDayBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<ToDayBean>, response: Response<ToDayBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    mCallbacks.forEach {
                        it.onLoadToadySuccess(body!!)
                    }
                }
            }
        } )
    }

    override fun getConsTomorrowMsg(name: String, type: String) {
        NetDataProvider.getTomorrowConsInfo(name,type,object:Callback<ToDayBean>{
            override fun onFailure(call: Call<ToDayBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<ToDayBean>, response: Response<ToDayBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    mCallbacks.forEach {
                        it.onLoadTomorrowSuccess(body!!)
                    }
                }
            }
        } )
    }

    override fun getConsWeekMsg(name: String, type: String) {
        NetDataProvider.getWeekConsInfo(name,type,object:Callback<WeekBean>{
            override fun onFailure(call: Call<WeekBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<WeekBean>, response: Response<WeekBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    mCallbacks.forEach {
                        it.onLoadWeekSuccess(body!!)
                    }
                }
            }
        } )
    }

    override fun getConsMonthMsg(name: String, type: String) {
        netLoading()
        NetDataProvider.getMonthConsInfo(name,type,object:Callback<MonthBean>{
            override fun onFailure(call: Call<MonthBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<MonthBean>, response: Response<MonthBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    mCallbacks.forEach {
                        it.onLoadMonthSuccess(body!!)
                    }
                }
            }
        } )
    }

    override fun getConsYearMsg(name: String, type: String) {
        NetDataProvider.getYearConsInfo(name,type,object:Callback<YearBean>{
            override fun onFailure(call: Call<YearBean>, t: Throwable) {
                netError(t)
            }
            override fun onResponse(call: Call<YearBean>, response: Response<YearBean>) {
                if (response.code()==HttpURLConnection.HTTP_OK) {
                    val body = response.body()
                    mCallbacks.forEach {
                        it.onLoadYearSuccess(body!!)
                    }
                }
            }
        } )
    }
}