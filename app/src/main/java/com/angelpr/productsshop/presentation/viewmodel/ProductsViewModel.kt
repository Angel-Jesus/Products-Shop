package com.angelpr.productsshop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.domain.GetProductsCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ProductsViewModel(
    private val getProductsCase: GetProductsCase
): ViewModel() {

    private val _stateProducts = MutableStateFlow(UiStateProducts())
    val stateProducts = _stateProducts.asStateFlow()

    private var job: Job? = null

    init {
        _stateProducts.update { it.copy(isLoading = true) }
        getProducts()
    }

    private fun getProducts(){
        job?.cancel()
        job = getProductsCase.get()
            .onEach { products ->
                _stateProducts.update { it.copy(
                    products = products,
                    isLoading = false
                ) }
            }
            .launchIn(viewModelScope)
    }

    data class UiStateProducts(
        val products: List<Product> = emptyList(),
        val isLoading: Boolean = true
    )

}