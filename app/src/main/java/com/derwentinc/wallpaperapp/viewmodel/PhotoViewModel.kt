package com.derwentinc.wallpaperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.repository.unsplash.ResultCallback
import com.derwentinc.wallpaperapp.service.repository.unsplash.UnsplashService

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


class PhotoDataSource(private val searchTerm: String) : PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        UnsplashService.registerCallback(object : ResultCallback {
            override fun onResult(photoList: List<Photo>) {
                callback.onResult(photoList, 1, 2)
            }
        })
        UnsplashService.getPhotos(searchTerm)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        UnsplashService.registerCallback(object : ResultCallback {
            override fun onResult(photoList: List<Photo>) {
                callback.onResult(photoList, params.key + 1)
            }
        })
        UnsplashService.getPhotos(searchTerm, params.key)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
    }

}

class PhotoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel() as T
    }
}