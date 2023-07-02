package com.example.amoledwallpaper.wallpaperFirebase.Fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.amoledwallpaper.R
import com.example.amoledwallpaper.databinding.FragmentHomeBinding
import com.example.amoledwallpaper.databinding.ItemBestofthemonthBinding
import com.example.amoledwallpaper.wallpaperFirebase.Adapter.BomAdapter
import com.example.amoledwallpaper.wallpaperFirebase.Adapter.CategoryAdapter
import com.example.amoledwallpaper.wallpaperFirebase.Adapter.ColortoneAdapter
import com.example.amoledwallpaper.wallpaperFirebase.Model.BomModel
import com.example.amoledwallpaper.wallpaperFirebase.Model.CategoryModel
import com.example.amoledwallpaper.wallpaperFirebase.Model.ColortoneModel
import com.google.firebase.FirebaseApp
import com.google.firebase.components.Lazy
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    //Just because we are using firestore database, that's why we are creating a variable below for that database
    lateinit var db: FirebaseFirestore
    lateinit var otherBinding: ItemBestofthemonthBinding
//    lateinit var listBestOfTheMonth : ArrayList<BomModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Now, inside this onCreateView method we need to initialize this database variable
        FirebaseApp.initializeApp(requireContext())

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        db = FirebaseFirestore.getInstance()


        db.collection("bestofthemonth").addSnapshotListener{ value, error ->
            //The value that we are getting, we want to store it inside this "data".
            //Ok now in our firebase, we have 2 fields i.e id and link. So we have to make
            //a data model class also.
            //Because the data class is used to fetch the data from the database.
            val listBestOfTheMonth = arrayListOf<BomModel>()

            //we need to give a safe call and null check
            val data = value?.toObjects(BomModel::class.java)
            listBestOfTheMonth.addAll(data!!)

            //Reverse layout ko true karne pe na aapke images last se shuru honge, pehle se nahi. Isiliye usko false kar do.
            binding.rcvBom.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rcvBom.adapter = BomAdapter(requireContext(), listBestOfTheMonth)

        }

        db.collection("thecolortone").addSnapshotListener{ value, error ->

            val listTheColorTone = arrayListOf<ColortoneModel>()
            val data = value?.toObjects(ColortoneModel::class.java)
            listTheColorTone.addAll(data!!)

//            binding.rcvTct.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            binding.rcvTct.layoutManager = GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            binding.rcvTct.adapter = ColortoneAdapter(requireContext(), listTheColorTone)

        }

        db.collection("categories").addSnapshotListener{ value, error ->
            val listOfCategory = arrayListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel::class.java)
            listOfCategory.addAll(data!!)
            binding.rcvCat.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvCat.adapter = CategoryAdapter(requireContext(), listOfCategory)
        }

        return binding.root
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Update the layout manager span count based on the new configuration
        val layoutManagerRcvBom = binding.rcvBom.layoutManager as LinearLayoutManager
        layoutManagerRcvBom.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManagerColortone = binding.rcvTct.layoutManager as GridLayoutManager
        layoutManagerColortone.spanCount = 5
        layoutManagerColortone.orientation = GridLayoutManager.HORIZONTAL

        val layoutManagerCategory = binding.rcvCat.layoutManager as GridLayoutManager
        layoutManagerCategory.spanCount = 3
        layoutManagerCategory.orientation = GridLayoutManager.VERTICAL
    }
}