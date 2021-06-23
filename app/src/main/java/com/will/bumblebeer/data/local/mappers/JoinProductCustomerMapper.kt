package com.will.bumblebeer.data.local.mappers

import com.will.bumblebeer.data.local.entities.JoinProductCustomerEntity
import com.will.bumblebeer.domain.entities.JoinProductCustomer

object JoinProductCustomerMapper {
    fun map(data: List<JoinProductCustomerEntity>): List<JoinProductCustomer> =
        data.map { map(it) }

    private fun map(data: JoinProductCustomerEntity): JoinProductCustomer =
        JoinProductCustomer(
            productDescription = data.productDescription,
            customerName = data.customerName
        )
}