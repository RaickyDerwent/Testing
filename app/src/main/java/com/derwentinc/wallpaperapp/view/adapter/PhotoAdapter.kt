package com.derwentinc.wallpaperapp.view.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo

fun getScreenWidth(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val metrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun getScreenHeight(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val metrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

class PhotoAdapter(val context: Context, val photoClickListener: PhotoClickListener) : PagedListAdapter<Photo, PhotoViewHolder>(PhotoDiffUtil) {
    private val screenWidth: Int = getScreenWidth(context)
    private val screenHeight: Int = getScreenHeight(context)

    companion object {
        private object PhotoDiffUtil : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem.url === newItem.url

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem.url === newItem.url

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.imageView.setOnClickListener { photoClickListener.onPhotoClick(getItem(position)) }
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().fitCenter().override(screenWidth, screenHeight))
            .asBitmap()
            .load(getItem(position)?.url)
            .into(holder.imageView)
    }
}

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView = view.findViewById<ImageView>(R.id.imageView)!!
}

interface PhotoClickListener {
    fun onPhotoClick(photo: Photo?)
}