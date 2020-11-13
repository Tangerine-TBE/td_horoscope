package com.example.td_horoscope.ui.activity

import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.module_base.util.SizeUtils
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.bean.PlateIndexCacheBean
import com.example.td_horoscope.ui.adapter.recyclerview.DocumentPlateAdapter
import com.example.td_horoscope.util.Contents
import com.example.td_horoscope.util.top.toOtherActivity
import com.tamsiree.rxkit.view.RxToast
import com.tamsiree.rxui.view.dialog.RxDialogSureCancel
import com.yanzhenjie.recyclerview.*
import kotlinx.android.synthetic.main.activity_plate_document.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.litepal.LitePal

class PlateDocumentActivity: MainBaseActivity() , SwipeMenuCreator, OnItemMenuClickListener {

    override fun getLayoutView(): Int =R.layout.activity_plate_document

    private lateinit var mDocumentPlateAdapter:DocumentPlateAdapter

    private var mCacheList:MutableList<PlateIndexCacheBean>?=ArrayList()

    private val mRxDialogSureCancel by lazy {
        RxDialogSureCancel(this).apply {
            setContent("你确定要删除全部记录吗？")
        }
    }

    override fun initView() {
        mDocumentPlateAdapter = DocumentPlateAdapter()
        //查询数据库的记录
        GlobalScope.launch (Dispatchers.Main){
            mCacheList = withContext(Dispatchers.IO) {
                LitePal.findAll(PlateIndexCacheBean::class.java)
            }
            mDocumentPlateAdapter.setList(mCacheList)
        }


        mDocumentContainer.setSwipeMenuCreator(this)
        mDocumentContainer.setOnItemMenuClickListener(this)
        mDocumentContainer.layoutManager=LinearLayoutManager(this)
        mDocumentContainer.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = SizeUtils.dip2px(this@PlateDocumentActivity, 7.5f)
            }
        })
        mDocumentContainer.adapter=mDocumentPlateAdapter
    }



    override fun onCreateMenu(leftMenu: SwipeMenu?, rightMenu: SwipeMenu?, position: Int) {
        val width = resources.getDimensionPixelSize(R.dimen.dp_70)
        val height = ViewGroup.LayoutParams.MATCH_PARENT
            val deleteItem: SwipeMenuItem =
                SwipeMenuItem(this@PlateDocumentActivity).setBackground(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_action_delete)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setBackground(R.drawable.shape_document_delete_bg)
                    .setHeight(height)
            rightMenu!!.addMenuItem(deleteItem) // 添加菜单到右侧。
    }

    override fun onItemClick(menuBridge: SwipeMenuBridge?, adapterPosition: Int) {
        val direction = menuBridge!!.direction // 左侧还是右侧菜单。
        if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
            //删除选中的数据
            GlobalScope.launch(Dispatchers.Main) {
         val deleteState = withContext(Dispatchers.IO) {
             val findAll = LitePal.findAll(PlateIndexCacheBean::class.java)
             if (adapterPosition < findAll.size)
                 LitePal.deleteAll(PlateIndexCacheBean::class.java, "plateHint=? and plateResult=?", findAll[adapterPosition].plateHint, findAll[adapterPosition].plateResult)
              else 0
             }
             if (deleteState==1) {
                    val allData =  withContext(Dispatchers.IO){
                        LitePal.findAll(PlateIndexCacheBean::class.java)
                    }
                    mDocumentPlateAdapter.setList(allData)
                }

            }

        }
    }


    override fun initEvent() {
        mDocumentBar.setOnBackClickListener(object:MyToolbar.OnBackClickListener{
            override fun onBack() {
                finish()
            }
            override fun onRightTo() {
                    if (mCacheList?.size?:0 > 0) mRxDialogSureCancel.show() else RxToast.warning("暂无合盘记录")
            }
        })

        mRxDialogSureCancel.setCancelListener(View.OnClickListener {
            mRxDialogSureCancel.dismiss()
        })

        //删除全部记录
        mRxDialogSureCancel.setSureListener(View.OnClickListener {
            GlobalScope.launch (Dispatchers.Main){
                val cacheData = withContext(Dispatchers.IO) {
                    LitePal.deleteAll(PlateIndexCacheBean::class.java)
                    LitePal.findAll(PlateIndexCacheBean::class.java)
                }
                mDocumentPlateAdapter.setList(cacheData)
                mRxDialogSureCancel.dismiss()
            }
        })

        //进入选中的页面
        mDocumentPlateAdapter.setOnItemClickListener { adapter, view, position ->
            GlobalScope.launch (Dispatchers.Main){
                val dataList = withContext(Dispatchers.IO) {
                    LitePal.findAll(PlateIndexCacheBean::class.java)
                }
                toOtherActivity<PlateIndexActivity>(this@PlateDocumentActivity,false){
                    putExtra(Contents.PLATE_HINT, dataList[position].plateHint)
                    putExtra(Contents.PLATE_RESULT, dataList[position].plateResult)
                }
            }
        }



    }

    override fun release() {
        mRxDialogSureCancel.dismiss()
        mCacheList=null
    }


}