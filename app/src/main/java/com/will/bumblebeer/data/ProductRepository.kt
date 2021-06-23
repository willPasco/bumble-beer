package com.will.bumblebeer.data

import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.JoinProductCustomer
import com.will.bumblebeer.domain.entities.Product

interface ProductRepository {
    suspend fun insertSingleProduct(product: Product): ResultWrapper<Boolean>
    suspend fun getJoinProductCustomer(): ResultWrapper<List<JoinProductCustomer>>
    suspend fun getProducts(): ResultWrapper<List<Product>>
    suspend fun getProductById(productId: Int): ResultWrapper<Product>
}