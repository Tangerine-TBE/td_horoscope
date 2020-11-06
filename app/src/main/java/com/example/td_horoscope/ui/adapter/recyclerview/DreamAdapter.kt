package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_dream_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 18:02
 * @class describe
 */
class DreamAdapter: BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_dream_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        item?.let {
            holder.itemView.mDreamHint.text=item.hint
            holder.itemView.mDreamTitle.text=item.title
        }
    }

}