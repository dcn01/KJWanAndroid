package kylec.me.user

import com.alibaba.android.arouter.launcher.ARouter
import kylec.me.base.RoutePath

/**
 * Created by KYLE on 2019/5/8 - 10:12
 */
object Router {

    fun startMainActivity() {
        ARouter.getInstance().build(RoutePath.TO_MAIN).navigation()
    }
}
