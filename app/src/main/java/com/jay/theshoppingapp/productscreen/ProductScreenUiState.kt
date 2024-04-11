package com.jay.theshoppingapp.productscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jay.theshoppingapp.model.Product

data class ProductScreenUiState(
    val title: String = "",
    val loading: Boolean = false,
    val error: Exception? = null,
    var products: SnapshotStateList<Product> = mutableStateListOf()
    //var products: List<Product> = mutableStateListOf()
)
