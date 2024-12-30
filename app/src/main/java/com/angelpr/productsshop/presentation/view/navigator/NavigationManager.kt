package com.angelpr.productsshop.presentation.view.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.angelpr.productsshop.data.model.Category
import com.angelpr.productsshop.data.model.Product
import com.angelpr.productsshop.presentation.view.InitScreen
import com.angelpr.productsshop.presentation.view.ProductDescriptionScreen

@Composable
fun NavigationManager() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ItemsNavScreen.Init
    ) {
        composable<ItemsNavScreen.Init> {

            InitScreen(
                onClickProduct = {
                    navController.navigate(
                        ItemsNavScreen.ProductDescription(
                            title = it.title,
                            price = it.price,
                            description = it.description,
                            images = it.images.joinToString("|"),
                            categoryName = it.category.name,
                            categoryImage = it.category.image
                        )
                    )
                }
            )
        }

        composable<ItemsNavScreen.ProductDescription> {
            val arg = it.toRoute<ItemsNavScreen.ProductDescription>()

            val productSelected = Product(
                title = arg.title,
                price = arg.price,
                description = arg.description,
                images = arg.images.split("|"),
                category = Category(
                    name = arg.categoryName,
                    image = arg.categoryImage
                )
            )

            ProductDescriptionScreen(
                product = productSelected,
                onBack = { navController.popBackStack() }
            )
        }
    }
}