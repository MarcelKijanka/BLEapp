package com.marcelkijanka.bleapp.app

import android.app.Application
import com.marcelkijanka.bleapp.details.di.DetailsKoinModuleFactory
import com.marcelkijanka.bleapp.search.di.SearchKoinModuleFactory
import com.marcelkijanka.bleapp.shared.di.BLEModuleFactory
import com.marcelkijanka.bleapp.shared.di.DeviceRepositoryModuleFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                    listOf(
                            SearchKoinModuleFactory.create(),
                            DetailsKoinModuleFactory.create(),
                            BLEModuleFactory.create(),
                            DeviceRepositoryModuleFactory.create()
                    )
            )
        }
    }
}
