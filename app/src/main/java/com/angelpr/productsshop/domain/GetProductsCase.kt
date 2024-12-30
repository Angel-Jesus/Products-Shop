package com.angelpr.productsshop.domain

import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.data.network.RepositoryNetwork

class GetProductsCase(
    private val repositoryNetwork: RepositoryNetwork
) {
    suspend operator fun invoke(): List<Product> = repositoryNetwork.getProducts()
}