package com.angelpr.productsshop.domain

import com.angelpr.productsshop.data.RepositoryDatabase
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.data.RepositoryNetwork

class GetProductsCase(
    private val repositoryNetwork: RepositoryNetwork,
    private val repositoryDatabase: RepositoryDatabase
) {
    suspend operator fun invoke(): List<Product> {
        return repositoryNetwork.getProducts()
    }
}