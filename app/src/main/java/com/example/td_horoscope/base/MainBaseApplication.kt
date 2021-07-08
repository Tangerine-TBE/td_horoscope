package com.example.td_horoscope.base

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI
import com.example.module_ad.advertisement.TTAdManagerHolder
import com.example.module_base.base.BaseApplication
import com.example.module_base.provider.ModuleProvider
import com.example.module_base.util.PackageUtil
import com.umeng.commonsdk.UMConfigure
import org.json.JSONObject

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.base
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/10 9:42
 * @class describe
 */
class MainBaseApplication:BaseApplication() {

    companion object{
        fun initSdk(){
            TTAdManagerHolder.init(application)
            //友盟
            UMConfigure.init(application, UMConfigure.DEVICE_TYPE_PHONE, "5fb66104509a646ab9389456")
            UMConfigure.setLogEnabled(true)
            //用户反馈
            FeedbackAPI.init(application, "25822454", "7a8bb94331a5141dcea61ecb1056bbbd")
            val jsonObject = JSONObject()
            try {
                jsonObject.put("appId", PackageUtil.getAppProcessName(application))
                jsonObject.put("appName", PackageUtil.getAppMetaData(application, ModuleProvider.APP_NAME))
                jsonObject.put("ver", PackageUtil.packageCode2(application))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            FeedbackAPI.setAppExtInfo(jsonObject)
        }
    }

    override fun initChild() {
//        TTAdManagerHolder.init(this)
    }
}