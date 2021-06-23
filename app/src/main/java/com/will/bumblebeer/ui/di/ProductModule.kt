package com.will.bumblebeer.ui.di

import com.will.bumblebeer.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    viewModel { ProductViewModel(get()) }
}