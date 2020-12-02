package com.marcelkijanka.bleapp.details.ui

import androidx.lifecycle.ViewModel
import com.marcelkijanka.bleapp.shared.models.Device
import com.marcelkijanka.bleapp.shared.models.Services
import com.marcelkijanka.bleapp.shared.repository.DevicesRepository
import com.polidea.rxandroidble2.RxBleDeviceServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class DetailsViewModel(
        private val devicesRepository: DevicesRepository
) : ViewModel() {
    private var disposable: Disposable? = null

    fun startUpdates(device: Device, updateFunc: (Services)->Unit){
        disposable = devicesRepository.connect(device)
                .flatMapSingle { connection -> connection.discoverServices() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ updateFunc(Services(it)) }
    }

    fun stop(){
        disposable?.dispose()
        disposable = null
    }
}