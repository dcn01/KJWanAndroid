package kylec.me.user.ui.interfaces

import com.alibaba.android.arouter.facade.annotation.Route
import kylec.me.base.RoutePath
import kylec.me.base.ui.BaseActivity
import kylec.me.user.R

/**
 * Created by KYLE on 2019/5/7 - 16:24
 */
@Route(path = RoutePath.TO_USER)
class UserActivity(override val layoutId: Int = R.layout.activity_user) : BaseActivity()
