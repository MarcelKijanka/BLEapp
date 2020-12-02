package com.marcelkijanka.bleapp.shared.models

import com.polidea.rxandroidble2.RxBleDeviceServices

data class Services(
        private val services: RxBleDeviceServices
){
    fun serviceList() = services.bluetoothGattServices
}
