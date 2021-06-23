package com.will.bumblebeer.data.di

import com.will.bumblebeer.data.CustomerRepository
import com.will.bumblebeer.data.CustomerRepositoryImpl
import com.will.bumblebeer.data.ProductRepository
import com.will.bumblebeer.data.ProductRepositoryImpl
import com.will.bumblebeer.data.local.CustomerLocalDataSource
import com.will.bumblebeer.data.local.ProductLocalDataSource
import com.will.bumblebeer.data.remote.CustomerRemoteDataSource
import com.will.bumblebeer.data.remote.ProductRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    factory<ProductRepository> {
        ProductRepositoryImpl(
            get(named(PRODUCT_LOCAL_DATA_SOURCE)) as ProductLocalDataSource,
            get(named(PRODUCT_REMOTE_DATA_SOURCE)) as ProductRemoteDataSource
        )
    }

    factory<CustomerRepository> {
        CustomerRepositoryImpl(
            get(named(CUSTOMER_LOCAL_DATA_SOURCE)) as CustomerLocalDataSource,
            get(named(CUSTOMER_REMOTE_DATA_SOURCE)) as CustomerRemoteDataSource
        )
    }
}