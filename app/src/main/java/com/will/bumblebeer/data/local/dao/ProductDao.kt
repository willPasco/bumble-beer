package com.will.bumblebeer.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.will.bumblebeer.data.local.entities.JoinProductCustomerEntity
import com.will.bumblebeer.data.local.entities.ProductEntity

/***
 * Data Access Object for product tables
 */
@Dao
interface ProductDao {

    /***
     * Method to insert a product entity in the local database
     *
     * @param productEntity A class that represent the product in local database.
     * @return Long The id of the record that was inserted.
     */
    @Insert
    suspend fun insertProduct(productEntity: ProductEntity): Long

    /***
     * Method to retrieve all products in local database.
     */
    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<ProductEntity>

    /***
     * Method to retrieve with live data all products in local database.
     */
    @Query("SELECT * FROM product")
    fun observeAllProducts(): LiveData<List<ProductEntity>>

    /**
     * Method to join customerId
     */
    @Query("SELECT product.description as productDescription, customer.name as customerName FROM product LEFT JOIN customer ON product.customer_id = customer.id")
    fun getJoinProductCustomer(): List<JoinProductCustomerEntity>

    /**
     * Method to return the product based in id.
     *
     * @param productId Id of the product.
     * @return ProductEntity (can be a null or not)
     */
    @Query("SELECT * FROM product WHERE id == :productId")
    fun getProductById(productId: Int): ProductEntity?
}