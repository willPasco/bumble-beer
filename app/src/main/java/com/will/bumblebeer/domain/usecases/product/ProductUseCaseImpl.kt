package com.will.bumblebeer.domain.usecases.product

import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.JoinProductCustomer
import com.will.bumblebeer.domain.entities.Product
import com.will.bumblebeer.data.ProductRepository

class ProductUseCaseImpl(private val repository: ProductRepository) : ProductUseCase {
    override suspend fun getJoinProductCustomer(): ResultWrapper<List<JoinProductCustomer>> =
        repository.getJoinProductCustomer()

    override suspend fun getProducts(): ResultWrapper<List<Product>> =
        repository.getProducts()

    override suspend fun getProductById(productId: Int): ResultWrapper<Product> =
        repository.getProductById(productId)

    override suspend fun insertSingleProduct(product: Product): ResultWrapper<Boolean> =
        repository.insertSingleProduct(product)
}