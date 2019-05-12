package kylec.me.base.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kylec.me.base.extend.loadSimple
import java.lang.IllegalArgumentException

/**
 * Created by KYLE on 2019/5/12 - 16:06
 */
class BaseViewHolder(
    parent: ViewGroup, @LayoutRes layoutId: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    private val views = SparseArray<View>()

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(@IdRes viewId: Int): T {
        var view = views.get(viewId)
        if (null == view) {
            view = itemView.findViewById(viewId)
            if (null == view) throw IllegalArgumentException("can not find view by $viewId")
            views.put(viewId, view)
        }

        return view as T
    }

    fun setText(@IdRes viewId: Int, text: CharSequence): BaseViewHolder {
        val textView = getView(viewId) as TextView
        textView.text = text

        return this
    }

    fun setImage(@IdRes viewId: Int, url: String): BaseViewHolder {
        val imageView = getView(viewId) as ImageView
        imageView.loadSimple(url)

        return this
    }

    fun setClickListener(@IdRes vararg viewIds: Int, block: (View) -> Unit): BaseViewHolder {
        viewIds.forEach {
            getView<View>(it).apply {
                setOnClickListener { block(this) }
            }
        }

        return this
    }
}
