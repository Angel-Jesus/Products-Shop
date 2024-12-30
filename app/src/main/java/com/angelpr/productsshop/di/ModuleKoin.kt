package com.angelpr.productsshop.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.angelpr.productsshop.data.db.ProductsDatabase
import com.angelpr.productsshop.data.RepositoryDatabase
import com.angelpr.productsshop.data.db.dao.ProductsDao
import com.angelpr.productsshop.data.RepositoryNetwork
import com.angelpr.productsshop.domain.GetProductsCase
import com.angelpr.productsshop.presentation.viewmodel.ProductsViewModel
import org.koin.android.ext.koin.androidContext
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

val roomBuilderModule = module {
    single<ProductsDatabase>{
        Room.databaseBuilder(androidContext(), ProductsDatabase::class.java, "products_db")
            .build()
    }

    single<ProductsDao>{
        get<ProductsDatabase>().getProductsDao()
    }
}

val dataModule = module {
    singleOf(::RepositoryDatabase)
    singleOf(::RepositoryNetwork)
    singleOf(::GetProductsCase)
}

val viewModelModule = module {
    viewModelOf(::ProductsViewModel)
}