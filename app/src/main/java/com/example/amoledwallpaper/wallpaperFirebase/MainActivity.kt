package com.example.amoledwallpaper.wallpaperFirebase

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.databinding.ActivityMainBinding
import com.example.amoledwallpaper.wallpaperFirebase.Fragments.DownloadFragment
import com.example.amoledwallpaper.wallpaperFirebase.Fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var fadeIn: Animation
    lateinit var zoomedIn: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        //This code is used to hide the status bar in this activity
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar)
        }

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        zoomedIn = AnimationUtils.loadAnimation(this, R.anim.zoomed_anim)

//        But by default there should be one fragment already present. So:-
        replaceFragment(HomeFragment())


        binding.homefl.setBackgroundResource(R.drawable.nav_clicked_bg)

        binding.homefl.setOnClickListener {
//            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            replaceFragment(HomeFragment())
            binding.homefl.setBackgroundResource(R.drawable.nav_clicked_bg)
            binding.downloadfl.setBackgroundResource(0)
            binding.icHome.startAnimation(zoomedIn)
            binding.downloadfl.clearAnimation()
            binding.homefl.startAnimation(fadeIn)
            binding.icDownload.clearAnimation()

        }

        binding.downloadfl.setOnClickListener {
//            Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show()
            replaceFragment(DownloadFragment())
            binding.downloadfl.setBackgroundResource(R.drawable.nav_clicked_bg)
            binding.homefl.setBackgroundResource(0)
            binding.icDownload.startAnimation(zoomedIn)
            binding.homefl.clearAnimation()
            binding.downloadfl.startAnimation(fadeIn)
            binding.icHome.clearAnimation()

        }
    }

    //this function is used to replace the fragment of the MainActivity screen
    fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentReplace, fragment)
        transaction.commit()
//        The main problem in this code is that our fragments will overlap each other
//        supportFragmentManager.beginTransaction().add(R.id.fragmentReplace, fragment).commit()
    }
}