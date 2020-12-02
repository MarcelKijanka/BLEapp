package com.marcelkijanka.bleapp.shared.models

data class Device(
    val macAddress: String,
    var name: String?,
    var rssi: Int
    ) {
    fun equals(other: Device): Boolean {
        return macAddress == other.macAddress
    }
}