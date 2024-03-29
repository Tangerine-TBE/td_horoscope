package com.example.td_horoscope.base
import com.example.module_ad.advertisement.TTAdManagerHolder
import com.example.module_base.base.BaseApplication
import com.example.module_base.util.PackageUtil
import com.example.td_horoscope.BuildConfig
import com.umeng.commonsdk.UMConfigure

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
            UMConfigure.init(application, UMConfigure.DEVICE_TYPE_PHONE, null)
            UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        }
    }

    override fun initChild() {
//        TTAdManagerHolder.init(this)
        UMConfigure.preInit(application, "5fb66104509a646ab9389456",PackageUtil.getAppMetaData(
            application,"UMENG_CHANNEL"))
    }
}