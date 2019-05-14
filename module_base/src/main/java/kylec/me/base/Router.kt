package kylec.me.base

import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by KYLE on 2019/5/14 - 22:57
 */
object Router {

    const val KEY_TITLE = "KEY_TITLE"
    const val KEY_URL = "KEY_URL"

    fun startWebActivity(webTitle: String, url: String) {
        ARouter.getInstance().build(RoutePath.TO_WEB)
            .withString(KEY_TITLE, webTitle)
            .withString(KEY_URL, url)
            .navigation()
    }
}
