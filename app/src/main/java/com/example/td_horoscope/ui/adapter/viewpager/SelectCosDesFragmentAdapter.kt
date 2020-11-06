package com.example.td_horoscope.ui.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.td_horoscope.ui.fragment.ConDesFragmentOne
import com.example.td_horoscope.ui.fragment.ConDesFragmentTwo
import com.example.td_horoscope.util.MyContentProvider

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.ui.adapter
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 13:36
 * @class describe
 */
class SelectCosDesFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return if (position in 0..1)
            ConDesFragmentOne.getInstance(MyContentProvider.dateContent[position].title,mCurrentCons)
         else
            ConDesFragmentTwo.getInstance(MyContentProvider.dateContent[position].title,mCurrentCons)

    }


    override fun getCount(): Int
    =MyContentProvider.dateContent.size


    override fun getPageTitle(position: Int): CharSequence? {
        return MyContentProvider.dateContent[position].hint
    }

    private var mCurrentCons="白羊座"

    fun setCurrentCons(cons:String) {
        mCurrentCons=cons
        notifyDataSetChanged()
    }
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }


}