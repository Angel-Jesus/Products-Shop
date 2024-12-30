package com.angelpr.productsshop.data.model

data class Product(
    val title: String,
    val price: Int,
    val description: String,
    var images: List<String>,
    val category: Category
)