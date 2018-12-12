package com.derwentinc.wallpaperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.Config.Builder
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.viewmodel.datasource.PhotoDataSourceFactory

class PhotoRepositoryImpl(private val searchTerm: String) {

    fun getPhotos(): LiveData<PagedList<Photo>> {
        val config = Builder()
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(5)
            .setPageSize(10)
            .build();

        return LivePagedListBuilder(PhotoDataSourceFactory(searchTerm), config)
            .setInitialLoadKey(1)
            .build()
    }

}