package kylec.me.base.helper

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

/**
 * Created by KYLE on 2019/5/9 - 14:06
 */

@BindingAdapter(value = ["visibleGone"])
fun View.visibleGone(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}
