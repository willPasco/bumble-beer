package com.will.bumblebeer.ui.di

import com.will.bumblebeer.ui.customer.CustomerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerModule = module {
    viewModel { CustomerViewModel(get()) }
}