package com.marcelkijanka.bleapp.details.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.marcelkijanka.bleapp.R
import com.marcelkijanka.bleapp.databinding.ActivityDetailsBinding
import com.marcelkijanka.bleapp.shared.models.Device
import com.marcelkijanka.bleapp.shared.models.Services
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var device: Device
    private lateinit var binding: ActivityDetailsBinding

    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()
        bindView()
        startUpdates()
    }

    fun startUpdates(){
        viewModel.startUpdates(device) { updateData(it) }
    }

    fun bindView(){
        with(binding){
            detailsName.text = device.name
            detailsMac.text = device.macAddress
            detailsServiceList.text = getString(R.string.please_wait)
        }
    }

    fun updateData(services: Services){
        binding.detailsServiceList.apply {
            text = ""
            for (service in services.serviceList()) {
                append("SERVICE (${service.uuid.toString().toUpperCase()})")
                append("\n")
                for(characteristic in service.characteristics){
                    append("CHARACTERISTIC (${characteristic.uuid.toString().toUpperCase()})\n")
                    append("\n")
                }
                append("\n\n")
            }
        }
    }

    fun getExtras(){
        device = intent.getParcelableExtra("device")!!
    }

    override fun onStop() {
        super.onStop()
        viewModel.stop()
    }
}