package kylec.me.base.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_base.*
import kylec.me.base.AppManager
import kylec.me.base.R

/**
 * Created by KYLE on 2019/3/30 - 13:47
 */
abstract class BaseActivity : BaseAutoDisposeActivity() {

    protected lateinit var mySelf: Activity

    /**
     * layout view id
     */
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.pushActivity(this)
        mySelf = this

        when {
            showTitleView() -> {
                setContentView(R.layout.activity_base)
                View.inflate(mySelf, layoutId, rootView)
            }
            else -> setContentView(layoutId)
        }

        initView()
        initData()
    }

    override fun onDestroy() {
        AppManager.instance.popActivity(this)
        super.onDestroy()
    }

    protected fun Fragment.commit(@IdRes containerViewId: Int) {
        supportFragmentManager.beginTransaction().apply {
            add(containerViewId, this@commit)
            commit()
        }
    }

    open fun showTitleView() = false

    protected open fun initView() {}

    protected open fun initData() {}

    override fun setTitle(title: CharSequence?) {
        baseTitleView.title = title
    }

    protected inline fun <reified T : Activity> startActivity() {
        val intent = Intent(mySelf, T::class.java)
        startActivity(intent)
    }
}
