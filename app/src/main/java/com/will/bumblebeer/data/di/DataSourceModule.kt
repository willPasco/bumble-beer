package com.will.bumblebeer.data.di

import com.will.bumblebeer.data.remote.ProductRemoteDataSource
import com.will.bumblebeer.data.local.CustomerLocalDataSource
import com.will.bumblebeer.data.local.CustomerLocalDataSourceImpl
import com.will.bumblebeer.data.local.ProductLocalDataSource
import com.will.bumblebeer.data.local.ProductLocalDataSourceImpl
import com.will.bumblebeer.data.remote.CustomerRemoteDataSource
import com.will.bumblebeer.data.remote.CustomerRemoteDataSourceImpl
import com.will.bumblebeer.data.remote.ProductRemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    factory<ProductLocalDataSource>(named(PRODUCT_LOCAL_DATA_SOURCE)) { ProductLocalDataSourceImpl(productDao = get()) }
    factory<ProductRemoteDataSource>(named(PRODUCT_REMOTE_DATA_SOURCE)) { ProductRemoteDataSourceImpl(api = get()) }
    factory<CustomerLocalDataSource>(named(CUSTOMER_LOCAL_DATA_SOURCE)) { CustomerLocalDataSourceImpl(customerDao = get()) }
    factory<CustomerRemoteDataSource>(named(CUSTOMER_REMOTE_DATA_SOURCE)) { CustomerRemoteDataSourceImpl(api = get()) }
}

const val PRODUCT_LOCAL_DATA_SOURCE = "product_local_data_source"
const val PRODUCT_REMOTE_DATA_SOURCE = "product_remote_data_source"

const val CUSTOMER_LOCAL_DATA_SOURCE = "customer_local_data_source"
const val CUSTOMER_REMOTE_DATA_SOURCE = "customer_remote_data_source"