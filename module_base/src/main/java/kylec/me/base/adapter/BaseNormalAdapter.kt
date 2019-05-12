package kylec.me.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kylec.me.base.extend.toastShort

/**
 * Created by KYLE on 2019/5/12 - 16:42
 */
abstract class BaseNormalAdapter<T>(
    @LayoutRes private val layoutId: Int,
    var data: ArrayList<T> = ArrayList()
) : RecyclerView.Adapter<BaseViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        return BaseViewHolder(parent, layoutId)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, data[position], position)
    }

    protected abstract fun convert(holder: BaseViewHolder, item: T, position: Int)

    fun toastShort(msg: String) = context?.toastShort(msg)
}

