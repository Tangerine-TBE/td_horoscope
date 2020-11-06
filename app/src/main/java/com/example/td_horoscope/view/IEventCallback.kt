package com.example.td_horoscope.view

import com.example.module_base.base.IBaseCallback
import com.example.td_horoscope.bean.historyevent.HistoryEventBean

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.view
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 14:21
 * @class describe
 */
interface IEventCallback:IBaseCallback {
    fun onLoadEventSuccess(historyEvent: HistoryEventBean)
}