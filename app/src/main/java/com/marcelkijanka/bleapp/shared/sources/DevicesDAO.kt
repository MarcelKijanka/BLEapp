package com.marcelkijanka.bleapp.shared.sources

import com.marcelkijanka.bleapp.shared.models.Device
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.RxBleConnection
import com.polidea.rxandroidble2.RxBleDevice
import com.polidea.rxandroidble2.scan.ScanFilter
import com.polidea.rxandroidble2.scan.ScanResult
import com.polidea.rxandroidble2.scan.ScanSettings
import io.reactivex.Observable

class DevicesDAO(
    private val client: RxBleClient
) {
    fun scan(): Observable<ScanResult>{
        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .build()

        return client.scanBleDevices(scanSettings)
    }

    fun connect(device: Device): Observable<RxBleConnection> {
        val bleDevice: RxBleDevice = client.getBleDevice(device.macAddress)
        return bleDevice.establishConnection(false)
    }
}