package com.derwentinc.wallpaperapp.viewmodel.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.derwentinc.wallpaperapp.model.Photo

class PhotoDataSourceFactory : DataSource.Factory<Int, Photo>() {
    private val mutableDataSource = MutableLiveData<PhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val dataSource = PhotoDataSource()
        mutableDataSource.postValue(dataSource)
        return dataSource
    }
}