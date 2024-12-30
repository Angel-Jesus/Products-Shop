package com.angelpr.productsshop.data.model

data class Product(
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val category: Category
)