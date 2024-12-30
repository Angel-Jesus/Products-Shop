package com.angelpr.productsshop.di

import com.angelpr.productsshop.data.network.RepositoryNetwork
import com.angelpr.productsshop.domain.GetProductsCase
import com.angelpr.productsshop.presentation.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitBuilderModule = module {
    single<Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val dataModule = module {
    singleOf(::RepositoryNetwork)
    singleOf(::GetProductsCase)
}

val viewModelModule = module {
    viewModelOf(::ProductsViewModel)
}