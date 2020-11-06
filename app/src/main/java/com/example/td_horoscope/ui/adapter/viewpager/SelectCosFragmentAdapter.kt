package com.example.td_horoscope.ui.adapter.viewpager

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.td_horoscope.ui.fragment.CosContentFragment
import com.example.td_horoscope.util.MyContentProvider

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 13:36
 * @class describe
 */
class SelectCosFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mCurrentPosition=0;
    override fun getItem(position: Int): Fragment =
            CosContentFragment.getInstance(MyContentProvider.homeHoroscope[position].title,mCurrentPosition)

    override fun getCount(): Int
    =MyContentProvider.homeHoroscope.size

    var mCurrentFragment: Fragment? = null

    override fun setPrimaryItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        mCurrentFragment = `object` as Fragment
        super.setPrimaryItem(container, position, `object`)
    }

    fun getCurrentFragment(): Fragment? {
        return mCurrentFragment
    }


    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }


    fun setPosition(position:Int) {
        mCurrentPosition=position
        notifyDataSetChanged()
    }

}