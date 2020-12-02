package com.marcelkijanka.bleapp.search.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marcelkijanka.bleapp.databinding.DeviceItemBinding
import com.marcelkijanka.bleapp.shared.models.Device
import com.polidea.rxandroidble2.RxBleDevice
import com.polidea.rxandroidble2.scan.ScanResult

typealias onDeviceClickListener = (Device) -> Unit

class SearchItemAdapter(
    private val onDeviceClick: onDeviceClickListener
): RecyclerView.Adapter<SearchItemAdapter.ItemViewHolder>() {

    private val devices = mutableListOf<Device>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder{
        return ItemViewHolder(createBinding(parent))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(devices[position], onDeviceClick)
    }

    override fun getItemCount() = devices.count()

    fun addDevice(device: Device) {

        devices.firstOrNull { it.equals(device) }
            ?.let { deviceOnList ->
                deviceOnList.name = device.name
                deviceOnList.rssi = device.rssi
            }
            ?: devices.add(device)
        notifyDataSetChanged()
    }

    private fun createBinding(parent: ViewGroup): DeviceItemBinding {
        return DeviceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    class ItemViewHolder(
        private val binding: DeviceItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device, onDeviceClick: onDeviceClickListener) {
            with(binding) {
                deviceName.text = device.name
                deviceMac.text = device.macAddress
                deviceRssi.text = device.rssi.toString()

                root.setOnClickListener{onDeviceClick(device)}
            }
        }
    }
}

