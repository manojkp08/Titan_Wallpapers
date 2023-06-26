package com.example.amoledwallpaper.wallpaperFirebase.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.wallpaperFirebase.CatActivity
import com.example.amoledwallpaper.wallpaperFirebase.Model.CategoryModel

class CategoryAdapter(val requireContext: Context,val listOfCategory: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.bomViewHolder>() {

    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageview = itemView.findViewById<ImageView>(R.id.cat_image)
        val name = itemView.findViewById<TextView>(R.id.cat_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_cat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        //We will use glide to load our image/wallpaper
        holder.name.text = listOfCategory[position].name
        Glide.with(requireContext).load(listOfCategory[position].link).into(holder.imageview)
        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, CatActivity::class.java)
            intent.putExtra("uid", listOfCategory[position].id)
            intent.putExtra("name", listOfCategory[position].name)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listOfCategory.size

}