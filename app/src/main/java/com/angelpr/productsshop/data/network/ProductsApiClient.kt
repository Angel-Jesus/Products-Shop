package com.angelpr.productsshop.data.network

import com.angelpr.productsshop.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApiClient {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}