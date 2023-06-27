package com.example.amoledwallpaper.wallpaperFirebase.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amoledwallpaper.R
import java.io.File

class ImageListAdapter(private val imageList: List<File>) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgDownloaded: ImageView = itemView.findViewById(R.id.imgDownloaded)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_downloaded, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageFile = imageList[position]
        Glide.with(holder.itemView)
            .load(imageFile)
            .into(holder.imgDownloaded)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
