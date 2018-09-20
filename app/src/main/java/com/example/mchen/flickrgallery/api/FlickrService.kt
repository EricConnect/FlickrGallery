package com.example.mchen.flickrgallery.api

import com.example.mchen.flickrgallery.data.FlickrPhotoList
import io.reactivex.Observable

class FlickrService(val apiKey: String){
    fun getFlickPhotos(apiMethod: String, page: Int?): Observable<FlickrPhotoList> {
        var photos = ApiInterface.Create(FlickrConfig.rootUrl)
        var query = hashMapOf<String, String>()
        query.put("method","flickr.photos.getRecent")
        query.put("api_key",FlickrConfig.apiKey)
        query.put("format","json")
        query.put("nojsoncallback","1")
        query.put("page", page?.toString() ?: "1")

        return photos.getGalleryList(query)
    }
}
