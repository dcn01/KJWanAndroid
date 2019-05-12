package kylec.me.base.viewmodule

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kylec.me.base.extend.d

/**
 * Created by KYLE on 2019/5/8 - 15:36
 */
abstract class BaseViewModel : BaseAutoDisposeViewModel(), DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        d(tag = "ViewModule--${javaClass.simpleName}", msg = "onCreate")
    }

    override fun onResume(owner: LifecycleOwner) {
        d(tag = "ViewModule--${javaClass.simpleName}", msg = "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        d(tag = "ViewModule--${javaClass.simpleName}", msg = "onPause")
    }

    override fun onStart(owner: LifecycleOwner) {
        d(tag = "ViewModule--${javaClass.simpleName}", msg = "onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        d(tag = "ViewModule--${javaClass.simpleName}", msg = "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        d(tag = "ViewModule--${javaClass.simpleName}", msg = "onDestroy")
    }
}
