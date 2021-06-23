package com.will.bumblebeer.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.will.bumblebeer.data.local.dao.CustomerDao
import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.data.local.exceptions.CustomersNotFoundException
import com.will.bumblebeer.infraestructure.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerLocalDataSourceImpl(
    private val customerDao: CustomerDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CustomerLocalDataSource {

    override suspend fun insertCustomer(customerEntity: CustomerEntity) = withContext(dispatcher) {
        customerDao.insertCustomer(customerEntity)
    }

    override suspend fun getCustomers(): ResultWrapper<List<CustomerEntity>> =
        withContext(dispatcher) {
            val listCustomer = customerDao.getCustomers()

            if (listCustomer.isNotEmpty()) {
                return@withContext ResultWrapper.Success(listCustomer)
            } else {
                return@withContext ResultWrapper.Error(CustomersNotFoundException())
            }
        }

    override fun observerAllCustomers(): LiveData<ResultWrapper<List<CustomerEntity>>> {
        return customerDao.observeAllCustomers().map {
            ResultWrapper.Success(it)
        }
    }
}