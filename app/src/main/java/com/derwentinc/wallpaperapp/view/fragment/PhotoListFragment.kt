package com.derwentinc.wallpaperapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.view.adapter.PhotoAdapter
import com.derwentinc.wallpaperapp.viewmodel.PhotoViewModel
import com.derwentinc.wallpaperapp.viewmodel.PhotoViewModelFactory

class PhotoListFragment : Fragment(), PhotoClickListener {

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoViewModel: PhotoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val createdView = inflater.inflate(R.layout.fragment_photo_list, container, false)

        val applicationContext = createdView!!.context.applicationContext
        photoAdapter = PhotoAdapter(applicationContext, this)

        photoViewModel = ViewModelProviders.of(this, PhotoViewModelFactory()).get(PhotoViewModel::class.java)

        recyclerView = createdView.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            adapter = photoAdapter
        }

        return createdView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoViewModel.getPhotos().observe(this, Observer { it -> photoAdapter.submitList(it) })
    }

    override fun onPhotoClicked(photo: Photo?) {
        val bundle = Bundle()
        bundle.putParcelable("photo", photo)
        view?.findNavController()?.navigate(R.id.photoViewFragment, bundle)
    }

}


interface PhotoClickListener {
    fun onPhotoClicked(photo: Photo?)
}
