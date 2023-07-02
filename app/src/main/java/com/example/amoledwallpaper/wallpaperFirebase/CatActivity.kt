package com.example.amoledwallpaper.wallpaperFirebase

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.databinding.ActivityCatBinding
import com.example.amoledwallpaper.wallpaperFirebase.Adapter.CatImageAdapter
import com.example.amoledwallpaper.wallpaperFirebase.Model.WallpaperCatModel
import com.google.firebase.firestore.FirebaseFirestore

class CatActivity : AppCompatActivity() {

    lateinit var binding: ActivityCatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar)
        }
        //This code is used to hide the status bar in this activity
//      requestWindowFeature(Window.FEATURE_NO_TITLE);
//      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val db = FirebaseFirestore.getInstance()
        val uid = intent.getStringExtra("uid")//Key Abstract
        val name = intent.getStringExtra("name")//Category name


        db.collection("categories").document(uid!!).collection("wallpaper")
            .addSnapshotListener { value, error ->

                val listOfCatWallpaper = arrayListOf<WallpaperCatModel>()
                val data = value?.toObjects(WallpaperCatModel::class.java)
                listOfCatWallpaper.addAll(data!!)

                binding.catTitle.text = name.toString()
                binding.catCount.text = "${listOfCatWallpaper.size} Wallpaper Available"

                binding.catRcv.adapter = CatImageAdapter(this, listOfCatWallpaper)

                val layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                layoutManager.gapStrategy =
                    StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                binding.catRcv.layoutManager = layoutManager
            }
    }
}