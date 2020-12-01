package com.marcelkijanka.bleapp.shared.di

import com.marcelkijanka.bleapp.shared.sources.DevicesDAO
import com.polidea.rxandroidble2.RxBleClient
import org.koin.dsl.module

object BLEModuleFactory {

    fun create() = module {
        single {
            RxBleClient.create(get())
        }

        factory {
            DevicesDAO(
                client = get()
            )
        }
    }
}