package com.derwentinc.wallpaperapp.view.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.repository.unsplash.UnsplashService
import com.derwentinc.wallpaperapp.view.adapter.CustomAdapter
import com.derwentinc.wallpaperapp.view.callback.ResultCallback

class MainActivity : AppCompatActivity(), ResultCallback {
    override fun onResult(result: List<Photo>) {
        adapter.update(result)
    }

    private lateinit var adapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CustomAdapter(applicationContext, ArrayList())

        recyclerView = findViewById(R.id.recycler_view)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        UnsplashService.registerCallback(this)
        UnsplashService.getPhotos("Japan")
    }

    override fun onPause() {
        super.onPause()

        UnsplashService.unregisterCallback(this)
    }
}
