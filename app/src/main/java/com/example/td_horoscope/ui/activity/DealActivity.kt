package com.example.td_horoscope.ui.activity

import com.example.module_base.util.PackageUtil
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.util.Contents
import kotlinx.android.synthetic.main.activity_deal.*

class DealActivity : MainBaseActivity()  {


    override fun getLayoutView(): Int =R.layout.activity_deal
    var mTitleMsg="用户协议"
    var mContent=R.string.user
    override fun initView() {
        when (intent.getIntExtra(Contents.SET_Deal1, 0)) {
            0 -> {
                mTitleMsg="用户协议"
                mContent=R.string.user
            }
            1 -> {
                mTitleMsg="隐私协议"
                mContent=R.string.privacy
            }
        }
        privacy_toolbar.setTitle(mTitleMsg)
        text.text = PackageUtil.difPlatformName(this,mContent)
    }

    override fun initEvent() {
        privacy_toolbar.setOnBackClickListener(object :MyToolbar.OnBackClickListener{
            override fun onBack() {
               finish()
            }

            override fun onRightTo() {

            }
        })
    }

}