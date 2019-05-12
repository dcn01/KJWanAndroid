package kylec.me.user.ui.interfaces.about

import kylec.me.base.ui.BaseActivity
import kylec.me.user.R

/**
 * Created by KYLE on 2019/5/11 - 22:57
 */
class AboutActivity(override val layoutId: Int = R.layout.activity_about) : BaseActivity() {

    override fun showTitleView() = true

    override fun initView() {
        title = "关于项目"
    }
}
