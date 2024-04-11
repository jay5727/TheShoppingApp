package com.jay.theshoppingapp.model

import com.google.gson.annotations.SerializedName

data class Products(
    val products: List<Product> = listOf()
)

data class Product(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("price")
    var price: Int = 0,
    @SerializedName("brand")
    var brand: String? = null,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("images")
    var images: List<String> = emptyList()
)