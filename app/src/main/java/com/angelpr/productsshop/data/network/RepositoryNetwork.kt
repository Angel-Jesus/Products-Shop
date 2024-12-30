package com.angelpr.productsshop.data.network

import com.angelpr.productsshop.data.model.Product
import retrofit2.Retrofit

class RepositoryNetwork(
    private val retrofit: Retrofit
){
    suspend fun getProducts(): List<Product> {
        try {
            val response = retrofit.create(ProductsApiClient::class.java).getProducts()
            return response.body() ?: emptyList()
        }
        catch (e: Exception){
            return emptyList()
        }
    }
}