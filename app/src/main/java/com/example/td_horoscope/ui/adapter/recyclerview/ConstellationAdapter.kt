package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_constellation_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 11:39
 * @class describe
 */
class ConstellationAdapter :
    BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_constellation_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        holder.itemView.mCosIcon.setImageResource(item.icon)
        holder.itemView.mCosTitle.text = item.title
        holder.itemView.mCosDate.text = item.hint
        if (mPosition == holder.adapterPosition)  holder.itemView.mCosInclude.setBackgroundResource(R.drawable.shape_cos_select_bg)
        else
            holder.itemView.mCosInclude.setBackgroundResource(R.drawable.shape_cos_normal_bg)

    }

    private var mPosition:Int=0
    fun setCurrentPosition(position:Int){
        mPosition=position
        notifyDataSetChanged()
    }

}