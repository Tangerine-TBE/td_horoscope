package com.example.td_horoscope.ui.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_ad.advertisement.AdType
import com.example.module_ad.advertisement.FeedHelper
import com.example.module_base.util.ToastUtil
import com.example.module_base.widget.MyToolbar
import com.example.module_usercenter.bean.RegisterBean
import com.example.module_usercenter.present.impl.LogoutPresentImpl
import com.example.module_usercenter.utils.SpUtil
import com.example.module_usercenter.view.ILogoutCallback
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.ui.adapter.recyclerview.SetAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.top.toOtherActivity
import com.feisukj.base.widget.Rx.RxDialogSureCancel
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: MainBaseActivity(), ILogoutCallback {

    private lateinit var mFeedAd: FeedHelper
    override fun getLayoutView(): Int=R.layout.activity_setting
    private lateinit var mSetAdapter:SetAdapter
    private val mLogoutPresent by lazy {
        LogoutPresentImpl.getInstance()
    }
    private val mRxDialogSureCancel by lazy {
        RxDialogSureCancel(this).apply {
            setContent("您确定要退出登录吗？")
        }
    }

    private val mLogoutDialogSureCancel by lazy {
        RxDialogSureCancel(this).apply {
            setContent("您确定要注销账号吗？")
        }
    }



    override fun initView() {
        mFeedAd=FeedHelper(this,mSetAdContainer)
        mFeedAd.showAd(AdType.MY_PAGE)

        mSetRv.layoutManager = LinearLayoutManager(this)
        mSetAdapter = SetAdapter()
        mSetAdapter.setList(arrayListOf(
                IconTitleBean(hint = "用户协议"),
                IconTitleBean(hint = "隐私政策"),
                IconTitleBean(hint = "退出登录"),
                IconTitleBean(hint = "永久注销")
        ))
        mSetRv.adapter=mSetAdapter

    }
    override fun initPresent() {
        mLogoutPresent.registerCallback(this)
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
                    if (!mSPUtil.getBoolean(com.example.module_usercenter.utils.Contents.USER_IS_LOGIN, false)) {
                        ToastUtil.showToast("您还没有登录")
                    } else {
                        mRxDialogSureCancel.show()
                    }
                }
                3->{
                    if (!mSPUtil.getBoolean(com.example.module_usercenter.utils.Contents.USER_IS_LOGIN, false)) {
                        ToastUtil.showToast("您还没有登录")
                    } else {
                        mLogoutDialogSureCancel.show()
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

        mLogoutDialogSureCancel.setSureListener(View.OnClickListener {
            mLogoutPresent.toLogout(mSPUtil.getString(com.example.module_usercenter.utils.Contents.USER_ID))
        })
        mLogoutDialogSureCancel.setCancelListener(View.OnClickListener {
            mLogoutDialogSureCancel.dismiss()
        })
    }


    override fun release() {
        mFeedAd.showAd(AdType.MY_PAGE)
        mLogoutPresent.unregisterCallback(this)
        mRxDialogSureCancel?.dismiss()
        mLogoutDialogSureCancel?.dismiss()
    }

    override fun onLoading() {

    }

    override fun onLogoutLoading() {
        mLogoutDialogSureCancel.dismiss()
        mLoadingDialog.show()
    }

    override fun onLogoutSuccess(registerBean: RegisterBean?) {
        mLoadingDialog.dismiss()
        if (registerBean?.ret == 200) {
            SpUtil.deleteUserInfo()
            ToastUtil.showToast("注销成功！")
            finish()
        } else {
            ToastUtil.showToast("注销失败！")
        }
    }

    override fun onLogoutError() {
        mLoadingDialog.dismiss()
        ToastUtil.showToast("注销失败！")
    }

    override fun onError() {

    }


}