package kylec.me.mjkwanandroid

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import kylec.me.user.ui.interfaces.UserInfoFragment
import kotlinx.android.synthetic.main.activity_main.*
import kylec.me.base.AppManager
import kylec.me.base.RouterPath
import kylec.me.base.common.currentTimeMillis
import kylec.me.base.extend.snack
import kylec.me.base.ui.BaseActivity
import kylec.me.base.ui.BlankFragment
import kylec.me.wan.ui.homepage.HomePageFragment
import kotlin.properties.Delegates

private const val COUNT_VIEW_PAGER_ITEM = 4

/**
 * Created by KYLE on 2019/5/7 - 15:17
 */
@Route(path = RouterPath.TO_MAIN)
class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity() {

    private var isDrawerOpened = false

    private var backPressedTime by Delegates.observable(0L) { _, old, new ->
        if (new - old < 2000) {
            AppManager.instance.exit()
        } else {
            drawerLayout.snack("再按一次返回键退出")
        }
    }

    override fun initView() {
        initDrawerLayoutNavigation()
        initBottomNavigation()
        initViewPager()
    }

    private fun initDrawerLayoutNavigation() {
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerClosed(drawerView: View) {
                isDrawerOpened = false
            }

            override fun onDrawerOpened(drawerView: View) {
                isDrawerOpened = true
            }
        })

        UserInfoFragment.newInstance().commit(R.id.leftNavigationView)
    }

    private fun initBottomNavigation() {
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            viewPager.currentItem =
                when (item.itemId) {
                    R.id.tab_wechat -> 0
                    R.id.tab_android -> 1
                    R.id.tab_kotlin -> 2
                    else -> 3
                }
            true
        }
    }

    private fun initViewPager() {
        viewPager.apply {
            offscreenPageLimit = COUNT_VIEW_PAGER_ITEM - 1
            adapter = MainViewPagerAdapter()
        }
    }

    inner class MainViewPagerAdapter : FragmentStatePagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int) =
            when (position) {
                0 -> HomePageFragment()
                1 -> BlankFragment()
                2 -> BlankFragment()
                else -> BlankFragment()
            }

        override fun getCount() = COUNT_VIEW_PAGER_ITEM
    }

    override fun onBackPressed() {
        if (isDrawerOpened) {
            drawerLayout.closeDrawers()
        } else {
            backPressedTime = currentTimeMillis
        }
    }
}
