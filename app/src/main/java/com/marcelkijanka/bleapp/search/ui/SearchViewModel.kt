package com.marcelkijanka.bleapp.search.ui

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
    private var disposable: Disposable? = null

    fun onDeviceClick(device: Device) {
        //TODO: Observer device
    }

    fun reloadButtonClick(adapter: SearchItemAdapter): Boolean {
        val state = (disposable == null) || (disposable?.isDisposed == true)
        if (state) {
            disposable = devices
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.addDevice(it.deviceModel)
                }, { it.printStackTrace() })
        } else
            disposable?.dispose()

        return state
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }
}