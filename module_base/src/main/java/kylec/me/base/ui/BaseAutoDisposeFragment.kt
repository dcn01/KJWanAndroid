package kylec.me.base.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by KYLE on 2019/5/10 - 19:50
 */
abstract class BaseAutoDisposeFragment : Fragment() {

    private val compositeDisposable
        by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CompositeDisposable() }

    override fun onDestroy() {
        with(compositeDisposable) { if (!isDisposed) clear() }
        super.onDestroy()
    }

    protected fun Disposable.add() {
        compositeDisposable.add(this)
    }
}
