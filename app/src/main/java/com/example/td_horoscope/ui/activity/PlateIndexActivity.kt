package com.example.td_horoscope.ui.activity

import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_base.base.BaseActivity
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean
import com.example.td_horoscope.bean.PlateIndexCacheBean
import com.example.td_horoscope.bean.consplate.ConsPlateBean
import com.example.td_horoscope.ui.adapter.recyclerview.PlateDesAdapter
import com.example.td_horoscope.ui.adapter.recyclerview.PlateIndexAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.LogUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_plate_index.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.litepal.LitePal.where


class PlateIndexActivity : BaseActivity() {

    private lateinit var mPlateIndexAdapter:PlateIndexAdapter
    private lateinit var mPlateDesAdapter:PlateDesAdapter


    override fun getLayoutView(): Int =R.layout.activity_plate_index
    override fun initView() {

        mPlateIndexContainer.layoutManager=GridLayoutManager(this,3)
        mPlateIndexAdapter=PlateIndexAdapter()
        mPlateIndexContainer.adapter=mPlateIndexAdapter

        mPlateDesContainer.layoutManager=LinearLayoutManager(this)
        mPlateDesAdapter=PlateDesAdapter()
        mPlateDesContainer.adapter=mPlateDesAdapter


        val plateHintStr = intent.getStringExtra(Contents.PLATE_HINT)
        val plateResultStr = intent.getStringExtra(Contents.PLATE_RESULT)
        LogUtil.i(this,"------------------$plateHintStr------------------------")
        LogUtil.i(this,"------------------$plateResultStr------------------------")
        if (!TextUtils.isEmpty(plateHintStr) and !TextUtils.isEmpty(plateResultStr)) {
            val plateHintData = Gson().fromJson<Array<IconTitleBean>>(plateHintStr, Array<IconTitleBean>::class.java)
            val  plateResultData = Gson().fromJson(plateResultStr, ConsPlateBean::class.java)
            showPlateIndex(plateHintData,plateResultData)
            GlobalScope.launch (Dispatchers.Main){
                val saveSuccess = withContext(Dispatchers.IO) {
                    val dateList: List<PlateIndexCacheBean> = where("plateHint=? and plateResult=?", plateHintStr,plateResultStr).find(PlateIndexCacheBean::class.java)
                    if (dateList.size == 0) {
                        PlateIndexCacheBean(plateHintStr!!, plateResultStr!!).save()
                    } else {
                        false
                    }
                }
            }
        }

    }


    private fun showPlateIndex(plateHintData: Array<IconTitleBean>?, plateResultData: ConsPlateBean?) {
        plateHintData?.let {
            //左
            mPlateName1.text=it[0].hint
            mPlateIcon1.setImageResource(it[0].icon)
            mPlateCons1.text=it[0].title
            //右
            mPlateName2.text=it[1].hint
            mPlateIcon2.setImageResource(it[1].icon)
            mPlateCons2.text=it[1].title
        }
        plateResultData?.result?.let {
            mPlateIndexAdapter.setList(arrayListOf(
                IconTitleBean(hint = "配对比重",title = it.bizhong),
                IconTitleBean(hint = "两情相悦指数",title = it.tcdj),
                IconTitleBean(hint = "天长地久指数",title = it.xiangyue)
            ))

            mPlateDesAdapter.setList(arrayListOf(
                IconTitleBean(hint = "恋爱建议",title = it.lianai),
                IconTitleBean(hint = "注意事项",title = it.zhuyi)
            ))
            mPlateIndex.text=it.zhishu
            mPlateJieGuo.text=it.jieguo
        }

    }


    override fun initEvent() {
        mPlateIndexBar.setOnBackClickListener(object :MyToolbar.OnBackClickListener{
            override fun onBack() {
                finish()
            }
            override fun onRightTo() {

            }

        })
    }
}