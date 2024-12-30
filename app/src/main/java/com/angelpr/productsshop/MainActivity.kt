package com.angelpr.productsshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.angelpr.productsshop.presentation.view.navigator.NavigationManager
import com.angelpr.productsshop.ui.theme.ProductsShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsShopTheme {
                NavigationManager()
            }
        }
    }
}

