package kylec.me.mjkwanandroid

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_main.*
import kylec.me.base.AppManager
import kylec.me.base.RouterPath
import kylec.me.base.common.currentTimeMillis
import kylec.me.base.extend.snack
import kylec.me.base.ui.BaseActivity
import kylec.me.user.ui.interfaces.UserInfoFragment
import kylec.me.wan.ui.homepage.HomePageFragment
import kotlin.properties.Delegates

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
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerClosed(drawerView: View) {
                isDrawerOpened = false
            }

            override fun onDrawerOpened(drawerView: View) {
                isDrawerOpened = true
            }
        })

        UserInfoFragment.newInstance().commit(R.id.leftNavigationView)
        HomePageFragment.newInstance().commit(R.id.mainContainer)
    }

    override fun onBackPressed() {
        if (isDrawerOpened) {
            drawerLayout.closeDrawers()
        } else {
            backPressedTime = currentTimeMillis
        }
    }
}
