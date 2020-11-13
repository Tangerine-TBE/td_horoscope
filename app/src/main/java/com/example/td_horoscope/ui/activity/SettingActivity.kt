package com.example.td_horoscope.ui.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_ad.advertisement.FeedHelper
import com.example.module_base.widget.MyToolbar
import com.example.module_usercenter.utils.SpUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.ui.adapter.recyclerview.SetAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.top.toOtherActivity
import com.tamsiree.rxkit.view.RxToast
import com.tamsiree.rxui.view.dialog.RxDialogSureCancel
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: MainBaseActivity() {

    private lateinit var mFeedAd: FeedHelper
    override fun getLayoutView(): Int=R.layout.activity_setting

    private lateinit var mSetAdapter:SetAdapter

    private val mRxDialogSureCancel by lazy {
        RxDialogSureCancel(this).apply {
            setContent("你确定要退出登录吗？")
        }
    }

    override fun initView() {
        mFeedAd=FeedHelper(this,mSetAdContainer)
        mFeedAd.showAd()

        mSetRv.layoutManager = LinearLayoutManager(this)
        mSetAdapter = SetAdapter()
        mSetAdapter.setList(arrayListOf(
                IconTitleBean(hint = "用户协议"),
                IconTitleBean(hint = "隐私政策"),
                IconTitleBean(hint = "退出登录")
        ))
        mSetRv.adapter=mSetAdapter

    }

    override fun initEvent() {
        mSetBar.setOnBackClickListener(object :MyToolbar.OnBackClickListener{
            override fun onBack() {
                finish()
            }

            override fun onRightTo() {

            }
        })


        mSetAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0-> toOtherActivity<DealActivity>(this,false){
                    putExtra(Contents.SET_Deal1,position)
                }
                1-> toOtherActivity<DealActivity>(this,false){
                    putExtra(Contents.SET_Deal1,position)
                }
                2->{
                    if (mSPUtil.getBoolean(com.example.module_usercenter.utils.Contents.USER_IS_LOGIN, false)) {
                        mRxDialogSureCancel.show()
                    } else {
                        RxToast.warning("您还没有登录")
                    }
                }
            }

        }

        mRxDialogSureCancel.setSureListener(View.OnClickListener {
            SpUtil.deleteUserInfo()
            mRxDialogSureCancel.dismiss()
            finish()
        })

        mRxDialogSureCancel.setCancelListener(View.OnClickListener {
            mRxDialogSureCancel.dismiss()
        })

    }

    override fun release() {
        mFeedAd.showAd()
    }



}