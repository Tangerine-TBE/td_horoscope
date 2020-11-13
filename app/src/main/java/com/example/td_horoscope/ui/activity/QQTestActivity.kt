package com.example.td_horoscope.ui.activity

import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.bean.qq.QQBean
import com.example.td_horoscope.present.impl.QQImpl
import com.example.td_horoscope.ui.adapter.recyclerview.QQTestAdapter
import com.example.td_horoscope.view.IQQCallback
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_qq_test.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.activity
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 15:08
 * @class describe
 */
class QQTestActivity: MainBaseActivity() , IQQCallback {

    private lateinit var mQQTestAdapter: QQTestAdapter
    override fun getLayoutView(): Int = R.layout.activity_qq_test
    override fun initView() {
        mQQContainer.layoutManager = LinearLayoutManager(this)
        mQQTestAdapter= QQTestAdapter()
        mQQContainer.adapter = mQQTestAdapter


    }

    override fun initPresent() {
        QQImpl.registerCallback(this)
    }

    override fun initEvent() {
        mQQTest.setOnClickListener {
            val qqData = mQQInput.text.toString()
            if (TextUtils.isEmpty(qqData)) RxToast.warning("QQ号码不能为空") else QQImpl.getQQMsg(qqData)
        }

        mQQBar.setOnBackClickListener(object: MyToolbar.OnBackClickListener{
            override fun onBack() {
               finish()
            }

            override fun onRightTo() {

            }
        })

    }


    override fun onLoadQQSuccess(qqData: QQBean) {
        dismissLoading()
        if (qqData.error_code == 0) {
            qqData.result.data?.let {
                mQQTestAdapter.setList(
                    arrayListOf(
                        IconTitleBean(hint ="QQ号码测试结论",title = it.conclusion ),
                        IconTitleBean(hint ="结论分析",title = it.analysis )
                    )
                )
            }
        } else {
            RxToast.warning(qqData.reason)
        }
    }

    override fun onLoading() {
        showLoading()
    }

    override fun onError(t: String) {
        dismissLoading()
        RxToast.warning("无网络连接，请检查网络设置")
    }

    override fun onEmpty() {
        dismissLoading()
    }

    override fun release() {
        QQImpl.unregisterCallback(this)
    }
}