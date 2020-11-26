package com.example.td_horoscope.ui.fragment

import android.Manifest
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_ad.advertisement.SplashHelper
import com.example.module_ad.bean.AdBean
import com.example.module_ad.request.AdPresent
import com.example.module_ad.request.IAdCallback
import com.example.module_base.base.BaseFragment
import com.example.module_base.util.PackageUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.ui.activity.DealActivity
import com.example.td_horoscope.ui.activity.MainActivity
import com.example.td_horoscope.ui.adapter.recyclerview.PermissionAdapter
import com.example.td_horoscope.util.ColorUtil
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.LogUtil
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.util.top.toOtherActivity
import com.google.gson.Gson
import com.permissionx.guolindev.PermissionX
import com.tamsiree.rxkit.RxNetTool
import kotlinx.android.synthetic.main.fragment_permissions.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/10 11:48
 * @class describe
 */
class PermissionFragment:BaseFragment(), IAdCallback {
    override fun getLayoutView(): Int= R.layout.fragment_permissions
    private lateinit var mPermissionAdapter:PermissionAdapter
    private val madPresent by lazy {
        AdPresent.getInstance()
    }
    private lateinit var mSplashHelper: SplashHelper

    override fun initView() {
        mSPUtil.putBoolean(com.example.module_ad.utils.Contents.NO_BACK,true)

        switchUIByState(PageState.SUCCESS)
        mSplashHelper= SplashHelper(activity, permission_container, MainActivity::class.java)

        rv_permission.layoutManager=LinearLayoutManager(activity)
        mPermissionAdapter = PermissionAdapter()
        mPermissionAdapter.setList(MyContentProvider.permissionList)
        rv_permission.adapter=mPermissionAdapter


        permissions_title.text=PackageUtil.difPlatformName(activity, R.string.welcome)
        val str = resources.getString(R.string.user_agreement)
        val stringBuilder = SpannableStringBuilder(str)
        val span1 = TextViewSpan1()
        stringBuilder.setSpan(span1, 10, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val span2 = TextViewSpan2()
        stringBuilder.setSpan(span2, 19, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        user_agreement.text=stringBuilder
        user_agreement.movementMethod=LinkMovementMethod.getInstance()

    }


    override fun initPresent() {
        madPresent.registerCallback(this)
        if (RxNetTool.isNetworkAvailable(mActivity)) {
            madPresent.getAdMsg()
        }
    }

    override fun initEvent() {
        go_main.setOnClickListener {
            checkRuntimePermission()
        }

        bt_try.setOnClickListener {
            checkRuntimePermission()
        }

    }

    private val permissions = arrayListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun checkRuntimePermission() {
        PermissionX.init(activity)
            .permissions(permissions)
            .setDialogTintColor(
                Color.parseColor(ColorUtil.THEME_COLOR_STR),
                Color.parseColor(ColorUtil.THEME_COLOR_STR)
            )
            .onExplainRequestReason { scope, deniedList, beforeRequest ->
                val msg = "即将申请的权限是程序必须依赖的权限"
                scope.showRequestReasonDialog(deniedList, msg, "开启", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                val msg = "您需要去应用程序设置当中手动开启权限"
                scope.showForwardToSettingsDialog(deniedList, msg, "开启", "取消")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    if (RxNetTool.isNetworkAvailable(mActivity)) {
                        mSplashHelper.showAd()
                    } else {
                        toOtherActivity<MainActivity>(activity,true){}
                    }
                    mSPUtil.putBoolean(Contents.IS_FIRST, false)
                }
            }
    }



    inner class TextViewSpan1 : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = ColorUtil.THEME_COLOR.toInt()
        }

        override fun onClick(widget: View) {
            //点击事件
            toOtherActivity<DealActivity>(activity,false){
                putExtra(Contents.SET_Deal1,0)
            }
        }
    }

     inner  class TextViewSpan2 : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = ColorUtil.THEME_COLOR.toInt()
        }

        override fun onClick(widget: View) {
            //点击事件
            toOtherActivity<DealActivity>(activity,false){
                putExtra(Contents.SET_Deal1,1)
            }
        }
    }

    override fun onLoadAdSuccess(adBean: AdBean?) {
        adBean?.let {
            mSPUtil.putString(com.example.module_ad.utils.Contents.AD_INFO,Gson().toJson(it.data))
        }

    }

    override fun onLoadError() {
        LogUtil.i(this,"66666666666666666666666666");
    }


    override fun release() {
        madPresent.unregisterCallback(this)
    }
}