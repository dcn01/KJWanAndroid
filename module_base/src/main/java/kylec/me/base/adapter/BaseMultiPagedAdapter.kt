package kylec.me.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

private const val ITEM_TYPE_HEADER = 0x111
private const val ITEM_TYPE_FOOTER = 0x222

/**
 * Based on the PagedList multi-types Adapter.
 *
 * focus on [registerAdapterDataObserver] to processing header
 *
 * Created by KYLE on 2019/5/12 - 14:08
 */
abstract class BaseMultiPagedAdapter<T : BaseItem>(
    private val layoutId: Int
) : PagedListAdapter<T, BaseViewHolder>(Diff<T>()) {

    protected var mContext: Context? = null

    protected open val headerCount = 0
    protected open val footerCount = 0

    override fun getItemViewType(position: Int) =
        when (position) {
            0 -> ITEM_TYPE_HEADER
            itemCount - 1 -> ITEM_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        mContext = parent.context
        return BaseViewHolder(parent, layoutId)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.apply { convert(holder, this, position) }
    }

    override fun getItem(position: Int): T? = super.getItem(position - headerCount)

    override fun getItemCount() = super.getItemCount() + headerCount + footerCount

    protected abstract fun convert(holder: BaseViewHolder, item: T, position: Int)

    /**
     * mainly process header item
     */
    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, headerCount))
    }
}

class Diff<T : BaseItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
