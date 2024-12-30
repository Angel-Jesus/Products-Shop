package com.angelpr.productsshop.presentation.view.navigator

import kotlinx.serialization.Serializable

sealed class ItemsNavScreen {
    @Serializable
    data object Init : ItemsNavScreen()
    @Serializable
    data class ProductDescription(
        val title: String,
        val price: Long,
        val description: String,
        val images: String,
        val categoryName: String,
        val categoryImage: String
    ): ItemsNavScreen()
}