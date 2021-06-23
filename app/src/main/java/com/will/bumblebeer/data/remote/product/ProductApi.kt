package com.will.bumblebeer.data.remote.product

import com.will.bumblebeer.data.local.entities.ProductEntity
import com.will.bumblebeer.data.remote.dto.ProductDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET(value = "/getProducts")
    suspend fun getProducts(): Response<List<ProductDTO>>
}