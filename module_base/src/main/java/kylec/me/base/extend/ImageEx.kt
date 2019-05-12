package kylec.me.base.extend

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kylec.me.base.R
import kylec.me.base.common.GlideApp

/**
 * Created by KYLE on 2019/5/12 - 16:16
 */

/**
 * @param any pic data source
 */
fun ImageView.loadSimple(any: Any) = GlideApp.with(this).load(any).into(this)

/**
 * @param any pic data source
 */
fun ImageView.loadComprehensive(
    any: Any, placeholder: Int = R.mipmap.ic_launcher,
    error: Int = R.drawable.load_failed,
    diskCacheStrategy: DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC
) =
    GlideApp.with(this).load(any)
        .placeholder(placeholder)
        .error(error)
        .diskCacheStrategy(diskCacheStrategy)
        .into(this)
