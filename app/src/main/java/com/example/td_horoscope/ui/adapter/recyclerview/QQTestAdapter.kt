package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import kotlinx.android.synthetic.main.item_qq_test_container.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/30 16:34
 * @class describe
 */
class QQTestAdapter : BaseQuickAdapter<IconTitleBean, BaseViewHolder>(R.layout.item_qq_test_container) {
    override fun convert(holder: BaseViewHolder, item: IconTitleBean) {
        holder.itemView.mQQTestHint.text= item.hint
        holder.itemView.mQQTestTitle.text = item.title
    }
}