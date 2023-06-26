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
import com.example.amoledwallpaper.wallpaperFirebase.WallpaperFinal

class BomAdapter(val requireContext: Context, val listBestOfTheMonth: ArrayList<BomModel>) : RecyclerView.Adapter<BomAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageview = itemView.findViewById<ImageView>(R.id.bom_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_bestofthemonth, parent, false)
        )
    }

    override fun getItemCount() = listBestOfTheMonth.size

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        //We will use glide to load our image/wallpaper
        holder.imageview.setOnClickListener {
            val intent = Intent(requireContext, WallpaperFinal::class.java)
            intent.putExtra("link", listBestOfTheMonth[position].link)
            requireContext.startActivity(intent)
        }
        Glide.with(requireContext).load(listBestOfTheMonth[position].link).into(holder.imageview)
    }

}