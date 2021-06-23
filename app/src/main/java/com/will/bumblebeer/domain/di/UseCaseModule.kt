package com.will.bumblebeer.domain.di

import com.will.bumblebeer.domain.usecases.customer.CustomerUseCase
import com.will.bumblebeer.domain.usecases.customer.CustomerUseCaseImpl
import com.will.bumblebeer.domain.usecases.product.ProductUseCase
import com.will.bumblebeer.domain.usecases.product.ProductUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase> { ProductUseCaseImpl(get()) }
    factory<CustomerUseCase> { CustomerUseCaseImpl(get()) }
}