package com.example.td_horoscope.base

import com.example.module_ad.advertisement.TTAdManagerHolder
import com.example.module_base.base.BaseApplication

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.base
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/10 9:42
 * @class describe
 */
class MainBaseApplication:BaseApplication() {

    override fun initChild() {
        TTAdManagerHolder.init(this)

    }
}