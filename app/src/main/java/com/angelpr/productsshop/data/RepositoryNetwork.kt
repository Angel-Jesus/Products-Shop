package com.angelpr.productsshop.data

import android.util.Log
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.data.network.ProductsApiClient
import retrofit2.Retrofit

class RepositoryNetwork(
    private val retrofit: Retrofit
){
    suspend fun getProducts(): List<Product> {
        try {
            val response = retrofit.create(ProductsApiClient::class.java).getProducts()
            val rawProducts = response.body() ?: emptyList()
            Log.d("Network", "Products: $rawProducts")

            if(rawProducts.isEmpty()) return emptyList()
            val products = fixImageUrlsInProducts(rawProducts)

            return products
        }
        catch (e: Exception){
            Log.d("Network", e.message.toString())
            return emptyList()
        }
    }

    private fun fixImageUrlsInProducts(products: List<Product>): List<Product>{
        val fixedProducts = products.toMutableList()

        for(index in products.indices){
            val rawImageList = fixedProducts[index].images
            fixedProducts[index].images = getUrlsFromList(rawImageList)
        }

        return fixedProducts
    }

    private fun getUrlsFromList(images: List<String>): List<String>{
        if(images.isEmpty()) return emptyList()
        // Extract url in string from rawString
        val regex = """https?://[^\s"']+""".toRegex()

        return images.map { regex.find(it)?.value.toString() }

    }
}