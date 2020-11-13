package com.example.td_horoscope.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.module_base.base.BaseFragment
import com.example.module_usercenter.ui.activity.BuyVipActivity
import com.example.module_usercenter.utils.Contents
import com.example.module_usercenter.utils.SpUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.ui.activity.AlloyPlateActivity
import com.example.td_horoscope.ui.activity.QQTestActivity
import com.example.td_horoscope.ui.activity.ZgDreamActivity
import com.example.td_horoscope.ui.activity.ZodiacActivity
import com.example.td_horoscope.ui.adapter.recyclerview.ConstellationAdapter
import com.example.td_horoscope.ui.adapter.recyclerview.HomeContextAdapter
import com.example.td_horoscope.ui.adapter.viewpager.SelectCosFragmentAdapter
import com.example.td_horoscope.util.MyContentProvider
import com.example.td_horoscope.util.top.toOtherActivity
import kotlinx.android.synthetic.main.fragment_constellation.*


/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.fragment
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/27 20:18
 * @class describe
 */
class ConstellationFragment:BaseFragment() {
    private lateinit var mConstellationAdapter: ConstellationAdapter

    override fun getLayoutView(): Int =R.layout.fragment_constellation

    private lateinit var mSelectCosFragmentAdapter: SelectCosFragmentAdapter
    private lateinit var mHomeContextAdapter: HomeContextAdapter



    override fun initView() {
        switchUIByState(PageState.SUCCESS)
        mConstellationContent.layoutManager=LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        mConstellationAdapter=
            ConstellationAdapter()
        mConstellationAdapter.data=MyContentProvider.homeHoroscope
        mConstellationContent.adapter=mConstellationAdapter
        mConstellationAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)

        mHomeContext.layoutManager=GridLayoutManager(activity,4)
        mHomeContextAdapter =
            HomeContextAdapter()
        mHomeContextAdapter.data=MyContentProvider.homeContext
        mHomeContext.adapter=mHomeContextAdapter
        mHomeContextAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)

        mSelectCosFragmentAdapter=
            SelectCosFragmentAdapter(
                childFragmentManager
            )
        mViewPager.adapter=mSelectCosFragmentAdapter

    }



    override fun initEvent() {
        mConstellationAdapter.setOnItemClickListener { adapter, view, position ->
                mViewPager.setCurrentItem(position,true)
            mConstellationAdapter.setCurrentPosition(position)

        }

        mHomeContextAdapter.setOnItemClickListener { adapter, view, position ->
            if (SpUtil.isVIP()) {
                when (position) {
                    0 -> toOtherActivity<ZodiacActivity>(activity, false) {}
                    1 -> toOtherActivity<AlloyPlateActivity>(activity, false) {}
                    2 -> toOtherActivity<QQTestActivity>(activity, false) {}
                    3 -> toOtherActivity<ZgDreamActivity>(activity, false) {}
                }
            } else {
                toOtherActivity<BuyVipActivity>(activity, false) {putExtra(Contents.TO_BUY,true)}
            }
        }



        mViewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mConstellationContent.smoothScrollToPosition(position)
                mConstellationAdapter.setCurrentPosition(position)
                mSelectCosFragmentAdapter.setPosition(position)
            }
        })
    }
}