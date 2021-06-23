package com.will.bumblebeer.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/***
 * Class to represent the product entity in local database.
 */
@Entity(
    tableName = "product",
    foreignKeys = [ForeignKey(
        entity = CustomerEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("customer_id"),
        onDelete = CASCADE
    )]
)
class ProductEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "customer_id")
    val customerId: Int
)