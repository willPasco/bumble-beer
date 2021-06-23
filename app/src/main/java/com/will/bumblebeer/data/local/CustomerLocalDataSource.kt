package com.will.bumblebeer.data.local

import androidx.lifecycle.LiveData
import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.infraestructure.ResultWrapper

/**
 * Interface to connect with the customer entity in local database.
 */
interface CustomerLocalDataSource {
    suspend fun insertCustomer(customerEntity: CustomerEntity)
    suspend fun getCustomers(): ResultWrapper<List<CustomerEntity>>
    fun observerAllCustomers(): LiveData<ResultWrapper<List<CustomerEntity>>>
}