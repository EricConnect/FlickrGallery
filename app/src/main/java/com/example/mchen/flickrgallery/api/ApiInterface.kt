package com.example.mchen.flickrgallery.api


import com.example.mchen.flickrgallery.data.FlickrPhotoList
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiInterface {
    //val flickrServiceUrl = "https://api.flickr.com/services/rest/?"

    @GET("https://api.flickr.com/services/rest/")
    fun getGalleryList(@QueryMap map: Map<String, String>): Observable<FlickrPhotoList>


    companion object {

        fun Create(rootUrl: String): ApiInterface{
            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()


            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(rootUrl)
                    .client(client)
                    .build()

            return retrofit.create(ApiInterface::class.java)

        }
    }

}
