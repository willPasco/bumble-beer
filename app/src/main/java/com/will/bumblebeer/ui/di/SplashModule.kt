package com.will.bumblebeer.ui.di

import com.will.bumblebeer.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(get(), get()) }
}