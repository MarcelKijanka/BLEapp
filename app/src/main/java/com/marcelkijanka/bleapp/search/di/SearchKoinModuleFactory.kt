package com.marcelkijanka.bleapp.search.di

import com.marcelkijanka.bleapp.shared.repository.DevicesRepository
import com.marcelkijanka.bleapp.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SearchKoinModuleFactory {
    fun create() = module{
        viewModel {
            SearchViewModel(
                devicesRepository = get()
            )
        }
    }
}