package com.example.td_horoscope.ui.activity

import android.view.Gravity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.zodiac.ZodiacBean
import com.example.td_horoscope.present.impl.ZodiacImpl
import com.example.td_horoscope.ui.adapter.recyclerview.ZodiacAdapter
import com.example.td_horoscope.ui.widget.ZodiacPopup
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.view.IZodiacCallback
import com.tamsiree.rxkit.RxImageTool
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_zodiac.*

class ZodiacActivity : MainBaseActivity() , IZodiacCallback {

    private val mZodiacAdapter by lazy {
        ZodiacAdapter()
    }
    private val mZodiacPopup by lazy {
        ZodiacPopup(this)
    }

    override fun getLayoutView(): Int = R.layout.activity_zodiac
    override fun initView() {
        mZodContainer.layoutManager = GridLayoutManager(this, 3)
        mZodiacAdapter.setList(MyContentProvider.zodiacList)
        mZodContainer.adapter = mZodiacAdapter
    }

    override fun initPresent() {
        ZodiacImpl.registerCallback(this)
    }


    override fun initEvent() {
        mZodiacAdapter.setOnItemClickListener { adapter, view, position ->
            mZodiacAdapter.setPosition(position)
            mZodiacAdapter.notifyDataSetChanged()
        }

        mZodiacBtn.setOnClickListener {
            mZodiacAdapter.getSelectList()?.let {
                if (it.size != 2) {
                    RxToast.warning("请选择两个生肖进行配对")
                } else {
                    ZodiacImpl.getZodiacMsg(it[0].title, it[1].title)
                }

            }
        }

        mZodiacBar.setOnBackClickListener(object : MyToolbar.OnBackClickListener {
            override fun onBack() {
                finish()
            }

            override fun onRightTo() {

            }
        })
    }



    override fun onLoadZodiacSuccess(zodiac: ZodiacBean) {
        dismissLoading()
        mZodiacPopup.setPopupResult(zodiac, mZodiacAdapter?.getSelectList())
        mZodiacPopup.showAsDropDown(mZodiacHint, 0, RxImageTool.dip2px(23f), Gravity.CENTER)

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
        ZodiacImpl.unregisterCallback(this)
    }


}