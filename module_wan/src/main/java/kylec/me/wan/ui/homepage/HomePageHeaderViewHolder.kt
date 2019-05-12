package kylec.me.wan.ui.homepage

import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import kylec.me.base.adapter.BaseNormalAdapter
import kylec.me.base.adapter.BaseViewHolder
import kylec.me.base.extend.loadComprehensive
import kylec.me.wan.R
import kylec.me.wan.model.Banner

/**
 * Home page banner
 *
 * Created by KYLE on 2019/5/12 - 15:58
 */
class HomePageHeaderViewHolder : BaseNormalAdapter<ArrayList<Banner>>(R.layout.layout_home_page_header) {

    override fun convert(holder: BaseViewHolder, item: ArrayList<Banner>, position: Int) {
        val mBanner = holder.getView<BGABanner>(R.id.banner)

        // set banner data
        mBanner.setAdapter { _, itemView, model, _ ->
            model?.let { (itemView as ImageView).loadComprehensive(it) }
        }

        val titles = arrayListOf<String>()
        val picPaths = arrayListOf<String>()
        item.forEach {
            titles.add(it.title)
            picPaths.add(it.imagePath)
        }

        mBanner.setData(picPaths, titles)

        // banner item click listener
        mBanner.setDelegate { _, itemView, model, bannerPos ->
            toastShort("你点击了：位置：$bannerPos  model: $model  itemView: $itemView")
        }
    }
}
