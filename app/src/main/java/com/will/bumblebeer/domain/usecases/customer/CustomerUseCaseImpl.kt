package com.will.bumblebeer.domain.usecases.customer

import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.data.CustomerRepository
import com.will.bumblebeer.domain.entities.Customer

class CustomerUseCaseImpl(private val repository: CustomerRepository) : CustomerUseCase {
    override suspend fun getCustomers(): ResultWrapper<List<Customer>> =
        repository.getCustomers()
}