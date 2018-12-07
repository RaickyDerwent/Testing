package com.derwentinc.wallpaperapp.view.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.view.adapter.CustomAdapter.CustomViewHolder

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

class CustomAdapter(private val context: Context, private var photoList: ArrayList<Photo>) : RecyclerView.Adapter<CustomViewHolder>() {
    private val screenWidth = getScreenWidth(context)
    private val screenHeight = getScreenHeight(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, position: Int) {
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().fitCenter().override(screenWidth, screenHeight))
            .asBitmap()
            .load(photoList[position].url)
            .into(viewHolder.imageView)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    fun update(photoList: List<Photo>) {
        this.photoList.clear()
        this.photoList.addAll(photoList)

        notifyDataSetChanged()
    }
}