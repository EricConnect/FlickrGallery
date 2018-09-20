package com.example.mchen.flickrgallery.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FlickrPhotoList {

    @SerializedName("photos")
    @Expose
    var photos: Photos? = null
    @SerializedName("stat")
    @Expose
    var stat: String? = null

}
