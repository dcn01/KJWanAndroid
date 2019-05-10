package kylec.me.user.ui

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import kylec.me.base.RouterPath
import kylec.me.base.ui.BaseActivity
import kylec.me.user.R

/**
 * Created by KYLE on 2019/5/7 - 16:24
 */
@Route(path = RouterPath.TO_USER)
class UserActivity : BaseActivity() {

    companion object {
        fun starter(context: Context) {
            val starter = Intent(context, UserActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_user
}
