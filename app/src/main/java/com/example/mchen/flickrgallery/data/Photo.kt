package com.example.mchen.flickrgallery.data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("owner")
    @Expose
    var owner: String? = null
    @SerializedName("secret")
    @Expose
    var secret: String? = null
    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("farm")
    @Expose
    var farm: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ispublic")
    @Expose
    var ispublic: Int? = null
    @SerializedName("isfriend")
    @Expose
    var isfriend: Int? = null
    @SerializedName("isfamily")
    @Expose
    var isfamily: Int? = null

    fun getUrl():String{

        var urlFormat = "https://farm%s.staticflickr.com/%s/%s_%s_m.jpg"

        return String.format(urlFormat,farm,server,id,secret)
    }

}