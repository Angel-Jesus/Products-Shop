package com.angelpr.productsshop.data

import com.angelpr.productsshop.data.db.dao.ProductsDao
import com.angelpr.productsshop.data.db.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

class RepositoryDatabase(
    private val productsDao: ProductsDao
) {
    fun getProducts(): Flow<List<ProductEntity>> = productsDao.getProducts()

    suspend fun insertProduct(product: List<ProductEntity>) = productsDao.insertProducts(product)
}