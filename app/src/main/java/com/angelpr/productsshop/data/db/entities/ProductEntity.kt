package com.angelpr.productsshop.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angelpr.productsshop.data.model.Category
import com.angelpr.productsshop.data.model.Product

@Entity(tableName = "products_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "price")
    val price: Long,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "images")
    val images: String,
    @ColumnInfo(name = "categoryName")
    val categoryName:String,
    @ColumnInfo(name = "categoryImage")
    val categoryImage:String
)

fun ProductEntity.toProduct() = Product(
    title = title,
    price = price,
    description = description,
    images = images.split("|"),
    category = Category(
        name = categoryName,
        image = categoryImage
    )
)

fun Product.toProductEntity() = ProductEntity(
    title = title,
    price = price,
    description = description,
    images = images.joinToString(separator = "|"),
    categoryName = category.name,
    categoryImage = category.image
)

