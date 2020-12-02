package com.marcelkijanka.bleapp.search.ui

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelkijanka.bleapp.R
import com.marcelkijanka.bleapp.databinding.ActivitySearchBinding
import com.marcelkijanka.bleapp.details.ui.DetailsActivity
import com.marcelkijanka.bleapp.search.ui.adapter.SearchItemAdapter
import com.marcelkijanka.bleapp.shared.models.Device
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
            onDeviceClick = { device -> onDeviceItemClick(device) }
        )
        binding.searchRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.searchRecyclerview.adapter = searchItemAdapter
        binding.searchReload.setOnClickListener{reloadClick()}
    }

    private fun onDeviceItemClick(device: Device){
        val detailsIntent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("device", device)
        }
        startActivity(detailsIntent)
    }

    private fun reloadClick(){
        if (viewModel.reloadButtonClick(searchItemAdapter)) binding.searchReload.setImageResource(R.drawable.ic_stop)
        else binding.searchReload.setImageResource(R.drawable.ic_refresh)
    }

    private fun requestPermissions(){
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),PERMISSIONS_REQUESTCODE)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stop()
        binding.searchReload.setImageResource(R.drawable.ic_refresh)
    }
}