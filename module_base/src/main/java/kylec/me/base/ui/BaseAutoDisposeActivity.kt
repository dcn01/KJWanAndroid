package kylec.me.base.ui

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by KYLE on 2019/5/10 - 20:08
 */
abstract class BaseAutoDisposeActivity : AppCompatActivity() {

    private val compositeDisposable
        by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CompositeDisposable() }

    override fun onDestroy() {
        with(compositeDisposable) { if (!isDisposed) clear() }
        super.onDestroy()
    }

    fun Disposable.add() {
        compositeDisposable.add(this)
    }
}
