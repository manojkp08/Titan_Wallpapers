package com.example.amoledwallpaper.wallpaperFirebase.Fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.databinding.FragmentDownloadBinding
import java.io.File


class DownloadFragment : Fragment() {

    private lateinit var binding: FragmentDownloadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)

        val allFiles: Array<File>
        val imageList = arrayListOf<String>()

        val targetPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Amoled Wallpaper"

        val targetFile = File(targetPath)
        allFiles = targetFile.listFiles()!!

        for(data in allFiles){
            imageList.add(data.absolutePath)
        }

        for(i in imageList){
            Log.e("@@@@", "onCreateView: " + i)
        }
        return binding.root
    }
}