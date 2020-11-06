package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_home_context_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 11:40
 * @class describe
 */
class HomeContextAdapter: BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_home_context_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        holder.itemView.mContextIcon.setImageResource(item.icon)
        holder.itemView.mContextTitle.text=item.title
    }
}