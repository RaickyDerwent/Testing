package com.derwentinc.wallpaperapp.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.repositories.PhotoRepositoryImpl
import com.derwentinc.wallpaperapp.view.adapter.PhotoAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoRepository: PhotoRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoRepository = PhotoRepositoryImpl("Japan")

        photoAdapter = PhotoAdapter(applicationContext)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            adapter = photoAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        photoRepository.getPhotos().observe(this, Observer { it -> photoAdapter.submitList(it) })
    }
}
