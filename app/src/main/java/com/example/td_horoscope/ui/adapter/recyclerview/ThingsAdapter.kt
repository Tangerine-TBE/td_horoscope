package com.example.td_horoscope.ui.adapter.recyclerview

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.historyevent.Result
import kotlinx.android.synthetic.main.item_things_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/29 14:55
 * @class describe
 */
class ThingsAdapter:BaseQuickAdapter<Result,BaseViewHolder>(R.layout.item_things_container) {

    override fun convert(holder: BaseViewHolder, item: Result) {
        item?.let {
             Glide.with(context).load(it.pic)
                 .error(R.mipmap.icon_thing_error)
                 .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                 .into(holder.itemView.mThingIcon)

             holder.itemView.mThingTitle.text=it.des
             holder.itemView.mThingDate.text="${it.year}年"

         }

    }
}