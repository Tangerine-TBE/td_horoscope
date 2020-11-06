package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.util.top.getStart
import kotlinx.android.synthetic.main.item_cons_index_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 16:11
 * @class describe
 */
class ConsIndexAdapter:BaseQuickAdapter<IconTitleBean,BaseViewHolder>(R.layout.item_cons_index_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        item?.let {
            holder.itemView.mIndex_hint.text=it.hint
            val start = it.title.toInt()?.getStart()
            holder.itemView.mRatingBar.rating=start

        }
    }

}