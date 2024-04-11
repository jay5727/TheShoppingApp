package com.jay.theshoppingapp.productscreen

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jay.theshoppingapp.model.Product

data class ProductScreenUiState(
    val title: String = "",
    val loading: Boolean = false,
    val error: Error? = null,
    val products: ImmutableList<Product> = ImmutableList(emptyList())
)

@Immutable
data class ImmutableList<T>(
    val items: List<T>
)
data class Error(val message:String)
