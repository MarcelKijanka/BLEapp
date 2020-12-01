package com.marcelkijanka.bleapp.search.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelkijanka.bleapp.R
import com.marcelkijanka.bleapp.databinding.ActivitySearchBinding
import com.marcelkijanka.bleapp.search.ui.adapter.SearchItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUESTCODE = 100

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchItemAdapter: SearchItemAdapter
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissions()
        setupComponents()
    }

    private fun setupComponents(){
        searchItemAdapter = SearchItemAdapter(
            onDeviceClick = { device -> viewModel.onDeviceClick(device) }
        )
        binding.searchRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.searchRecyclerview.adapter = searchItemAdapter
        binding.searchReload.setOnClickListener{viewModel.reloadButtonClick(searchItemAdapter)}
    }

    private fun requestPermissions(){
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),PERMISSIONS_REQUESTCODE)
    }
}