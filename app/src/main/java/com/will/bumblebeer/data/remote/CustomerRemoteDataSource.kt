package com.will.bumblebeer.data.remote

import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.data.remote.dto.CustomerDTO
import com.will.bumblebeer.infraestructure.ResultWrapper

/**
 * Interface to connect with the customer entity in local database.
 */
interface CustomerRemoteDataSource {
    suspend fun getCustomers(): ResultWrapper<List<CustomerDTO>>
}