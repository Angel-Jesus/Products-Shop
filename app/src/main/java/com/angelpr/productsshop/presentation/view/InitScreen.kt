package com.angelpr.productsshop.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.presentation.components.LoadingIndicator
import com.angelpr.productsshop.presentation.viewmodel.ProductsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitScreen(
    productsViewModel: ProductsViewModel = koinViewModel<ProductsViewModel>(),
    onClickProduct: (Product) -> Unit
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

            item{
                Text(
                    text = "Lista de productos disponibles",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                )
            }

            items(uiStateProducts.products){ product ->
                ItemCardView(
                    product = product,
                    onClickItem = {
                        onClickProduct(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun ItemCardView(
    product: Product,
    onClickItem: (Product) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onClickItem(product)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                model = product.images[0],
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Precio: S/.${product.price} "
                )
            }
        }
    }
}