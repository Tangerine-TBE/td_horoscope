package com.example.td_horoscope.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.sdk.android.feedback.impl.FeedbackAPI
import com.example.module_base.base.BaseFragment
import com.example.module_usercenter.bean.*
import com.example.module_usercenter.present.impl.LoginPresentImpl
import com.example.module_usercenter.present.impl.LogoutPresentImpl
import com.example.module_usercenter.present.impl.ThirdlyLoginPresentImpl
import com.example.module_usercenter.present.impl.WeChatPresentImpl
import com.example.module_usercenter.ui.activity.LoginActivity
import com.example.module_usercenter.utils.Contents
import com.example.module_usercenter.utils.SpUtil
import com.example.module_usercenter.view.ILoginCallback
import com.example.module_usercenter.view.ILogoutCallback
import com.example.module_usercenter.view.IThirdlyLoginCallback
import com.example.module_usercenter.view.IWeChatCallback
import com.example.td_horoscope.R
import com.example.td_horoscope.ui.activity.AboutActivity
import com.example.td_horoscope.ui.adapter.recyclerview.SettingAdapter
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.util.top.toOtherActivity
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.fragment_my.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 9:41
 * @class describe
 */
class MyFragment : BaseFragment(), ILoginCallback, IWeChatCallback, IThirdlyLoginCallback,
    ILogoutCallback {


    override fun getLayoutView(): Int = R.layout.fragment_my
    private lateinit var mSettingAdapter: SettingAdapter
    private var mLoginPresent:LoginPresentImpl?=null
    private var mLogoutPresent: LogoutPresentImpl? = null
    private var mThirdlyLoginPresent: ThirdlyLoginPresentImpl? = null
    private var mWeChatPresent: WeChatPresentImpl? = null
    private var mIsLogin:Boolean?=false

    override fun initView() {
        switchUIByState(PageState.SUCCESS)
        mSetContainer.layoutManager = LinearLayoutManager(activity)
        mSettingAdapter =
            SettingAdapter()
        mSettingAdapter.data=MyContentProvider.settingList
        mSetContainer.adapter=mSettingAdapter


    }

    override fun initPresent() {
        mLoginPresent = LoginPresentImpl.getInstance()
        mLoginPresent?.registerCallback(this)

        mWeChatPresent = WeChatPresentImpl.getInstance()
        mWeChatPresent?.registerCallback(this)

        mThirdlyLoginPresent = ThirdlyLoginPresentImpl.getInstance()
        mThirdlyLoginPresent?.registerCallback(this)

        mLogoutPresent = LogoutPresentImpl.getInstance()
        mLogoutPresent?.registerCallback(this)
    }


    override fun initEvent() {
        mSettingAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                1->{
                    FeedbackAPI.openFeedbackActivity()
                }

                3->{
                   toOtherActivity<AboutActivity>(activity){}
                }
            }

        }

        mLoginInclude.setOnClickListener {
            if (!mSPUtil.getBoolean(Contents.USER_IS_LOGIN, false)) toOtherActivity<LoginActivity>(activity){}
        }

    }


    override fun onResume() {
        super.onResume()

        mIsLogin = mSPUtil.getBoolean(Contents.USER_IS_LOGIN, false)
        var userId = mSPUtil.getString(Contents.USER_ID, "");
        var userVip = mSPUtil.getInt(Contents.USER_VIP_LEVEL, 0);
        var userVipTime = mSPUtil.getString(Contents.USER_VIP_TIME, "");

        if (mIsLogin!!) {
            mLoginId.text=userId
            mLoginHint.text= if(userVip==0) "您还不是VIP"  else "VIP等级：$userVip  有效期：$userVipTime"
            mLoginPic.setImageResource(R.mipmap.ic_launcher)
        } else {
            logoutState()
        }
    }

    private fun logoutState() {
        mLoginId?.text="立即登录"
        mLoginHint?.text="登录数据不丢失，享受更多功能"
        mLoginPic?.setImageResource(R.mipmap.ic_launcher)
    }


    private fun loginState(loginBean: LoginBean?) {
        val data = loginBean?.data
        mLoginId?.text=data?.id.toString()
        mLoginHint?.text=   if (data?.vip==0) "您还不是VIP" else "VIP等级："+data?.vip+"  有效期："+data?.vipexpiretime
        mLoginPic?.setImageResource(R.mipmap.ic_launcher)
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

    override fun onLogoutSuccess(registerBean: RegisterBean?) {
        if (registerBean?.ret==200) {
            logoutState()
            SpUtil.deleteUserInfo()
            RxToast.success("注销成功！")
        }
    }

    override fun onLogoutError() {

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