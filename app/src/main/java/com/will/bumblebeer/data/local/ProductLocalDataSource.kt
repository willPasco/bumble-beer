package com.will.bumblebeer.data.local

import androidx.lifecycle.LiveData
import com.will.bumblebeer.data.local.entities.JoinProductCustomerEntity
import com.will.bumblebeer.data.local.entities.ProductEntity
import com.will.bumblebeer.infraestructure.ResultWrapper

/**
 * Interface to connect with the product entity in local database.
 */
interface ProductLocalDataSource {
    suspend fun insertProduct(productEntity: ProductEntity): ResultWrapper<Boolean>
    suspend fun getProducts(): ResultWrapper<List<ProductEntity>>
    suspend fun getJoinProductCustomer(): ResultWrapper<List<JoinProductCustomerEntity>>
    suspend fun getProductById(productId: Int): ResultWrapper<ProductEntity>
    fun observerAllProducts(): LiveData<ResultWrapper<List<ProductEntity>>>
}