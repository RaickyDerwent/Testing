package com.derwentinc.wallpaperapp.viewmodel.datasource

import androidx.paging.PageKeyedDataSource
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.repository.unsplash.ResultCallback
import com.derwentinc.wallpaperapp.service.repository.unsplash.UnsplashService

class PhotoDataSource(private val searchTerm: String) : PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        UnsplashService.registerCallback(object : ResultCallback {
            override fun onResult(photoList: List<Photo>) {
                callback.onResult(photoList, 1, 2);
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