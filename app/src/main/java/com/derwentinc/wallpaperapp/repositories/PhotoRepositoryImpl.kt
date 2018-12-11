package com.derwentinc.wallpaperapp.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.Config.Builder
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.viewmodel.datasource.PhotoDataSourceFactory

class PhotoRepositoryImpl(val searchTerm: String) : PhotoRepository {

    override fun getPhotos(): LiveData<PagedList<Photo>> {
        val config = Builder().setInitialLoadSizeHint(10)
            .setPageSize(10).build();

        val photoDataSourceFactory = PhotoDataSourceFactory(searchTerm)

        return LivePagedListBuilder(photoDataSourceFactory, config)
            .setInitialLoadKey(1)
            .build()
    }

}