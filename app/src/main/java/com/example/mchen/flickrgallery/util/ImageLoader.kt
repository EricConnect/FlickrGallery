package com.example.mchen.flickrgallery.util


import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target


object ImageLoader {
    fun load(context: Context, url: String, iv: ImageView) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
                .crossFade()
                .into(iv)
    }

    fun load(context: Context, url: String, iv: ImageView, placeholder: Int) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
                .crossFade()
                .placeholder(placeholder)
                .into(iv)
    }

    fun load(context: Context, resId: Int, iv: ImageView) {
        Glide.with(context)
                .load(resId)
                .crossFade()
                .into(iv)
    }

    /**
     * 需要在子线程执行
     *
     * @param context
     * @param url
     * @return
     */
    fun load(context: Context, url: String): Bitmap? {
        try {
            return Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
