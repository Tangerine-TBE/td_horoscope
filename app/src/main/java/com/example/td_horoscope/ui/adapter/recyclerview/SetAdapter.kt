package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_set_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 16:34
 * @class describe
 */
class SetAdapter : BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_set_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        holder.itemView.mSetHint.text= item.hint

    }
}