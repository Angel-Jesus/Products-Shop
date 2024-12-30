package com.angelpr.productsshop.data

import com.angelpr.productsshop.data.db.dao.ProductsDao
import com.angelpr.productsshop.data.db.entities.ProductEntity

class RepositoryDatabase(
    private val productsDao: ProductsDao
) {
    fun getProducts() = productsDao.getProducts()

    suspend fun insertProduct(product: ProductEntity) = productsDao.insertProducts(product)
}