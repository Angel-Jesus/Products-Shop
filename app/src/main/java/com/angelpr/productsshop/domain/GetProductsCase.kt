package com.angelpr.productsshop.domain

import com.angelpr.productsshop.data.RepositoryDatabase
import com.angelpr.productsshop.data.RepositoryNetwork
import com.angelpr.productsshop.data.db.entities.toProduct
import com.angelpr.productsshop.data.db.entities.toProductEntity
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.presentation.view.model.FilterBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductsCase(
    private val repositoryNetwork: RepositoryNetwork,
    private val repositoryDatabase: RepositoryDatabase
) {
    fun get(filter: FilterBy = FilterBy.Ascending): Flow<List<Product>> {
        return repositoryDatabase.getProducts().map { productEntity ->
            // Insert data from Api to Database
            if(productEntity.isEmpty()){
                val productsFromApi = repositoryNetwork.getProducts()
                repositoryDatabase.insertProduct(productsFromApi.map { it.toProductEntity() })
            }

            when(filter){
                is FilterBy.Ascending -> productEntity.sortedBy { it.price }.map { it.toProduct() }
                is FilterBy.Descending -> productEntity.sortedByDescending { it.price }.map { it.toProduct() }
                is FilterBy.Name -> productEntity.filter { it.title.startsWith(filter.name, ignoreCase = true) }.map { it.toProduct() }
            }

        }
    }

}