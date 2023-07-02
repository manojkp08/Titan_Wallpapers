package com.example.amoledwallpaper.wallpaperFirebase.Fragments

//import android.os.Bundle
//import android.os.Environment
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.amoledwallpaper.R
//import com.example.amoledwallpaper.databinding.FragmentDownloadBinding
//import java.io.File
//
//
//class DownloadFragment : Fragment() {
//
//    private lateinit var binding: FragmentDownloadBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)
//
//        val allFiles: Array<File>
//        val imageList = arrayListOf<String>()
//
//        val targetPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Amoled Wallpaper"
//
//        val targetFile = File(targetPath)
//        allFiles = targetFile.listFiles()!!
//
//        for(data in allFiles){
//            imageList.add(data.absolutePath)
//        }
//
//        for(i in imageList){
//            Log.e("@@@@", "onCreateView: " + i)
//        }
//        return binding.root
//    }
//}

import android.content.res.Configuration
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.amoledwallpaper.databinding.FragmentDownloadBinding
import com.example.amoledwallpaper.wallpaperFirebase.Adapter.ImageListAdapter
import java.io.File

class DownloadFragment : Fragment() {

    private lateinit var binding: FragmentDownloadBinding
    private lateinit var imageListAdapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)

        val imageList = getDownloadedImageList()

        imageListAdapter = ImageListAdapter(imageList)
        binding.rcvDownloaded.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcvDownloaded.adapter = imageListAdapter

        return binding.root
    }

    private fun getDownloadedImageList(): List<File> {
        val targetPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Amoled Wallpaper"
        val targetFile = File(targetPath)
        return targetFile.listFiles()?.toList() ?: emptyList()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Update the layout manager span count based on the new configuration
        val layoutManager = binding.rcvDownloaded.layoutManager as StaggeredGridLayoutManager
        layoutManager.spanCount = 2
    }

}
