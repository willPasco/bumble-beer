package com.will.bumblebeer.ui.di

import com.will.bumblebeer.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val homeModule: Module = module {
    viewModel { HomeViewModel(get()) }
}