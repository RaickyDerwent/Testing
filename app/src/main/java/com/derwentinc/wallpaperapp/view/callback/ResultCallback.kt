package com.derwentinc.wallpaperapp.view.callback

import com.derwentinc.wallpaperapp.model.Photo

interface ResultCallback {
    fun onResult(result: List<Photo>)
}