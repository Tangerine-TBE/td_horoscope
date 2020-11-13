package com.example.td_horoscope.ui.widget

import android.animation.ValueAnimator
import android.app.Activity
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
class ZodiacPopup(activity: Activity):PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT) {

    private val mActivity=activity
    private  var mView: View = LayoutInflater.from(activity).inflate(R.layout.diy_zodiac_popup_window, null)

    init {
        contentView = mView
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isFocusable = true
        isOutsideTouchable = false
        animationStyle = R.style.ZodiacPopup
        intBgAnimation()
        initEvent()


    }

    private fun initEvent() {
        mView?.mZodResultBtn.setOnClickListener {
            dismiss()
        }

        setOnDismissListener {
            mOutValueAnimator?.start()
        }

    }


    fun setPopupResult(resultInfo:ZodiacBean,topInfo:MutableList<IconTitleBean>) {
        mView?.apply {
            mInValueAnimator?.start()
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


    private var mInValueAnimator: ValueAnimator? = null
    private var mOutValueAnimator: ValueAnimator? = null
    private fun intBgAnimation() {
        mInValueAnimator = ValueAnimator.ofFloat(1.0f, 0.5f).setDuration(300)
        mInValueAnimator?.addUpdateListener { animation -> updateBgWindowAlpha((animation.animatedValue as Float)) }
        mOutValueAnimator = ValueAnimator.ofFloat(0.5f, 1.0f).setDuration(300)
        mOutValueAnimator?.addUpdateListener{ animation -> updateBgWindowAlpha(animation.animatedValue as Float) }
    }



    //设置窗口渐变
    private fun updateBgWindowAlpha(alpha: Float) {
        val window = mActivity.window
        val attributes = window.attributes
        attributes.alpha = alpha
        window.attributes = attributes
    }



}