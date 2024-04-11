package com.jay.theshoppingapp.productscreen.repository

import com.jay.theshoppingapp.model.Product
import kotlinx.coroutines.flow.Flow
import com.jay.theshoppingapp.core.result.Result

interface ProductRepository {
    suspend fun fetchProducts(): Flow<Result<out List<Product>>>
}