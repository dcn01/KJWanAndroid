package kylec.me.base.viewmodule

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by KYLE on 2019/5/8 - 22:05
 */
abstract class AutoDisposeViewModel : ViewModel() {

    private val compositeDisposable
        by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CompositeDisposable() }

    protected fun Disposable.add() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        with(compositeDisposable) { if (!isDisposed) clear() }
    }
}
