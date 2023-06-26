package com.example.amoledwallpaper.wallpaperFirebase.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.wallpaperFirebase.Model.BomModel
import com.example.amoledwallpaper.wallpaperFirebase.Model.WallpaperCatModel
import com.example.amoledwallpaper.wallpaperFirebase.WallpaperFinal
import com.makeramen.roundedimageview.RoundedImageView

class CatImageAdapter(val requireContext: Context, val listOfCatWallpaper: ArrayList<WallpaperCatModel>) : RecyclerView.Adapter<CatImageAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageview = itemView.findViewById<RoundedImageView>(R.id.catImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_wallpapercat, parent, false)
        )
    }

    override fun getItemCount() = listOfCatWallpaper.size

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        //We will use glide to load our image/wallpaper
        holder.imageview.setOnClickListener {
            val intent = Intent(requireContext, WallpaperFinal::class.java)
            intent.putExtra("link", listOfCatWallpaper[position].link)
            requireContext.startActivity(intent)
        }
        Glide.with(requireContext).load(listOfCatWallpaper[position].link).into(holder.imageview)
    }

}