package com.example.amoledwallpaper.wallpaperFirebase

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.splashStatusBarColor)
        }

        val leftFadeIn : Animation = AnimationUtils.loadAnimation(this, R.anim.left_fade_in)
        binding.splashLogo.startAnimation(leftFadeIn)

        val leftFadeIn2 : Animation = AnimationUtils.loadAnimation(this, R.anim.left_fade_in2)
        binding.appName.startAnimation(leftFadeIn2)

        val bottomFadeIn : Animation = AnimationUtils.loadAnimation(this, R.anim.bottom_fade_in)
        binding.madeWithLove.startAnimation(bottomFadeIn)

        Handler().postDelayed({
                              startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1700)

    }
}