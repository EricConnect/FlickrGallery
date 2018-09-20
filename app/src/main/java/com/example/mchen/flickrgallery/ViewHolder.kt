package com.example.mchen.flickrgallery

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var itemImage: ImageView
    var itemTitle: TextView
    var itemSize: TextView
    var itemDimension: TextView
    init {
        itemImage = itemView.findViewById(R.id.card_iv_image)
        itemTitle = itemView.findViewById(R.id.card_tv_title)
        itemSize = itemView.findViewById(R.id.card_tv_size)
        itemDimension = itemView.findViewById(R.id.card_tv_dimension)
    }

}
