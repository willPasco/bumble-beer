package com.will.bumblebeer.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.will.bumblebeer.data.local.dao.ProductDao
import com.will.bumblebeer.data.local.entities.JoinProductCustomerEntity
import com.will.bumblebeer.data.local.entities.ProductEntity
import com.will.bumblebeer.data.local.exceptions.ProductsNotFoundException
import com.will.bumblebeer.data.local.exceptions.ProductsNotSavedException
import com.will.bumblebeer.infraestructure.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductLocalDataSourceImpl(
    private val productDao: ProductDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductLocalDataSource {

    override suspend fun insertProduct(productEntity: ProductEntity) =
        withContext(dispatcher) {
            val insertResponse = productDao.insertProduct(productEntity)

            if (insertResponse.toInt() == productEntity.id) {
                return@withContext ResultWrapper.Success(true)
            } else {
                return@withContext ResultWrapper.Error(ProductsNotSavedException())
            }
        }

    override suspend fun getProducts(): ResultWrapper<List<ProductEntity>> =
        withContext(dispatcher) {
            val listProduct = productDao.getProducts()

            if (listProduct.isNotEmpty()) {
                return@withContext ResultWrapper.Success(listProduct)
            } else {
                return@withContext ResultWrapper.Error(ProductsNotFoundException())
            }
        }

    override suspend fun getJoinProductCustomer(): ResultWrapper<List<JoinProductCustomerEntity>> =
        withContext(dispatcher) {
            val listProduct = productDao.getJoinProductCustomer()

            return@withContext if (listProduct.isNotEmpty()) {
                ResultWrapper.Success(listProduct)
            } else {
                ResultWrapper.Error(ProductsNotFoundException())
            }
        }

    override suspend fun getProductById(productId: Int): ResultWrapper<ProductEntity> =
        withContext(dispatcher) {
            val data = productDao.getProductById(productId)

            return@withContext if (data != null) {
                ResultWrapper.Success(data)
            } else {
                ResultWrapper.Error(ProductsNotFoundException())
            }

        }

    override fun observerAllProducts(): LiveData<ResultWrapper<List<ProductEntity>>> {
        return productDao.observeAllProducts().map {
            ResultWrapper.Success(it)
        }
    }
}