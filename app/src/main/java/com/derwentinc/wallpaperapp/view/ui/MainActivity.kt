package com.derwentinc.wallpaperapp.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.view.adapter.CustomAdapter
import com.derwentinc.wallpaperapp.viewmodel.PhotoListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoListViewModel: PhotoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CustomAdapter(applicationContext, ArrayList())

        recyclerView = findViewById(R.id.recycler_view)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        photoListViewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        photoListViewModel.getPhotoList("Japan").observe(this, Observer { adapter.update(it) })
    }
}
