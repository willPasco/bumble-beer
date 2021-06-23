package com.will.bumblebeer.data.local.entities

/**
 * Database view (result from a join between Product and Customer)
 */
class JoinProductCustomerEntity(
    val productDescription: String,
    val customerName: String
)