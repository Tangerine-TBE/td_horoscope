package com.example.td_horoscope.ui.adapter.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.bean.PlateIndexCacheBean
import com.example.td_horoscope.bean.consplate.ConsPlateBean
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_document_contianer.view.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter.recyclerview
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/3 17:57
 * @class describe
 */
class DocumentPlateAdapter:BaseQuickAdapter<PlateIndexCacheBean,BaseViewHolder>(R.layout.item_document_contianer) {

    override fun convert(holder: BaseViewHolder, item: PlateIndexCacheBean) {
        holder.itemView.apply {

        item?.let { it ->
            val plateHintData = Gson().fromJson<Array<IconTitleBean>>(it.plateHint, Array<IconTitleBean>::class.java)
            val plateResultData = Gson().fromJson(it.plateResult, ConsPlateBean::class.java)

            plateHintData?.let {
                mPlateName1.text =plateHintData[0].hint
                mPlateIcon1.setImageResource(plateHintData[0].icon)
                mPlateCons1.text =plateHintData[0].title

                mPlateName2.text =plateHintData[1].hint
                mPlateIcon2.setImageResource(plateHintData[1].icon)
                mPlateCons2.text =plateHintData[1].title
            }

            plateResultData?.let {
                mItemPlateIndex.text=it.result.zhishu
            }

        }
        }
    }
}