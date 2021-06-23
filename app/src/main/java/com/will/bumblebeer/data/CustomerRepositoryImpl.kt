package com.will.bumblebeer.data

import com.will.bumblebeer.data.remote.CustomerRemoteDataSource
import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.data.local.mappers.CustomerMapper
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.data.local.CustomerLocalDataSource
import com.will.bumblebeer.domain.entities.Customer

class CustomerRepositoryImpl(
    private val localDataSource: CustomerLocalDataSource,
    private val remoteRemoteDataSource: CustomerRemoteDataSource
) : CustomerRepository {

    override suspend fun getCustomers(): ResultWrapper<List<Customer>> {
        return when (val localData = getLocalCustomers()) {
            is ResultWrapper.Success -> localData
            is ResultWrapper.Error -> {
                when (val remoteData = getRemoteCustomers()) {
                    is ResultWrapper.Success -> {
                        insertCustomers(CustomerMapper.fromDtoToEntity(remoteData.data))
                        getLocalCustomers()
                    }
                    is ResultWrapper.Error -> remoteData
                }
            }
        }
    }

    private suspend fun insertCustomers(data: List<CustomerEntity>) =
        data.map { localDataSource.insertCustomer(it) }

    private suspend fun getLocalCustomers() =
        when (val localData = localDataSource.getCustomers()) {
            is ResultWrapper.Success -> ResultWrapper.Success(CustomerMapper.fromEntityToCustomer(localData.data))
            is ResultWrapper.Error -> ResultWrapper.Error(localData.exception)
        }

    private suspend fun getRemoteCustomers() =
        when (val remoteData = remoteRemoteDataSource.getCustomers()) {
            is ResultWrapper.Success -> ResultWrapper.Success(remoteData.data)
            is ResultWrapper.Error -> ResultWrapper.Error(remoteData.exception)
        }
}