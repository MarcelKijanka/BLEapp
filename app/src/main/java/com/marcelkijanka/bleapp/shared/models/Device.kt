package com.marcelkijanka.bleapp.shared.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    val macAddress: String,
    var name: String?,
    var rssi: Int
    ): Parcelable {
    fun equals(other: Device): Boolean {
        return macAddress == other.macAddress
    }
}