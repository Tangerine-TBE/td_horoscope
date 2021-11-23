package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.module_base.util.ToastUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_zodiac_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/2 10:40
 * @class describe
 */
class ZodiacAdapter :
    BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_zodiac_container) {

    private val mSelectList: MutableList<IconTitleBean>? = ArrayList()
    private var mPosition = -1
    fun setPosition(position: Int) {
        mPosition = position
    }

    fun getSelectList():MutableList<IconTitleBean>{
        return mSelectList!!
    }

    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        holder.itemView?.apply {
            item?.let {
                mZodIcon.setImageResource(it.icon)
                mZodTitle.text = it.title
                mSelectList?.apply {
                    if (contains(it)) mZodiacInclude.setBackgroundResource(R.drawable.shape_zodiac_selecet_bg) else mZodiacInclude.setBackgroundResource(R.color.transparent)

                    if (holder.adapterPosition == mPosition) {
                        if (contains(it)) {
                            remove(it)
                            mZodiacInclude.setBackgroundResource(R.color.transparent)
                        } else {
                            if (size < 2) {
                                add(it)
                                mZodiacInclude.setBackgroundResource(R.drawable.shape_zodiac_selecet_bg)
                            } else {
                                ToastUtil.showToast("最多只能选择两个生肖")
                            }

                        }
                    }
                }
            }
        }
    }
}