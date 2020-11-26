package com.example.td_horoscope.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.module_ad.advertisement.AdType
import com.example.module_ad.advertisement.FeedHelper
import com.example.module_base.base.BaseFragment
import com.example.module_usercenter.utils.SpUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.*
import com.example.td_horoscope.present.impl.ConstellationPresentImpl
import com.example.td_horoscope.ui.adapter.recyclerview.ConsDesAdapter
import com.example.td_horoscope.ui.adapter.recyclerview.ConsIndexAdapter
import com.example.td_horoscope.ui.adapter.recyclerview.ConsLikeAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.LogUtil
import com.example.td_horoscope.view.IConstellationCallback
import kotlinx.android.synthetic.main.fragment_cons_des.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 17:06
 * @class describe
 */
class ConDesFragmentOne : BaseFragment(), IConstellationCallback {
    override fun getLayoutView(): Int = R.layout.fragment_cons_des

    private val mConstellationPresentImpl by lazy {
        ConstellationPresentImpl()
    }

    private lateinit var mConsIndexAdapter: ConsIndexAdapter
    private lateinit var mConsLikeAdapter: ConsLikeAdapter
    private lateinit var mConsDesAdapter: ConsDesAdapter

    companion object {
        fun getInstance(dateType: String, cons: String): ConDesFragmentOne {
            val cosContentFragment = ConDesFragmentOne()
            val bundle = Bundle()
            bundle.putString(Contents.CURRENT_TYPE, dateType)
            bundle.putString(Contents.CURRENT_CONSTELLATION, cons)
            cosContentFragment.arguments = bundle
            return cosContentFragment
        }
    }

    override fun initView() {
        mConsIndex.layoutManager = LinearLayoutManager(activity)
        mConsIndexAdapter = ConsIndexAdapter()
        mConsIndex.adapter = mConsIndexAdapter
        mConsIndexAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)

        mConsLike.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        mConsLikeAdapter = ConsLikeAdapter()
        mConsLike.adapter = mConsLikeAdapter
        mConsLikeAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight)

        mConsDes.layoutManager = LinearLayoutManager(activity)
        mConsDesAdapter = ConsDesAdapter()
        mConsDes.adapter = mConsDesAdapter
        mConsDesAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)

        showAd()
    }
    private lateinit var mFeedHelper: FeedHelper
    private fun  showAd(){
        mFeedHelper = FeedHelper(activity, mHomeFeedContainerOne)
        mFeedHelper.showAd(AdType.HOME_PAGE)
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        SpUtil.removeAdView(hidden,mHomeFeedContainerOne)
    }

    override fun initPresent() {
        mConstellationPresentImpl.registerCallback(this)

    }

    override fun initLoadData() {
        loadNetData()
    }


    override fun onReload() {
        loadNetData()
    }

    private fun loadNetData() {
        arguments?.let {
            val consType = it.getString(Contents.CURRENT_CONSTELLATION)
            when (val dataType = it.getString(Contents.CURRENT_TYPE)) {
                Contents.TODAY -> {
                    mConstellationPresentImpl.getConsDayMsg(consType!!, dataType)
                }
                Contents.TOMORROW -> {
                    mConstellationPresentImpl.getConsTomorrowMsg(consType!!, dataType)
                }
            }
            mConstellationPresentImpl.getConsWeekMsg(consType!!, Contents.WEEK)
        }
    }


    override fun release() {
        mConstellationPresentImpl.unregisterCallback(this)
        mFeedHelper.releaseAd()
    }



    private fun showDayInfo(dayData: ToDayBean) {
        switchUIByState(PageState.SUCCESS)
        dismissLoading()
        dayData.all?.let {
            mConsIndexAdapter.setList(arrayListOf(
                    IconTitleBean(hint = "综合运势：", title = dayData.all),
                    IconTitleBean(hint = "工作运势：", title = dayData.work),
                    IconTitleBean(hint = "爱情运势：", title = dayData.love),
                    IconTitleBean(hint = "理财运势：", title = dayData.money)
            ))

            LogUtil.i(this,"------------${dayData.name}--${dayData.all}----${dayData.work}------${dayData.love}-------${dayData.money}---->")

            mConsLikeAdapter.setList(arrayListOf(
                    IconTitleBean(hint = "速配星座：", title = dayData.QFriend),
                    IconTitleBean(hint = "幸运色：", title = dayData.color),
                    IconTitleBean(hint = "健康指数：", title = dayData.health)
            ))


            mluckNumber.text=dayData.number.toString()
            visible(mluckNumber,mHint)
        }
    }
    override fun onLoadToadySuccess(dayData: ToDayBean) {
        showDayInfo(dayData)

    }

    override fun onLoadTomorrowSuccess(dayData: ToDayBean) {
        showDayInfo(dayData)
        LogUtil.i(this,"---onLoadTomorrowSuccess--${dayData.toString()}-----")
    }

    override fun onLoadWeekSuccess(week: WeekBean) {
        if (week.date == null) return
        week?.let {
            val weekList = arrayListOf(
                    IconTitleBean(hint = "说    明", title = it.job.substring(3)),
                    IconTitleBean(hint = "事业运", title = it.work.substring(3)),
                    IconTitleBean(hint = "感情运", title = it.love.substring(3)),
                    IconTitleBean(hint = "财    运", title = it.money.substring(3))
            )
            mConsDesAdapter.setList(weekList)
        }
    }

    override fun onLoading() {
        showLoading()
    }

    override fun onError(t: String) {
        dismissLoading()
       //RxToast.warning("网络异常，请检查网络连接")
        switchUIByState(PageState.ERROR)
    }


    override fun onLoadMonthSuccess(month: MonthBean) {

    }

    override fun onLoadYearSuccess(year: YearBean) {

    }

    override fun onEmpty() {

    }

}