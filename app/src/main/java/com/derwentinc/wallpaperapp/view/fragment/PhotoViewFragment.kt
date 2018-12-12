package com.derwentinc.wallpaperapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.view.adapter.getScreenHeight
import com.derwentinc.wallpaperapp.view.adapter.getScreenWidth
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewFragment : Fragment() {
    private lateinit var imageView: PhotoView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photo_view, container, false)
        imageView = view.findViewById(R.id.fragment_photo_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo = arguments?.getParcelable<Photo>("photo")

        val screenWidth = getScreenWidth(view.context.applicationContext)
        val screenHeight = getScreenHeight(view.context.applicationContext)
        val requestOptions = RequestOptions().fitCenter().override(screenWidth, screenHeight)
        Glide.with(view.context)
            //.applyDefaultRequestOptions(requestOptions)
            .asBitmap()
            .load(photo?.url)
            .into(imageView)
    }

}
