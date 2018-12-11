package com.derwentinc.wallpaperapp.viewmodel.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.derwentinc.wallpaperapp.model.Photo

class PhotoDataSourceFactory(val searchTerm: String) : DataSource.Factory<Int, Photo>() {
    private val mutableDataSource = MutableLiveData<PhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val dataSource = PhotoDataSource(searchTerm)
        mutableDataSource.postValue(dataSource)
        return dataSource
    }
}