package com.example.td_horoscope.ui.fragment

import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.module_base.base.BaseFragment
import com.example.module_base.util.SPUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.historyevent.HistoryEventBean
import com.example.td_horoscope.present.impl.HistoryEventImpl
import com.example.td_horoscope.ui.adapter.recyclerview.ThingDateAdapter
import com.example.td_horoscope.ui.adapter.recyclerview.ThingsAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.view.IEventCallback
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_thing.*


/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 9:40
 * @class describe
 */
class ThingFragment:BaseFragment(), IEventCallback {
    override fun getLayoutView(): Int= R.layout.fragment_thing

    private lateinit var mThingsAdapter:ThingsAdapter
    private lateinit var mThingDateAdapter: ThingDateAdapter
    private val mDateList:MutableList<String>?=ArrayList()

    override fun initView() {
       // switchUIByState(PageState.SUCCESS)
        mThingsContainer.layoutManager = LinearLayoutManager(activity)
        mThingsAdapter= ThingsAdapter()
        mThingsContainer.adapter=mThingsAdapter
        mThingsAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)


        mCurrentDate.layoutManager=GridLayoutManager(activity,10)
        mThingDateAdapter = ThingDateAdapter()
        mCurTimeString?.toCharArray()?.forEach {
            mDateList?.add(it.toString())
        }
        mThingDateAdapter.setList(mDateList)
        mCurrentDate.adapter=mThingDateAdapter
        mThingDateAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)

    }


    override fun initPresent() {
        HistoryEventImpl.registerCallback(this)
    }

    override fun initLoadData() {
        val historyEventCache = mSPUtil.getString(Contents.CACHE_HISTORY_EVENT)
        if (TextUtils.isEmpty(historyEventCache)) {
            HistoryEventImpl.getHistoryEventMsg()
        } else {
            val lastTime = mSPUtil.getString(Contents.CACHE_EVENT_TIME)
            if (mCurTimeString == lastTime) {
                val cacheThingData = Gson().fromJson(historyEventCache, HistoryEventBean::class.java)
                if (cacheThingData != null) {
                    showThingList(cacheThingData)
                }
            } else {
                HistoryEventImpl.getHistoryEventMsg()
            }
        }
    }

    override fun onReload() {
        HistoryEventImpl.getHistoryEventMsg()
    }

    override fun onLoadEventSuccess(historyEvent: HistoryEventBean) {
        dismissLoading()
        showThingList(historyEvent)
        SPUtil.getInstance().putString(Contents.CACHE_HISTORY_EVENT, Gson().toJson(historyEvent))
            .putString(Contents.CACHE_EVENT_TIME,mCurTimeString)

    }

    private fun showThingList(historyEvent: HistoryEventBean) {
        switchUIByState(PageState.SUCCESS)
        historyEvent.result?.let {
            if (it.isNotEmpty()) {
                mThingsAdapter.setList(it)
            }
        }

    }

    override fun onLoading() {
        showLoading()
    }

    override fun onError(t: String) {
        dismissLoading()
        switchUIByState(PageState.ERROR)
    }

    override fun onEmpty() {

    }


    override fun release() {
        HistoryEventImpl.unregisterCallback(this)
    }
}