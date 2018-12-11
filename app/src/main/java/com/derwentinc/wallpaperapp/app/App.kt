package com.derwentinc.wallpaperapp.app

import android.app.Application
import com.derwentinc.wallpaperapp.service.repository.unsplash.UnsplashService

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        UnsplashService.create(applicationContext)
    }
}