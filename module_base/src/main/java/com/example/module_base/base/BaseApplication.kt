package com.example.module_base.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.alibaba.sdk.android.feedback.impl.FeedbackAPI
import com.example.module_base.provider.ModuleProvider
import com.example.module_base.util.PackageUtil
import com.example.module_base.util.SPUtil
import com.tamsiree.rxkit.RxTool
import org.json.JSONObject
import org.litepal.LitePal

/**
 * @name td_horoscope
 * @class name：com.example.module_base.base
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/27 19:34
 * @class describe
 */
 class BaseApplication:Application() {

    companion object {
        var appContext: Context? = null
        fun getContext(): Context {
            return appContext!!
        }

        var mMainHandler:Handler?=null
        fun getMainHandler(): Handler {
            return mMainHandler!!
        }


    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
        mMainHandler= Handler()
        SPUtil.init(this)
        RxTool.init(this)
        LitePal.initialize(this)
        LitePal.getDatabase()
       // ARouter.openDebug();
        ARouter.init(this)
        //用户反馈
        FeedbackAPI.init(this, "25822454", "7a8bb94331a5141dcea61ecb1056bbbd")
        val jsonObject = JSONObject()
        try {
            jsonObject.put("appId", PackageUtil.getAppProcessName(this))
            jsonObject.put("appName",PackageUtil.getAppMetaData(this,ModuleProvider.APP_NAME))
            jsonObject.put("ver", PackageUtil.packageCode2(applicationContext))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        FeedbackAPI.setAppExtInfo(jsonObject)

    }
}