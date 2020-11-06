package com.example.td_horoscope.view

import com.example.module_base.base.IBaseCallback
import com.example.td_horoscope.bean.zodiac.ZodiacBean

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
interface IZodiacCallback:IBaseCallback {

    fun onLoadZodiacSuccess(zodiac:ZodiacBean)

}