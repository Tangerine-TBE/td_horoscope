package com.example.td_horoscope.ui.activity

import com.example.module_base.base.BaseActivity
import com.example.module_base.util.PackageUtil
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.util.Contents
import kotlinx.android.synthetic.main.activity_deal.*

class DealActivity : BaseActivity() {


    override fun getLayoutView(): Int =R.layout.activity_deal

    override fun initView() {
        when(intent.getIntExtra(Contents.SET_Deal1,0)) {
           0->{
               privacy_toolbar.setTitle("用户协议")
               text.text= PackageUtil.difPlatformName(this, R.string.user)
           }

            1->{
                privacy_toolbar.setTitle("隐私协议")
                text.text= PackageUtil.difPlatformName(this, R.string.privacy)
            }

        }


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