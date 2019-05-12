package kylec.me.base.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

private val HEADER_COUNT = 1

/**
 * Based on the PagedList multi-types Adapter.
 *
 * focus on [registerAdapterDataObserver]
 *
 * Created by KYLE on 2019/5/12 - 14:08
 */
class BaseMultiPagedAdapter<T : BaseItem> :
    PagedListAdapter<T, RecyclerView.ViewHolder>(Diff<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, HEADER_COUNT))
    }

    class Diff<T : BaseItem> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }
}
