package com.angelpr.productsshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelpr.productsshop.data.db.dao.ProductsDao
import com.angelpr.productsshop.data.db.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductsDatabase: RoomDatabase() {
    abstract fun getProductsDao(): ProductsDao
}