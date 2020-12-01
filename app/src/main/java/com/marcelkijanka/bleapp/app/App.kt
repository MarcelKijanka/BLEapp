package com.marcelkijanka.bleapp.app

import android.app.Application
import com.marcelkijanka.bleapp.search.di.SearchKoinModuleFactory
import com.marcelkijanka.bleapp.util.extensions.BLEModuleFactory
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
                            BLEModuleFactory.create()
                    )
            )
        }
    }
}
