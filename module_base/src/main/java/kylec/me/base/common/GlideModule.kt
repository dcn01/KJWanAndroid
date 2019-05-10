package kylec.me.base.common

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by KYLE on 2019/5/8 - 11:20
 */
@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(
            InternalCacheDiskCacheFactory(
                context,
                diskCacheFolderName(),
                diskCacheSizeBytes()
            )
        )
            .setMemoryCache(LruResourceCache(memoryCacheSizeBytes()))
    }

    /**
     * Implementations should return `false` after they and their dependencies have migrated
     * to Glide's annotation processor.
     */
    override fun isManifestParsingEnabled() = false

    /**
     * set the memory cache size, unit is the [Byte].
     */
    private fun memoryCacheSizeBytes(): Long = 1024 * 1024 * 20 // 20 MB

    /**
     * set the disk cache size, unit is the [Byte].
     */
    private fun diskCacheSizeBytes(): Long = 1024 * 1024 * 512 // 512 MB

    /**
     * set the disk cache folder's name.
     */
    private fun diskCacheFolderName() = "KJWanAndroid"
}
