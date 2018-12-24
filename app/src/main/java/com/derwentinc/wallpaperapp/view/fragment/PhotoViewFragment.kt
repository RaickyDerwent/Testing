package com.derwentinc.wallpaperapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewFragment : Fragment() {
    private lateinit var imageView: PhotoView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_view, container, false).apply { imageView = findViewById(R.id.fragment_photo_view) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context)
            .asBitmap()
            .load(arguments?.getParcelable<Photo>("photo")?.url)
            .into(imageView)
    }

}
