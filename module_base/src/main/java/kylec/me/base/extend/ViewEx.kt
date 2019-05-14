package kylec.me.base.extend

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

fun <VH : RecyclerView.ViewHolder, A : RecyclerView.Adapter<VH>> RecyclerView.init(
    _adapter: A,
    _layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
) {
    layoutManager = _layoutManager
    adapter = _adapter
}

// ----------------------------- TextView ---------------------------------

fun TextView.setDrawableLeft(resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0)
}

fun TextView.setDrawableLeft(res: Drawable) {
    setCompoundDrawablesWithIntrinsicBounds(res, null, null, null)
}

fun TextView.setDrawableRight(resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0)
}

fun TextView.setDrawableRight(res: Drawable) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, res, null)
}

fun TextView.setDrawableTop(resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0)
}

fun TextView.setDrawableTop(res: Drawable) {
    setCompoundDrawablesWithIntrinsicBounds(null, res, null, null)
}

fun TextView.setDrawableBottom(resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resId)
}

fun TextView.setDrawableBottom(res: Drawable) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, null, res)
}
