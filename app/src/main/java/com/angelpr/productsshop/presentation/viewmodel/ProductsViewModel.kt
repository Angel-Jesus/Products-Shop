package com.angelpr.productsshop.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelpr.productsshop.domain.GetProductsCase
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getProductsCase: GetProductsCase
): ViewModel() {
    fun getProducts(){
        viewModelScope.launch {
            val products = getProductsCase()
            Log.d("ProductsLog", products.toString())
        }
    }
}