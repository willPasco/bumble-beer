package com.will.bumblebeer.data.local.mappers

import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.data.remote.dto.CustomerDTO
import com.will.bumblebeer.domain.entities.Customer

object CustomerMapper {
    fun fromEntityToCustomer(data: List<CustomerEntity>): List<Customer> =
        data.map { customer ->
            Customer(
                id = customer.id,
                name = customer.name
            )
        }

    fun fromDtoToEntity(data: List<CustomerDTO>): List<CustomerEntity> =
        data.map { customer ->
            CustomerEntity(
                id = customer.id,
                name = customer.name
            )
        }
}
