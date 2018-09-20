package com.example.mchen.flickrgallery.api

import android.util.Log
import com.example.mchen.flickrgallery.data.FlickrPhotoList
import com.example.mchen.flickrgallery.data.Photos
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import rx.Subscriber
import kotlin.concurrent.thread

class FlickrServiceTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getFlickPhotos() {
    }

    @Test
    fun getApiKey() {
        var service = FlickrService(FlickrConfig.apiKey)
        var result = service.getFlickPhotos("")
        var list: Photos

        result.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {t: FlickrPhotoList? -> printPhoto(t!!) },
                        {error -> print("error: " + error.toString())}
                )
        Thread.sleep(2000)
        //assert(result != Any())
    }
    fun printPhoto(flickrPhotoList: FlickrPhotoList){
        for(photo in arrayOf(flickrPhotoList.photos?.photo).iterator()){
            print(photo)
        }
    }
}
