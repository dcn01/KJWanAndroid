package kylec.me.user.ui

import android.view.View
import android.view.WindowManager
import kylec.me.user.Router
import kylec.me.base.ui.BaseActivity
import kylec.me.base.user.UserConfig
import kylec.me.user.R
import kylec.me.user.ui.interfaces.UserActivity

/**
 * Created by KYLE on 2019/5/8 - 9:09
 */
class SplashActivity(override val layoutId: Int = R.layout.activity_splash) : BaseActivity() {

    override fun initView() {
        hideSystemNavigationBar()
    }

    override fun initData() {
        UserConfig.init()
        start()
    }

    private fun hideSystemNavigationBar() {
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = uiOptions
    }

    private fun start() {
        if (UserConfig.isLogin) {
            Router.startMainActivity()
        } else {
            startActivity<UserActivity>()
        }

        finish()
    }

    override fun finish() {
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        super.finish()
    }
}
