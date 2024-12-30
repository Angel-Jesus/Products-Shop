package com.angelpr.productsshop.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.angelpr.productsshop.data.model.Category
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.presentation.components.LoadingIndicator
import com.angelpr.productsshop.presentation.viewmodel.ProductsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitScreen(
    productsViewModel: ProductsViewModel = koinViewModel<ProductsViewModel>()
){
    val uiStateProducts by productsViewModel.stateProducts.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getProducts()
    }

    if(uiStateProducts.isLoading){
        LoadingIndicator()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(uiStateProducts.products){ product ->
                ItemCardView(product)
            }
        }
    }
}

@Composable
private fun ItemCardView(product: Product){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Row {
            Text(
                modifier = Modifier
                    .width(100.dp),
                text = product.category.image
            )

            Column {
                Text(
                    text = product.title
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.price.toString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        ItemCardView(
            Product(
                title = "Modern Ergonomic Office Chair",
                price = 30,
                description = "Esto es una prueba",
                images = listOf("url1", "url2"),
                category = Category(name = "electronic", "https://i.imgur.com/Qphac99.jpeg")
            )
        )
    }
}