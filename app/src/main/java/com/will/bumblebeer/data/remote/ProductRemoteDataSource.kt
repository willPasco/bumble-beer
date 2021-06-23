package com.will.bumblebeer.data.remote

import com.will.bumblebeer.data.local.entities.ProductEntity
import com.will.bumblebeer.data.remote.dto.ProductDTO
import com.will.bumblebeer.infraestructure.ResultWrapper

/**
 * Interface to connect with the product entity in local database.
 */
interface ProductRemoteDataSource {
    suspend fun getProducts(): ResultWrapper<List<ProductDTO>>
}