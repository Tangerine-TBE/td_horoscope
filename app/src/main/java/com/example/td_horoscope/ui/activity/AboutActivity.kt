package com.example.td_horoscope.ui.activity

import com.example.module_base.base.BaseActivity
import com.example.module_base.util.PackageUtil
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity() {



    override fun getLayoutView(): Int=R.layout.activity_about

    override fun initEvent() {
        version.text= PackageUtil.packageCode(this)

        mAboutBar.setOnBackClickListener(object :MyToolbar.OnBackClickListener{
            override fun onBack() {
                finish()
            }

            override fun onRightTo() {

            }
        })
    }
}