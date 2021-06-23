package com.will.bumblebeer.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.will.bumblebeer.data.local.entities.CustomerEntity

/***
 * Data Access Object for customer tables
 */
@Dao
interface CustomerDao {

    /***
     * Method to insert a customer entity in the local database
     */
    @Insert
    suspend fun insertCustomer(customerEntity: CustomerEntity)

    /***
     * Method to retrieve with all customers in local database.
     */
    @Query("SELECT * FROM customer")
    suspend fun getCustomers(): List<CustomerEntity>

    /***
     * Method to retrieve with live data all products in local database.
     */
    @Query("SELECT * FROM customer")
    fun observeAllCustomers(): LiveData<List<CustomerEntity>>
}