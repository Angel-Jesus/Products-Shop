package com.angelpr.productsshop.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.domain.GetProductsCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getProductsCase: GetProductsCase
): ViewModel() {

    private val _stateProducts = MutableStateFlow(UiStateProducts())
    val stateProducts = _stateProducts.asStateFlow()

    fun getProducts(){
        viewModelScope.launch {
            _stateProducts.update { it.copy(isLoading = true) }
            val products = getProductsCase()
            _stateProducts.update { it.copy(products = products, isLoading = false) }
            Log.d("ProductsLog", products.toString())
        }
    }


    data class UiStateProducts(
        val products: List<Product> = emptyList(),
        val isLoading: Boolean = false
    )

}