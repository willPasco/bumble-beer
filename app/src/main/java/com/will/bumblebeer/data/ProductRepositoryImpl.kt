package com.will.bumblebeer.data

import com.will.bumblebeer.data.remote.ProductRemoteDataSource
import com.will.bumblebeer.data.local.entities.ProductEntity
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.JoinProductCustomer
import com.will.bumblebeer.domain.entities.Product
import com.will.bumblebeer.data.local.mappers.JoinProductCustomerMapper
import com.will.bumblebeer.data.local.ProductLocalDataSource
import com.will.bumblebeer.data.local.mappers.ProductMapper

class ProductRepositoryImpl(
    private val localDatasource: ProductLocalDataSource,
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun getJoinProductCustomer(): ResultWrapper<List<JoinProductCustomer>> {
        return when (val localData = localDatasource.getJoinProductCustomer()) {
            is ResultWrapper.Success -> ResultWrapper.Success(JoinProductCustomerMapper.map(localData.data))
            is ResultWrapper.Error -> ResultWrapper.Error(localData.exception)
        }
    }

    override suspend fun getProducts(): ResultWrapper<List<Product>> {
        return when (val localData = getLocalProducts()) {
            is ResultWrapper.Success -> localData
            is ResultWrapper.Error -> {
                when (val remoteData = getRemoteProducts()) {
                    is ResultWrapper.Success -> {
                        insertProductList(remoteData.data)
                        getLocalProducts()
                    }
                    is ResultWrapper.Error -> ResultWrapper.Error(remoteData.exception)
                }
            }
        }
    }

    override suspend fun getProductById(productId: Int): ResultWrapper<Product> =
        when (val localData = localDatasource.getProductById(productId)) {
            is ResultWrapper.Success -> ResultWrapper.Success(ProductMapper.fromEntityToProduct(localData.data))
            is ResultWrapper.Error -> ResultWrapper.Error(localData.exception)
        }

    override suspend fun insertSingleProduct(product: Product): ResultWrapper<Boolean> {
        return when (val localData = localDatasource.insertProduct(ProductMapper.fromProductToEntity(product))) {
            is ResultWrapper.Success -> ResultWrapper.Success(localData.data)
            is ResultWrapper.Error -> ResultWrapper.Error(localData.exception)
        }
    }

    private suspend fun insertProductList(data: List<ProductEntity>) =
        data.map { localDatasource.insertProduct(it) }

    private suspend fun getLocalProducts(): ResultWrapper<List<Product>> =
        when (val localData = localDatasource.getProducts()) {
            is ResultWrapper.Success -> ResultWrapper.Success(ProductMapper.fromEntityToProduct(localData.data))
            is ResultWrapper.Error -> ResultWrapper.Error(localData.exception)
        }


    private suspend fun getRemoteProducts(): ResultWrapper<List<ProductEntity>> =
        when (val remoteData = remoteDataSource.getProducts()) {
            is ResultWrapper.Success -> ResultWrapper.Success(ProductMapper.fromDtoToEntity(remoteData.data))
            is ResultWrapper.Error -> ResultWrapper.Error(remoteData.exception)
        }
}