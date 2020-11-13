package com.example.td_horoscope.ui.activity

import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.bean.dream.DreamBean
import com.example.td_horoscope.present.impl.DreamImpl
import com.example.td_horoscope.ui.adapter.recyclerview.DreamAdapter
import com.example.td_horoscope.view.IDreamCallback
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_zg_dream.*


class ZgDreamActivity : MainBaseActivity() , IDreamCallback {

    override fun getLayoutView(): Int = R.layout.activity_zg_dream

    private lateinit var mDreamAdapter: DreamAdapter
    private var mDreamList: MutableList<IconTitleBean>? = ArrayList()


    override fun initView() {
        mDreamContainer.layoutManager = LinearLayoutManager(this)
        mDreamAdapter = DreamAdapter()
        mDreamContainer.adapter = mDreamAdapter
        mDreamAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
    }

    override fun initPresent() {
        DreamImpl.registerCallback(this)
    }


    override fun initEvent() {
        mDreamBar.setOnBackClickListener(object : MyToolbar.OnBackClickListener {
            override fun onBack() {
                finish()
            }

            override fun onRightTo() {

            }
        })

        mDreamBtn.setOnClickListener {
            if (mDreamBtn.text == "开始解梦") {
                val dreamData = mDreamText.text.toString()
                if (TextUtils.isEmpty(dreamData)) RxToast.warning("梦的内容不能为空") else DreamImpl.getDreamMsg(
                        dreamData
                )
            } else {
                mDreamBtn.text = "开始解梦"
                visible(mInputLayout)
                invisible(mDreamContainer)
            }
        }




    }


    override fun onLoadDreamSuccess(dreamData: DreamBean) {
        mDreamList?.clear()
        dismissLoading()
        val dreamList = dreamData.result
        if (dreamList != null) {
            dreamList.forEach {
                mDreamList?.add(IconTitleBean(hint = it.title, title = it.des))
            }

            mDreamAdapter.setList(mDreamList)
            visible(mDreamContainer)
            invisible(mInputLayout)
            mDreamBtn.text = "再次解梦"

        } else {
            RxToast.warning("格式错误")
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
        DreamImpl.unregisterCallback(this)
        mDreamList=null
    }
}