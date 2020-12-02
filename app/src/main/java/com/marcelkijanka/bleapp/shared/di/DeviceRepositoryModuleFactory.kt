package com.marcelkijanka.bleapp.shared.di

import com.marcelkijanka.bleapp.shared.repository.DevicesRepository
import org.koin.dsl.module

object DeviceRepositoryModuleFactory {
    fun create() = module {
        factory{
            DevicesRepository(
                    source = get()
            )
        }
    }
}