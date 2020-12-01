package com.marcelkijanka.bleapp.util.extensions

import com.marcelkijanka.bleapp.shared.models.Device
import com.polidea.rxandroidble2.RxBleDevice
import com.polidea.rxandroidble2.scan.ScanResult

val ScanResult.deviceModel: Device
    get() = Device(this.bleDevice.macAddress, this.bleDevice.name)
