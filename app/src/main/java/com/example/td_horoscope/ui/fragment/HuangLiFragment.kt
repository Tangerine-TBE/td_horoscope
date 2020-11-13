package com.example.td_horoscope.ui.fragment

import android.text.TextUtils
import com.example.module_base.base.BaseFragment
import com.example.module_base.util.SPUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.HuangLiBean
import com.example.td_horoscope.present.impl.HuangLiImpl
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.LogUtil
import com.example.td_horoscope.view.IHuangLiCallback
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_huangli.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 9:39
 * @class describe
 */
class HuangLiFragment:BaseFragment(), IHuangLiCallback {
    override fun getLayoutView(): Int= R.layout.fragment_huangli

    override fun initView() {

    }

    override fun initPresent() {
        HuangLiImpl.registerCallback(this)
    }

    override fun initLoadData() {
        val huangLiCache = mSPUtil.getString(Contents.CACHE_HUANG_LI)
        if (TextUtils.isEmpty(huangLiCache)) {
            HuangLiImpl.getHuangLiMsg()
        } else {
            val lastTime = mSPUtil.getString(Contents.CACHE_HL_TIME)
            LogUtil.i(this,"----mSPUtil-----$lastTime--------$mCurTimeString------>")
            if (mCurTimeString == lastTime) {
                val cacheHuangLiData =Gson().fromJson(huangLiCache,HuangLiBean::class.java)
                if (cacheHuangLiData != null) {
                    showHuangLi(cacheHuangLiData)
                }
            } else {
                HuangLiImpl.getHuangLiMsg()
            }
        }
    }

    override fun onReload() {
        HuangLiImpl.getHuangLiMsg()
    }

    private fun showHuangLi(data: HuangLiBean) {
        switchUIByState(PageState.SUCCESS)
        val resultBean = data.result
        resultBean.suici
        val stringBuffer = StringBuffer()
        val suici: List<String> = resultBean.suici
        for (s in suici) {
            stringBuffer.append("$s  ")
        }
        tv_hl_date.text=
            resultBean.year+ "." + resultBean.month + "." + resultBean.day

        val nongli: String = resultBean.nongli
        tv_hl_nongli.text=nongli.substring(7, nongli.length)
        tv_hl_suici.text=stringBuffer.toString() + "  星期" + resultBean.week

        // 宜
        val stringBuffer1 = StringBuffer()
        for (s in resultBean.yi) {
            stringBuffer1.append("$s  ")
        }
        tv_yi.text=stringBuffer1
        //忌
        val stringBuffer2 = StringBuffer()
        for (s in resultBean.ji) {
            stringBuffer2.append("$s  ")
        }
        tv_ji.text=stringBuffer2
        tv_xiongsheng.text=resultBean.jiri
        tv_jishenyiqu.text=resultBean.xiongshen
        tv_c_hl_text.text=resultBean.chong + "  " + resultBean.sha
        tv_s_hl_text.text=resultBean.wuxing
        tv_jiri.text=resultBean.jishenyiqu


    }


    override fun onLoadHuangLiSuccess(huangLi: HuangLiBean) {
        dismissLoading()
        showHuangLi(huangLi)
        SPUtil.getInstance().putString(Contents.CACHE_HUANG_LI, Gson().toJson(huangLi))
                .putString(Contents.CACHE_HL_TIME,mCurTimeString)


    }

    override fun onLoading() {
        showLoading()
    }

    override fun onError(t: String) {
        dismissLoading()
        // RxToast.warning("网络异常，请检查网络连接")
        switchUIByState(PageState.ERROR)
    }

    override fun onEmpty() {

    }

    override fun release() {
        HuangLiImpl.unregisterCallback( this)
    }

}