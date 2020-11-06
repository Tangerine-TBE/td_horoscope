package com.example.td_horoscope.ui.fragment

import android.os.Bundle
import com.example.module_base.base.BaseFragment
import com.example.td_horoscope.R
import com.example.td_horoscope.ui.adapter.viewpager.SelectCosDesFragmentAdapter
import com.example.td_horoscope.util.Contents
import kotlinx.android.synthetic.main.fragment_cos_content.*

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 13:34
 * @class describe
 */
class CosContentFragment:BaseFragment() {
    override fun getLayoutView(): Int= R.layout.fragment_cos_content

    companion object{
      fun  getInstance(cosType:String,position:Int):CosContentFragment{
          val cosContentFragment = CosContentFragment()
          val bundle = Bundle()
          bundle.putString(Contents.CURRENT_CONSTELLATION,cosType)
          bundle.putInt(Contents.CURRENT_POSITION,position)
          cosContentFragment.arguments=bundle
          return cosContentFragment
      }
    }




    override fun initView() {
        switchUIByState(PageState.SUCCESS)
        mTabLayout.setupWithViewPager(mChildPager)
        mTabLayout.isFocusableInTouchMode = false;
        val selectCosDesFragmentAdapter =
            SelectCosDesFragmentAdapter(
                childFragmentManager
            )
        mChildPager.adapter=selectCosDesFragmentAdapter
        val string = arguments?.getString(Contents.CURRENT_CONSTELLATION)
        selectCosDesFragmentAdapter.setCurrentCons(string!!)


    }



    override fun initPresent() {

    }

    override fun initLoadData() {

    }

}