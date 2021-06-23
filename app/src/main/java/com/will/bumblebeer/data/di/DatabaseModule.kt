package com.will.bumblebeer.data.di

import com.will.bumblebeer.data.local.db.BumbleBeerDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { BumbleBeerDatabase.createDatabase(get()) }
    single { get<BumbleBeerDatabase>().getProductDao() }
    single { get<BumbleBeerDatabase>().getCustomerDao() }
}