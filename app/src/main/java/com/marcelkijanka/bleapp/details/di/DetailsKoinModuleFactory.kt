package com.marcelkijanka.bleapp.details.di

import com.marcelkijanka.bleapp.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailsKoinModuleFactory {
    fun create() = module {
        viewModel {
            DetailsViewModel(
                    devicesRepository = get()
            )
        }
    }
}