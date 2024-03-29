package com.example.td_horoscope.ui.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_ad.advertisement.AdType
import com.example.module_ad.advertisement.FeedHelper
import com.example.module_base.base.BaseFragment
import com.example.module_usercenter.bean.*
import com.example.module_usercenter.present.impl.LoginPresentImpl
import com.example.module_usercenter.present.impl.ThirdlyLoginPresentImpl
import com.example.module_usercenter.present.impl.WeChatPresentImpl
import com.example.module_usercenter.ui.activity.BuyVipActivity
import com.example.module_usercenter.ui.activity.LoginActivity
import com.example.module_usercenter.utils.Contents
import com.example.module_usercenter.view.ILoginCallback
import com.example.module_usercenter.view.IThirdlyLoginCallback
import com.example.module_usercenter.view.IWeChatCallback
import com.example.td_horoscope.R
import com.example.td_horoscope.ui.activity.AboutActivity
import com.example.td_horoscope.ui.activity.DealActivity
import com.example.td_horoscope.ui.activity.FeedbackActivity
import com.example.td_horoscope.ui.adapter.recyclerview.SettingAdapter
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.util.top.toOtherActivity
import kotlinx.android.synthetic.main.fragment_my.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 9:41
 * @class describe
 */
class MyFragment : BaseFragment(), ILoginCallback, IWeChatCallback, IThirdlyLoginCallback {
    override fun getLayoutView(): Int = R.layout.fragment_my
    private lateinit var mSettingAdapter: SettingAdapter
    private val mLoginPresent by lazy {
        LoginPresentImpl.getInstance()
    }

    private val mThirdlyLoginPresent by lazy {
        ThirdlyLoginPresentImpl.getInstance()
    }
    private val mWeChatPresent by lazy {
        WeChatPresentImpl.getInstance()
    }

    private var mIsLogin=false
    private lateinit var mFeedAd:FeedHelper


    override fun initView() {
        switchUIByState(PageState.SUCCESS)
        mSetContainer.layoutManager = LinearLayoutManager(activity)
        mSettingAdapter =
            SettingAdapter()
        mSettingAdapter.data=MyContentProvider.settingList
        mSetContainer.adapter=mSettingAdapter
        showAd()
    }




    private fun showAd() {
        mFeedAd = FeedHelper(activity, mMyAdContainer)
        mFeedAd.showAd(AdType.MY_PAGE)
    }

    override fun initPresent() {
        mLoginPresent.registerCallback(this)
        mWeChatPresent.registerCallback(this)
        mThirdlyLoginPresent.registerCallback(this)

    }

    override fun release() {
        mLoginPresent.unregisterCallback(this)
        mWeChatPresent.unregisterCallback(this)
        mThirdlyLoginPresent.unregisterCallback(this)
        mFeedAd.releaseAd()
    }

    override fun initEvent() {
        mSettingAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> startActivity(Intent(mActivity,FeedbackActivity::class.java))
                1 -> toOtherActivity<AboutActivity>(activity) {}
                3->{
                    toOtherActivity<DealActivity>(activity) {
                        putExtra(com.example.td_horoscope.util.Contents.SET_Deal1,0)
                    }
                }
                4->{
                    toOtherActivity<DealActivity>(activity) {
                        putExtra(com.example.td_horoscope.util.Contents.SET_Deal1,1)
                    }
                }
            }

        }

        mLoginInclude.setOnClickListener {
            if (!mSPUtil.getBoolean(Contents.USER_IS_LOGIN, false)) {
                toOtherActivity<LoginActivity>(activity, false) {}
                mSPUtil.putBoolean(Contents.BUY_PAGER, false)
            }
        }

        mVipTitle.setOnClickListener {
            toOtherActivity<BuyVipActivity>(activity,false){putExtra(Contents.TO_BUY,false)}
        }

    }


    override fun onResume() {
        super.onResume()
        mIsLogin = mSPUtil.getBoolean(Contents.USER_IS_LOGIN, false)
        var userId = mSPUtil.getString(Contents.USER_ID, "");
        var userVip = mSPUtil.getInt(Contents.USER_VIP_LEVEL, 0);
        var userVipTime = mSPUtil.getString(Contents.USER_VIP_TIME, "");
        if (mIsLogin) {
            mLoginId.text=userId
            if (userVip > 0) {
                mMyAdContainer.removeAllViews()
            }
            mLoginHint.text= if(userVip==0) "您还不是VIP"  else "VIP等级:$userVip  有效期:$userVipTime"
            mLoginPic.setImageResource(R.mipmap.icon_login)

        } else {
            logoutState()
        }
    }

    private fun logoutState() {
        mLoginId?.text="立即登录"
        mLoginHint?.text="登录数据不丢失，享受更多功能"
        mLoginPic?.setImageResource(R.mipmap.icon_no_login)
        showAd()
    }


    private fun loginState(loginBean: LoginBean?) {
        loginBean?.data?.let {
            mLoginId?.text=it.id.toString()
            mLoginHint?.text=  if (it.vip==0) "您还不是VIP" else "VIP等级:"+it.vip+"  有效期:"+it.vipexpiretime
            if (it.vip> 0) {
                mMyAdContainer.removeAllViews()
            }
            mLoginPic?.setImageResource(R.mipmap.icon_login)
        }
    }


    override fun onLoginSuccess(loginBean: LoginBean?) {
        loginState(loginBean)
    }

    override fun onWxLoginError() {

    }

    override fun onThirdlyRegisterError() {

    }

    override fun onLoading() {

    }



    override fun onNumberSuccess(oauthBean: OauthBean?) {

    }


    override fun onThirdlyLoginSuccess(loginBean: LoginBean?) {
        loginState(loginBean)
    }

    override fun onThirdlyLoginError() {

    }

    override fun onWxLoginSuccess(loginBean: LoginBean?) {
        loginState(loginBean)
    }

    override fun onWxRegisterSuccess(thirdlyRegisterBean: ThirdlyRegisterBean?) {

    }

    override fun onWxAccreditSuccess(weiXinBean: WeiXinBean?) {

    }

    override fun onCheckWxError() {

    }

    override fun onError() {

    }

    override fun onCheckThirdlyRegisterSuccess(bean: RegisterBean?) {

    }

    override fun onCheckError() {

    }

    override fun onThirdlyRegisterSuccess(bean: ThirdlyRegisterBean?) {

    }

    override fun onWxAccreditError() {

    }

    override fun onCheckWxRegisterSuccess(bean: RegisterBean?) {

    }

    override fun onWxRegisterError() {

    }

    override fun onLoginError() {

    }
}