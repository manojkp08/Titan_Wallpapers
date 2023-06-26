package com.example.amoledwallpaper.wallpaperFirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.databinding.ActivityColorToneBinding

class ColorToneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColorToneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}