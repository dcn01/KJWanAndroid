package kylec.me.base.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by KYLE on 2019/5/13 - 11:31
 */
interface IDelegateAdapter<T> {

    /**
     * Find the method that is called when the delegate returns the type that you can handle
     */
    fun isForViewType(t: T): Boolean

    /**
     * `OnCreateViewHolder` func for delegate adapter
     */
    fun onCreateViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int): BaseViewHolder

    /**
     * `onBindViewHolder` func for delegate adapter
     */
    fun convert(holder: BaseViewHolder, data: T, position: Int)
}
