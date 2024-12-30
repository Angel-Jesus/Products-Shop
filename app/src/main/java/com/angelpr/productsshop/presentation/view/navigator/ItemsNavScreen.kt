package com.angelpr.productsshop.presentation.view.navigator

import kotlinx.serialization.Serializable

sealed class ItemsNavScreen {
    @Serializable
    data object Init : ItemsNavScreen()
    @Serializable
    data object ProductDescription: ItemsNavScreen()
}