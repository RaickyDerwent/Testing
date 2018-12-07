package com.derwentinc.wallpaperapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.repository.unsplash.UnsplashService

class PhotoListViewModel : ViewModel() {
    fun getPhotoList(searchTerm: String): MutableLiveData<MutableList<Photo>> {
        return UnsplashService.getPhotos(searchTerm)
    }
}