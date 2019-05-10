package kylec.me.base.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kylec.me.base.AppManager

/**
 * Created by KYLE on 2019/3/30 - 13:47
 */
abstract class BaseActivity : BaseAutoDisposeActivity() {

    protected lateinit var mySelf: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.pushActivity(this)
        setContentView(getLayoutId())

        mySelf = this

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

    /**
     * set layout view
     *
     * @return layout view id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected open fun initView() {}

    protected open fun initData() {}
}
