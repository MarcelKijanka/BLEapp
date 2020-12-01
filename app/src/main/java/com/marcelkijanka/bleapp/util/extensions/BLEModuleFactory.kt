package com.marcelkijanka.bleapp.util.extensions

import com.polidea.rxandroidble2.RxBleClient
import org.koin.dsl.module

object BLEModuleFactory {

    fun create() = module {
        single {
            RxBleClient.create(get())
        }
    }
}