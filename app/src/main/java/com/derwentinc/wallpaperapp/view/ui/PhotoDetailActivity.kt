package com.derwentinc.wallpaperapp.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.view.fragment.PhotoViewFragment

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_detail_activity)

        val photoViewFragment = PhotoViewFragment()
        photoViewFragment.arguments = intent.extras

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, photoViewFragment).commit()
    }
}
