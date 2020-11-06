package com.example.td_horoscope.view

import com.example.module_base.base.IBaseCallback
import com.example.td_horoscope.bean.dream.DreamBean

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
interface IDreamCallback:IBaseCallback {

    fun onLoadDreamSuccess(dreamData:DreamBean)

}