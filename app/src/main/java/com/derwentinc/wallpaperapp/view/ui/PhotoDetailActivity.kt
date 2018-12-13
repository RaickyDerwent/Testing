package com.derwentinc.wallpaperapp.view.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_detail_activity)

        val photo = intent.getParcelableExtra<Photo>("photo")

        val photoView = findViewById<ImageView>(R.id.detail_imageView)
        val requestOptions = RequestOptions().fitCenter().fitCenter()

        Glide.with(applicationContext)
            .setDefaultRequestOptions(requestOptions)
            .asBitmap()
            .load(photo.url)
            .into(photoView)
    }
}
