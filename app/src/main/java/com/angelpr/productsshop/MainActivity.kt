package com.angelpr.productsshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angelpr.productsshop.presentation.viewmodel.ProductsViewModel
import com.angelpr.productsshop.ui.theme.ProductsShopTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val productsViewModel = koinViewModel<ProductsViewModel>()
            ProductsShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item{
                            Button(
                                onClick = {
                                    productsViewModel.getProducts()
                                }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(start = 10.dp, end = 10.dp, bottom = 4.dp, top = 4.dp),
                                    text = "Click"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

