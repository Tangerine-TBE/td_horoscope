package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_setting_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 10:21
 * @class describe
 */
class SettingAdapter:BaseQuickAdapter<IconTitleBean,BaseViewHolder>(R.layout.item_setting_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        holder.itemView.mSetItemIcon.setImageResource(item.icon)
        holder.itemView.mSetItemTitle.text=item.title
    }
}