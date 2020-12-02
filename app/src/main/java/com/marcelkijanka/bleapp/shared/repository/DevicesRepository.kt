package com.marcelkijanka.bleapp.shared.repository

import com.marcelkijanka.bleapp.shared.models.Device
import com.marcelkijanka.bleapp.shared.sources.DevicesDAO

class DevicesRepository(
    private val source: DevicesDAO
) {
    fun scan() = source.scan()
    fun connect(device: Device) = source.connect(device)
}