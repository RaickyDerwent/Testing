package com.derwentinc.wallpaperapp.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.derwentinc.wallpaperapp.model.Photo

interface PhotoRepository {
    fun getPhotos(): LiveData<PagedList<Photo>>
}