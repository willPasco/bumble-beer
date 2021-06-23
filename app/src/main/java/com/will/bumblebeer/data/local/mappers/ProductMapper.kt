package com.will.bumblebeer.data.local.mappers

import com.will.bumblebeer.data.local.entities.ProductEntity
import com.will.bumblebeer.data.remote.dto.ProductDTO
import com.will.bumblebeer.domain.entities.Product

object ProductMapper {

    fun fromEntityToProduct(data: List<ProductEntity>): List<Product> =
        data.map { product ->
            Product(
                id = product.id,
                description = product.description,
                customerId = product.customerId
            )
        }

    fun fromEntityToProduct(data: ProductEntity): Product =
        Product(
            id = data.id,
            description = data.description,
            customerId = data.customerId
        )

    fun fromProductToEntity(data: Product): ProductEntity =
        ProductEntity(
            id = data.id,
            description = data.description,
            customerId = data.customerId
        )

    fun fromDtoToEntity(data: List<ProductDTO>): List<ProductEntity> =
        data.map { product ->
            ProductEntity(
                id = product.id,
                description = product.description,
                customerId = product.customerId
            )
        }
}