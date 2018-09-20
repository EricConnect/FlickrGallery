package com.example.mchen.flickrgallery.gallery

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.example.mchen.flickrgallery.GalleryAdapter
import com.example.mchen.flickrgallery.R
import com.example.mchen.flickrgallery.api.FlickrConfig
import com.example.mchen.flickrgallery.api.FlickrService
import com.example.mchen.flickrgallery.data.FlickrPhotoList
import com.example.mchen.flickrgallery.data.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.mchen.flickrgallery.util.EndlessScrollListener


class GalleryActivity : AppCompatActivity() {
    val TAG = "GalleryActivity"
    lateinit var mContext: Context
    lateinit var mRecyclerView: RecyclerView
    private var galleryList = ArrayList<Photo>()
    private var mCurrentPage = 1
    private var mMaxPage = 10
    private var isLoading = false
    private var mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        mContext = applicationContext
        mRecyclerView = findViewById(R.id.rv_gallery)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = GalleryAdapter(mContext, galleryList)
        mRecyclerView.addOnScrollListener(object : EndlessScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                getList(mCurrentPage, mMaxPage)
            }
        })

        getList(mCurrentPage, mMaxPage)


    }

    fun getList(page: Int, maxPage: Int?) {
        // if is onloading then do not change recyclerview
        // Or if page great the maxpage then return
        if (isLoading) return

        if ((maxPage?:10) < page) return

        isLoading = true 
        var service = FlickrService(FlickrConfig.apiKey)
        var result = service.getFlickPhotos("", page)
        //var list: Photos

        Log.d(TAG, "get list function run up. page:" + page.toString())

        result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { t: FlickrPhotoList? -> getFirstPagePhotoList(t) },
                        { error -> print("error: " + error.toString()) }
                )

    }

    fun getFirstPagePhotoList(flickrPhotoList: FlickrPhotoList?) {
        var list = flickrPhotoList?.photos?.photo
        mMaxPage = flickrPhotoList?.photos?.pages ?: 1 //set max page or just 1 when first time get results
        Log.d(TAG, "get first page from photo list. " + list?.size)
        if (list != null) {
            //mRecyclerView.adapter = GalleryAdapter(mContext, list)
            Log.d(TAG, "list is no null. gallery list size:" + galleryList.size)

            // must in order notify insert, insert elements to list then notify dataset change.
            // otherwise will blank
            mRecyclerView.adapter.notifyItemInserted(galleryList.size - list.size)
            galleryList.addAll(list)

            if (mRecyclerView.adapter == null) {
                mRecyclerView.adapter = GalleryAdapter(mContext, galleryList)
            }

            mRecyclerView.adapter.notifyDataSetChanged()


            if (mCurrentPage <= mMaxPage) {
                mCurrentPage++
            }


        } else {
            Log.e(TAG, "list is null")
        }
        isLoading = false


    }

}

