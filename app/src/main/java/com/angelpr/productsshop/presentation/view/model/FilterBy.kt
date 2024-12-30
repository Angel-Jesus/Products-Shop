package com.angelpr.productsshop.presentation.view.model

sealed class FilterBy {
    data class Name(val name: String) : FilterBy()
    data object Ascending : FilterBy()
    data object Descending : FilterBy()
}