package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import kotlinx.android.synthetic.main.item_thing_date_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 11:17
 * @class describe
 */
class ThingDateAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_thing_date_container) {
    override fun convert(holder: BaseViewHolder, item: String) {
      item?.let {
          holder.itemView.mThingDate.text=item
      }
    }
}