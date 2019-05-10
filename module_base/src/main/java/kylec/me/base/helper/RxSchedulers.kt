package kylec.me.base.helper

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by KYLE on 2019/5/8 - 10:57
 */
object RxSchedulers {

    val io: Scheduler
        get() = Schedulers.io()

    val ui: Scheduler
        get() = AndroidSchedulers.mainThread()

    fun <T> io2ui() =
        ObservableTransformer<T, T> { upstream -> upstream.observeOn(ui).subscribeOn(io) }
}
