package com.example.module_base.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.module_base.R
import kotlinx.android.synthetic.main.layout_toolbar_new.view.*

/**
 * @name WeatherOne
 * @class name：com.nanjing.tqlhl.ui.custom.mj15day
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/20 11:43
 * @class describe
 */
class MyToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar_new, this, true)
        initAttrs(attrs)
        initView()
        initEvent()
    }



    private  var mTitle:String?=null
    private  var mRightTitle:String?=null
    private var mTitleColor:Int=Color.BLACK
    private var mBarBgColor:Int=Color.WHITE
    private var mIcon:Int?=null
    private var isHaveAdd:Boolean?=null
    private var isHaveBack:Boolean?=null
    private var isHaveRight:Boolean?=null
    private var mRightTitleColor:Int=Color.BLACK



    private fun initAttrs(attrs: AttributeSet? = null) {
        val obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MyToolbar)
        mTitle = obtainStyledAttributes.getString(R.styleable.MyToolbar_title)
        mRightTitle = obtainStyledAttributes.getString(R.styleable.MyToolbar_rightTitle)
        mTitleColor = obtainStyledAttributes.getColor(R.styleable.MyToolbar_titleColor,Color.BLACK)
        mBarBgColor = obtainStyledAttributes.getColor(R.styleable.MyToolbar_barBgColor,Color.WHITE)
        mRightTitleColor = obtainStyledAttributes.getColor(R.styleable.MyToolbar_rightTitleColor,Color.WHITE)
        mIcon = obtainStyledAttributes.getResourceId(R.styleable.MyToolbar_iconStyle,-1)
        isHaveAdd = obtainStyledAttributes.getBoolean(R.styleable.MyToolbar_has_add, false)
        isHaveRight = obtainStyledAttributes.getBoolean(R.styleable.MyToolbar_hasRightTitle, false)
        isHaveBack = obtainStyledAttributes.getBoolean(R.styleable.MyToolbar_has_add, true)
        obtainStyledAttributes.recycle()

    }

    private fun initView() {
        mTitle?.let {
            tv_bar_title?.text=it
        }

        tv_bar_title.setTextColor(mTitleColor)

        rl_bar.setBackgroundColor(mBarBgColor)

        mIcon?.let {
            if (it!=-1) {
                iv_bar_back.setImageResource(it)
            }
        }


        if (isHaveAdd!!) {
            iv_bar_add.visibility = View.VISIBLE
        } else {
            iv_bar_add.visibility = View.GONE
        }

        if (isHaveBack!!) {
            iv_bar_back.visibility = View.VISIBLE
        } else {
            iv_bar_back.visibility = View.GONE
        }

        mRightTitle?.let {
            tv_bar_right?.text=it
        }

        tv_bar_right.setTextColor(mRightTitleColor)

        if (isHaveRight!!) {
            tv_bar_right.visibility = View.VISIBLE
        } else {
            tv_bar_right.visibility = View.GONE
        }


    }
    private fun initEvent() {
        iv_bar_back.setOnClickListener {
            mOnBackClickListener?.onBack()
        }

            tv_bar_right.setOnClickListener {
                mOnBackClickListener?.onRightTo()
            }
        
    }

    private var mOnBackClickListener: OnBackClickListener?=null
    fun setOnBackClickListener(listener: OnBackClickListener?){
        this.mOnBackClickListener=listener
    }

    fun setTitle(title:String) {
        mTitle=title
        initView()
    }


    interface OnBackClickListener{
        fun onBack()

        fun onRightTo()
    }
}


