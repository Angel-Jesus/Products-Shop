package com.angelpr.productsshop.presentation.view

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.presentation.components.LoadingIndicator
import com.angelpr.productsshop.presentation.view.model.FilterBy
import com.angelpr.productsshop.presentation.viewmodel.ProductsViewModel
import com.angelpr.productsshop.ui.theme.TopBarColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitScreen(
    productsViewModel: ProductsViewModel = koinViewModel<ProductsViewModel>(),
    onClickProduct: (Product) -> Unit
) {
    val uiStateProducts by productsViewModel.stateProducts.collectAsState()

    if (uiStateProducts.isLoading) {
        LoadingIndicator()
    }

    Scaffold(
        topBar = {
            TopBar(
                onAscending = { productsViewModel.getProducts(FilterBy.Ascending) },
                onDescending = { productsViewModel.getProducts(FilterBy.Descending) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(uiStateProducts.products) { product ->
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
) {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onAscending: () -> Unit,
    onDescending : () -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Lista de Productos") },
        actions = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "MoreVert",
                    tint = Color.Black
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Ascendente") },
                    onClick = {
                        expanded = false
                        onAscending()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Descendente") },
                    onClick = {
                        expanded = false
                        onDescending()
                    }
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = TopBarColor,
            scrolledContainerColor = TopBarColor,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = TopBarColor
        )
    )
}
