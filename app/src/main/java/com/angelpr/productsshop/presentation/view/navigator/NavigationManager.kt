package com.angelpr.productsshop.presentation.view.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelpr.productsshop.presentation.view.InitScreen
import com.angelpr.productsshop.presentation.view.ProductDescriptionScreen

@Composable
fun NavigationManager(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ItemsNavScreen.Init
    ){
        composable<ItemsNavScreen.Init>{
            InitScreen()
        }

        composable<ItemsNavScreen.ProductDescription> {
            ProductDescriptionScreen()
        }
    }
}