package com.example.td_horoscope.view

import com.example.module_base.base.IBaseCallback
import com.example.td_horoscope.bean.consplate.ConsPlateBean

/**
 * @author: 铭少
 * @date: 2020/10/28 0028
 * @description：
 */
interface IPlateCallback:IBaseCallback {

    fun onLoadPlateSuccess(consPlate:ConsPlateBean)

}