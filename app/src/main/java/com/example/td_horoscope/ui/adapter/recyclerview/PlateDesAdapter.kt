package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_plate_des_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 16:52
 * @class describe
 */
class PlateDesAdapter: BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_plate_des_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        item?.let {
            holder.itemView.mPlateDesHint.text=item.hint
            holder.itemView.mPlateDesTitle.text=item.title
            }
        }

}