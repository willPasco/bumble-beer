package com.will.bumblebeer.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Class to represent the customer entity in local database.
 */
@Entity(tableName = "customer")
class CustomerEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)