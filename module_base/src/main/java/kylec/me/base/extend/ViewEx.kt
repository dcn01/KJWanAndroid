package kylec.me.base.extend

import android.view.View
import androidx.annotation.IdRes
import com.google.android.material.snackbar.Snackbar

/**
 * Created by KYLE on 2019/5/8 - 14:30
 */

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.visible(isVisible: Boolean) {
    if (isVisible) visible() else gone()
}

fun View.snack(msg: String) = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()

inline fun <reified T : View> View.find(@IdRes id: Int): T = findViewById(id)
