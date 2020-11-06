package com.example.td_horoscope.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.base.BaseActivity
import com.example.module_base.provider.ModuleProvider
import com.example.module_usercenter.utils.SpUtil
import com.example.td_horoscope.R
import com.example.td_horoscope.ui.fragment.ConstellationFragment
import com.example.td_horoscope.ui.fragment.HuangLiFragment
import com.example.td_horoscope.ui.fragment.MyFragment
import com.example.td_horoscope.ui.fragment.ThingFragment
import com.example.td_horoscope.util.top.toOtherActivity
import com.jpeng.jptabbar.OnTabSelectListener
import com.jpeng.jptabbar.anno.NorIcons
import com.jpeng.jptabbar.anno.SeleIcons
import com.jpeng.jptabbar.anno.Titles
import com.tamsiree.rxkit.view.RxToast
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = ModuleProvider.ROUTE_MAIN_ACTIVITY)
class MainActivity : BaseActivity() {

    private var mLastFragment: Fragment? = null
    private val mConstellationFragment by lazy {
        ConstellationFragment()
    }
    private val mHuangLiFragment by lazy {
        HuangLiFragment()
    }
    private val mThingFragment by lazy {
        ThingFragment()
    }
    private val mMyFragment by lazy {
        MyFragment()
    }

    @Titles
    private val mTitles = arrayOf("星座物语", "老黄历", "今日往事", "我的")

    @SeleIcons
    private val mSelectIcons = intArrayOf(
            R.mipmap.icon_home_xz_select,
            R.mipmap.icon_home_hl_select,
            R.mipmap.icon_home_ws_select,
            R.mipmap.icon_home_my_select
    )

    @NorIcons
    private val mNormalIcons = intArrayOf(
            R.mipmap.icon_home_xz_normal,
            R.mipmap.icon_home_hl_normal,
            R.mipmap.icon_home_ws_normal,
            R.mipmap.icon_home_my_normal
    )


    override fun getLayoutView(): Int = R.layout.activity_main


    override fun initView() {
        if (SpUtil.loginTimeOut()) {
            RxToast.normal("本地登录已过期,请重新登录")
        }
        switchFragment(mConstellationFragment)
    }

    override fun initEvent() {
        mJPTabBar.setTabListener(object : OnTabSelectListener {
            override fun onTabSelect(index: Int) {
                        when (index) {
                            0 -> {
                                switchFragment(mConstellationFragment)
                                mHomeTitle.text="星座物语"
                                invisible(mHomeSet)
                            }
                            1 ->{
                                switchFragment(   mHuangLiFragment)
                                mHomeTitle.text="老黄历"
                                invisible(mHomeSet)
                            }

                            2 ->  {
                                switchFragment( mThingFragment)
                                mHomeTitle.text = "今日往事"
                                invisible(mHomeSet)
                            }
                            3 ->
                            {
                                switchFragment( mMyFragment)
                                mHomeTitle.text="我的"
                                visible(mHomeSet)

                            }
                            }
            }
            override fun onInterruptSelect(index: Int): Boolean = false
        })


        mHomeSet.setOnClickListener {
            toOtherActivity<SettingActivity>(this){}
        }
    }


    private fun switchFragment(baseFragment: Fragment) {
        if (mLastFragment === baseFragment) {
            return
        }
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (!baseFragment.isAdded) {
            transaction.add(R.id.main_container, baseFragment)
        } else {
            transaction.show(baseFragment)
        }
        if (mLastFragment != null) {
            transaction.hide(mLastFragment!!)
        }
        mLastFragment = baseFragment
        transaction.commitAllowingStateLoss()
    }


    override fun onResume() {
        super.onResume()
        when (intent.getIntExtra(ModuleProvider.FRAGMENT_ID, 5)) {
            0->mJPTabBar.setSelectTab(0)
            3->mJPTabBar.setSelectTab(3)
        }
    }
}