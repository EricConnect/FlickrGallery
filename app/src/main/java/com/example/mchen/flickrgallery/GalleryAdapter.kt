package com.example.mchen.flickrgallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.example.mchen.flickrgallery.data.Image
import com.example.mchen.flickrgallery.data.Photo
import java.lang.Exception
import java.util.*

class GalleryAdapter(var context: Context, var list:ArrayList<Photo>): RecyclerView.Adapter<ViewHolder>(){
    private lateinit var onLoadMoreListener: OnLoadMoreListener

    interface OnLoadMoreListener{
        fun onLoadMore()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
        return ViewHolder(holder)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = list[position]
        holder.itemTitle.text = list[position].title
        val uri = Uri.parse(image.getUrl())
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                //.into(holder.itemImage)
                .into(object: SimpleTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                        Log.d("", resource?.width.toString())
                        holder.itemImage.setImageBitmap(resource)
                        holder.itemDimension.text = String.format("%d * %d", resource?.width,resource?.height)
                        holder.itemSize.text = String.format("%dk  -%d",(resource?.byteCount?:0)/1024, position)
                        //holder.itemView.layoutParams.height = ((resource?.height ?: 0) + 75)*2
                    }

                })

    }



}
