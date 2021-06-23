package com.will.bumblebeer.ui.di

import com.will.bumblebeer.ui.product.ProductCrudViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productCrudModule = module {
    viewModel { ProductCrudViewModel(get(), get()) }
}