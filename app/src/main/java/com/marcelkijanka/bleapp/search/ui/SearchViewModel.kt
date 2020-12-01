package com.marcelkijanka.bleapp.search.ui

import android.bluetooth.BluetoothClass
import android.util.Log
import androidx.lifecycle.ViewModel
import com.marcelkijanka.bleapp.search.ui.adapter.SearchItemAdapter
import com.marcelkijanka.bleapp.shared.models.Device
import com.marcelkijanka.bleapp.shared.repository.DevicesRepository
import com.marcelkijanka.bleapp.util.extensions.deviceModel
import com.polidea.rxandroidble2.exceptions.BleScanException
import com.polidea.rxandroidble2.scan.ScanResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class SearchViewModel(
    private val devicesRepository: DevicesRepository
): ViewModel() {
    private val devices: Observable<ScanResult> = devicesRepository.scan()

    fun onDeviceClick(device: Device){

    }

    fun reloadButtonClick(adapter: SearchItemAdapter){
        devicesRepository.scan()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.addDevice(it.deviceModel)
            },{it.printStackTrace()})
    }

    private fun dispose() {
        scanDisposable = null
    }

    private var scanDisposable: Disposable? = null

    private fun onScanFailure(throwable: Throwable) {
        if (throwable is BleScanException) throwable.printStackTrace()
    }
}