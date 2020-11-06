package com.example.td_horoscope.util

import android.util.Log

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.util
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 18:14
 * @class describe
 */
object LogUtil {

    private const val LEVEL: Int = 0

    private const val DEBUG = 0
    private const val INFO = 1
    private const val WARM = 2
    private const val ERROR = 3


    fun d(context: Any, msg: String?) {
        if (DEBUG >= LEVEL) {
            Log.d(context.javaClass.simpleName, msg!!)
        }
    }

    fun i(context: Any, msg: String?) {
        if (INFO >= LEVEL) {
            Log.i(context.javaClass.simpleName, msg!!)
        }
    }

    fun w(context: Any, msg: String?) {
        if (WARM >= LEVEL) {
            Log.w(context.javaClass.simpleName, msg!!)
        }
    }

    fun e(context: Any, msg: String?) {
        if (ERROR >= LEVEL) {
            Log.e(context.javaClass.simpleName, msg!!)
        }
    }



}