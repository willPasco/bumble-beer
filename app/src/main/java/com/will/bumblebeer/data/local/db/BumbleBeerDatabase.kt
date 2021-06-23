package com.will.bumblebeer.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.will.bumblebeer.data.local.dao.CustomerDao
import com.will.bumblebeer.data.local.dao.ProductDao
import com.will.bumblebeer.data.local.entities.CustomerEntity
import com.will.bumblebeer.data.local.entities.ProductEntity

@Database(
    entities = [ProductEntity::class, CustomerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BumbleBeerDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    abstract fun getCustomerDao(): CustomerDao

    companion object {
        private const val DATABASE_NAME = "beer_database"

        fun createDatabase(context: Context): BumbleBeerDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                BumbleBeerDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}