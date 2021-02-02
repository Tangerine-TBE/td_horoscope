package com.example.td_horoscope.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.module_ad.advertisement.AdType
import com.example.module_ad.advertisement.FeedHelper
import com.example.module_base.base.BaseFragment
import com.example.module_usercenter.utils.SpUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.*
import com.example.td_horoscope.present.impl.ConstellationPresentImpl
import com.example.td_horoscope.ui.adapter.recyclerview.ConsDesAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.view.IConstellationCallback
import kotlinx.android.synthetic.main.fragment_cons_des_two.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 17:06
 * @class describe
 */
class ConDesFragmentTwo : BaseFragment(), IConstellationCallback {

    private lateinit var mConsDesAdapter: ConsDesAdapter


    override fun getLayoutView(): Int = R.layout.fragment_cons_des_two
    private val mConstellationPresentImpl by lazy {
        ConstellationPresentImpl()
    }

    companion object {
        fun getInstance(dateType: String, cons: String): ConDesFragmentTwo {
            val cosContentFragment = ConDesFragmentTwo()
            val bundle = Bundle()
            bundle.putString(Contents.CURRENT_TYPE, dateType)
            bundle.putString(Contents.CURRENT_CONSTELLATION, cons)
            cosContentFragment.arguments = bundle
            return cosContentFragment
        }
    }


    override fun initView() {

        mDesContainer.layoutManager = LinearLayoutManager(activity)
        mConsDesAdapter = ConsDesAdapter()
        mDesContainer.adapter = mConsDesAdapter
        mConsDesAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
        showAd()
    }

    private lateinit var mFeedHelper: FeedHelper
    private fun showAd() {
        mFeedHelper = FeedHelper(activity, mHomeFeedContainerTwo)
        mFeedHelper.showAd(AdType.HOME_PAGE)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        SpUtil.removeAdView(hidden, mHomeFeedContainerTwo)
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
                Contents.WEEK -> {
                    mConstellationPresentImpl.getConsWeekMsg(consType!!, dataType)
                }
                Contents.MONTH -> {
                    mConstellationPresentImpl.getConsMonthMsg(consType!!, dataType)
                }
                Contents.YEAR -> {
                    mConstellationPresentImpl.getConsYearMsg(consType!!, dataType)
                }
                Contents.LOVE -> {
                    val loveList = getLoveList(consType)
                    mConsDesAdapter.setList(loveList)
                    switchUIByState(PageState.SUCCESS)
                }
            }
        }
    }


    private val weekList: MutableList<IconTitleBean>? = ArrayList()
    override fun onLoadWeekSuccess(week: WeekBean) {
        if (week.date == null) return
        switchUIByState(PageState.SUCCESS)
        dismissLoading()
        weekList?.apply {
            week?.let { it ->
                if (it.job?.length > 0) {
                    add(IconTitleBean(hint = "说    明", title = it.job?.substring(3)))
                }
                if (it.work?.length > 0) {
                    add(IconTitleBean(hint = "事业运", title = it.work?.substring(3)))
                }
                if (it.love?.length > 0) {
                    add(IconTitleBean(hint = "感情运", title = it.love?.substring(3)))
                }
                if (it.money?.length > 0) {
                    add(IconTitleBean(hint = "财    运", title = it.money?.substring(3)))
                }
            }
            mConsDesAdapter.setList(weekList)
        }
    }

    private val monthList: MutableList<IconTitleBean>? = ArrayList()
    override fun onLoadMonthSuccess(month: MonthBean) {
        if (month.date == null) return
        switchUIByState(PageState.SUCCESS)
        dismissLoading()
        monthList?.apply {
            month?.let { it ->
                if (it.all?.length > 0) {
                    add(IconTitleBean(hint = "说    明", title = it.all.substring(4)))
                }
                if (it.work?.length > 0) {
                    add(IconTitleBean(hint = "事业运", title = it.work.substring(4)))
                }
                if (it.love?.length > 0) {
                    add(IconTitleBean(hint = "感情运", title = it.love.substring(4)))
                }
                if (it.money?.length > 0) {
                    add(IconTitleBean(hint = "财    运", title = it.money.substring(3)))
                }
            }
            mConsDesAdapter.setList(monthList)
        }
    }


    private val yearList: MutableList<IconTitleBean>? = ArrayList()
    override fun onLoadYearSuccess(year: YearBean) {
        if (year.date == null) return
        switchUIByState(PageState.SUCCESS)
        dismissLoading()
        val mima = year.mima
        val info = mima?.info
        val text = mima?.text
        yearList?.apply {
            year?.let {
                if (!TextUtils.isEmpty(info)) {
                    add(IconTitleBean(hint = "概    述", title = info))
                }
                if (text.isNotEmpty()) {
                    add(IconTitleBean(hint = "说    明", title = text[0]))
                }
                if (it.career.isNotEmpty()) {
                    add(IconTitleBean(hint = "事业运", title = it.career[0]))
                }
                if (it.love.isNotEmpty()) {
                    add(IconTitleBean(hint = "感情运", title = it.love[0]))
                }
                if (it.finance.isNotEmpty()) {
                    add(IconTitleBean(hint = "财    运", title = it.finance[0]))
                }

            }
            mConsDesAdapter.setList(yearList)
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


    override fun onLoadToadySuccess(today: ToDayBean) {

    }

    override fun onLoadTomorrowSuccess(tomorrow: ToDayBean) {

    }

    override fun release() {
        mConstellationPresentImpl.unregisterCallback(this)
        mFeedHelper.releaseAd()
    }

    //爱情运选择
    private fun getLoveList(consType: String?): ArrayList<IconTitleBean> {
        return arrayListOf(
            when (consType) {
                Contents.BAIYANG ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_baiyang)

                Contents.JINGNIU ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_jinniu)

                Contents.SHUANGZI ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_shuangzi)

                Contents.JUXIE ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_juxie)

                Contents.SHIZI ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_shizi)

                Contents.CHUNV ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_chunv)

                Contents.TINGPING ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_tianping)

                Contents.TIANXIE ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_tianxie)

                Contents.SHESHOU ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_sheshou)

                Contents.MOJIE ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_mojie)

                Contents.SHUIPING ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_shuiping)

                Contents.SHUANGYU ->
                    IconTitleBean(hint = "感情运", title = MyContentProvider.love_shuangyu)

                else -> IconTitleBean(hint = "感情运", title = "暂无数据")

            }
        )
    }

}