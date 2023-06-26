package com.example.amoledwallpaper.wallpaperFirebase

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.amoledwallpaper.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.net.URL
import java.util.Objects

class WallpaperFinal : AppCompatActivity() {

    private lateinit var btnSetWallpaper: ImageView
    private lateinit var btnDownload: ImageView
    private lateinit var finalWallpaper: ImageView
    lateinit var zoomed: Animation
    lateinit var fadeIn: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper_final)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar)
        }

        btnSetWallpaper = findViewById(R.id.btn_SetWallpaper)
        btnDownload = findViewById(R.id.btn_Download)
        finalWallpaper = findViewById(R.id.final_wallpaper)
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        zoomed = AnimationUtils.loadAnimation(this, R.anim.zoomed_anim)

        val url = intent.getStringExtra("link")

        val urlImage = URL(url)

        Glide.with(this).load(url).into(finalWallpaper)

        //Fade in animation
        val fadeInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        finalWallpaper.startAnimation(fadeInAnimation)


        btnSetWallpaper.setOnClickListener {
            //set wallpaper
            val result: kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }

            btnSetWallpaper.startAnimation(zoomed)
            btnSetWallpaper.startAnimation(fadeIn)
            btnDownload.clearAnimation()

        }

        btnDownload.setOnClickListener {
            // download wallpaper
            val result: kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {
                saveImage(result.await())
            }

            btnDownload.startAnimation(zoomed)
            btnDownload.startAnimation(fadeIn)
            btnSetWallpaper.clearAnimation()

        }
    }

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException){
            null
        }
    }

    private fun saveImage(image: Bitmap?){
        val random1 = java.util.Random().nextInt(520985)
        val random2 = java.util.Random().nextInt(952663)

        val name = "AMOLED-${random1 + random2}"

        val data: OutputStream
        try {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Amoled Wallpaper"
            )
            val imageURI = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageURI)!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG, 100, data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            Toast.makeText(this, "Image Not Saved", Toast.LENGTH_SHORT).show()
        }
    }
}