package com.example.td_horoscope.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.bean.zodiac.ZodiacBean
import kotlinx.android.synthetic.main.diy_zodiac_popup_window.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.widget
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/2 14:57
 * @class describe
 */
class ZodiacPopup(activity: Context):PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT) {

    private  var mView: View = LayoutInflater.from(activity).inflate(R.layout.diy_zodiac_popup_window, null)

    init {
        contentView = mView
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isFocusable = true
        isOutsideTouchable = false
        animationStyle = R.style.ExitPopup
        initEvent()

    }

    private fun initEvent() {
        mView?.mZodResultBtn.setOnClickListener {
            dismiss()
        }
    }


    fun setPopupResult(resultInfo:ZodiacBean,topInfo:MutableList<IconTitleBean>) {
        mView?.apply {
            topInfo?.let {
                mZodMan.setImageResource(it[0].icon)
                mZodManTitle.text="生肖${it[0].title}"
                mZodWomen.setImageResource(it[1].icon)
                mZodWomenTitle.text="生肖${it[1].title}"
            }

            resultInfo?.let {
                mZodiacResult.text=it.result?.data
            }
        }

    }
}