package com.jay.theshoppingapp.productscreen.repository

import android.util.Log.DEBUG
import com.jay.theshoppingapp.core.inteceptors.ConnectivityInterceptor
import com.jay.theshoppingapp.core.logger.AppLoggerImpl
import com.jay.theshoppingapp.core.result.Result
import com.jay.theshoppingapp.model.Products
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    retrofit: Retrofit
) : ProductRepository {

    private val client: DummyJsonApiClient = retrofit.create(DummyJsonApiClient::class.java)

    override suspend fun fetchProducts() = flow {
        try {
            emit(Result.Loading)
            val result = client.fetchProducts()
            AppLoggerImpl.log(DEBUG, this::class.java.simpleName, result.toString())
            emit(Result.Success(result.products))
        } catch (ex: Exception) {
            if (ex is ConnectivityInterceptor.NoConnectivityException) {
                emit(Result.NoInternet)
            } else {
                emit(Result.Error(ex))
            }
        }
    }
}

interface DummyJsonApiClient {
    @GET("products")
    suspend fun fetchProducts(): Products
}

