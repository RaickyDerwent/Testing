package com.derwentinc.wallpaperapp.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.view.adapter.PhotoAdapter
import com.derwentinc.wallpaperapp.view.adapter.PhotoClickListener
import com.derwentinc.wallpaperapp.viewmodel.PhotoRepositoryImpl

class MainActivity : AppCompatActivity(), PhotoClickListener {
    override fun onPhotoClick(photo: Photo?) {
        val intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
        intent.putExtra("photo", photo)
        this@MainActivity.startActivity(intent)
    }

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoRepository: PhotoRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoRepository = PhotoRepositoryImpl("Japan")

        photoAdapter = PhotoAdapter(applicationContext, this)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            adapter = photoAdapter
        }

        photoRepository.getPhotos().observe(this, Observer { it -> photoAdapter.submitList(it) })
    }
}
