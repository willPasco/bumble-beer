package com.will.bumblebeer.data.di

import com.will.bumblebeer.data.remote.customer.CustomerApi
import com.will.bumblebeer.data.remote.product.ProductApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    factory { providesOkHttpClient() }

    single {
        createWebService<ProductApi>(
            okHttpClient = get(),
            url = "https://private-e1c7ca-deliveryapppoc.apiary-mock.com"
        )
    }

    single {
        createWebService<CustomerApi>(
            okHttpClient = get(),
            url = "https://private-e1c7ca-deliveryapppoc.apiary-mock.com"
        )
    }
}

fun providesOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
