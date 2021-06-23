package com.will.bumblebeer.data.remote

import com.will.bumblebeer.data.remote.dto.ProductDTO
import com.will.bumblebeer.data.remote.product.ProductApi
import com.will.bumblebeer.infraestructure.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ProductRemoteDataSourceImpl(
    private val api: ProductApi,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRemoteDataSource(dispatcher = dispatcher), ProductRemoteDataSource {

    override suspend fun getProducts(): ResultWrapper<List<ProductDTO>> =
        serviceCall { api.getProducts() }
}