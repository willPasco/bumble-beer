package com.will.bumblebeer.data.remote

import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.data.remote.customer.CustomerApi
import com.will.bumblebeer.data.remote.dto.CustomerDTO
import com.will.bumblebeer.infraestructure.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CustomerRemoteDataSourceImpl(
    private val api: CustomerApi,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRemoteDataSource(dispatcher = dispatcher),
    CustomerRemoteDataSource {
    override suspend fun getCustomers(): ResultWrapper<List<CustomerDTO>> =
        serviceCall { api.getCustomers() }
}