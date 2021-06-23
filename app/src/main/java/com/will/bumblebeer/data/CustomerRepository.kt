package com.will.bumblebeer.data

import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.Customer

interface CustomerRepository {
    suspend fun getCustomers(): ResultWrapper<List<Customer>>
}