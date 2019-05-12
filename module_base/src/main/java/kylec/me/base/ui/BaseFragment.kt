package kylec.me.base.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import kylec.me.base.extend.toastShort
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

/**
 * Created by KYLE on 2019/5/7 - 16:16
 */
abstract class BaseFragment : BaseAutoDisposeFragment(), View.OnTouchListener, KodeinAware {

    protected lateinit var mContext: FragmentActivity

    protected val parentKodein by kodein()

    override val kodeinContext: KodeinContext<*>
        get() = kcontext(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context as FragmentActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        getViewId(inflater, container, savedInstanceState),
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataAfterViewCreated(view)
    }

    /**
     * consumed. To prevent click the blanks through to the next layer
     */
    override fun onTouch(v: View?, event: MotionEvent?): Boolean = true

    /**
     * set layout view
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract fun getViewId(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int

    /**
     * Set data after some initialization here
     *
     * @param view current view
     */
    protected open fun initDataAfterViewCreated(view: View) {}

    protected inline fun <reified T : Activity> startActivity() {
        val intent = Intent(mContext, T::class.java)
        startActivity(intent)
    }

    protected fun toastShort(msg: String) = mContext.toastShort(msg)

    protected fun finish() = mContext.finish()
}
