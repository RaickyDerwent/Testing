package com.derwentinc.wallpaperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.viewmodel.datasource.PhotoDataSource

class PhotoViewModel : ViewModel() {
    var photoLiveData: LiveData<PagedList<Photo>>

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(5)
            .setPageSize(10)
            .build()

        photoLiveData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Photo> {

        val dataSourceFactory = object : DataSource.Factory<Int, Photo>() {
            override fun create(): DataSource<Int, Photo> {
                return PhotoDataSource("japan")
            }
        }
        return LivePagedListBuilder<Int, Photo>(dataSourceFactory, config)
    }

    fun getPhotos(): LiveData<PagedList<Photo>> = this.photoLiveData
}