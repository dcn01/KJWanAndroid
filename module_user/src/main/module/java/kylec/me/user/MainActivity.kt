package kylec.me.user

import kylec.me.base.ui.BaseContainerActivity
import kylec.me.user.ui.interfaces.UserInfoFragment

/**
 * Created by KYLE on 2019/5/11 - 10:00
 */
class MainActivity : BaseContainerActivity() {

    override fun getFragment() = UserInfoFragment()
}
