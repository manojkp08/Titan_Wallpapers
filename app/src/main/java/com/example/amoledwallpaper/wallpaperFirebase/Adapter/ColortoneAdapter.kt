package com.example.amoledwallpaper.wallpaperFirebase.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.wallpaperFirebase.Model.ColortoneModel
import com.example.amoledwallpaper.wallpaperFirebase.WallpaperFinal

class ColortoneAdapter(val requireContext: Context, val listTheColorTone: ArrayList<ColortoneModel>) : RecyclerView.Adapter<ColortoneAdapter.tctViewHolder>() {

    inner class tctViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val cardBack = itemView.findViewById<CardView>(R.id.item_card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tctViewHolder {
        return tctViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_colortone, parent, false)
        )
    }

    override fun getItemCount() = listTheColorTone.size

    override fun onBindViewHolder(holder: tctViewHolder, position: Int) {

        val color = listTheColorTone[position].color
        holder.cardBack.setCardBackgroundColor(Color.parseColor(color))

        holder.cardBack.setOnClickListener {
            val intent = Intent(requireContext, WallpaperFinal::class.java)
            intent.putExtra("link", listTheColorTone[position].link)
            requireContext.startActivity(intent)
        }
    }

}