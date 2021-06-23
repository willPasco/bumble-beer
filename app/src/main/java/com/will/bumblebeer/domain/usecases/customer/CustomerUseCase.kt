package com.will.bumblebeer.domain.usecases.customer

import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.Customer

interface CustomerUseCase {
    suspend fun getCustomers(): ResultWrapper<List<Customer>>
}