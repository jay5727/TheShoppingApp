package com.jay.theshoppingapp.productscreen.repository

import android.util.Log.DEBUG
import com.jay.theshoppingapp.core.logger.AppLoggerImpl
import com.jay.theshoppingapp.model.Products
import com.jay.theshoppingapp.core.result.Result
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    retrofit: Retrofit,
    //private val observer: NetworkObserver
) : ProductRepository {

    private val client: DummyJsonApiClient = retrofit.create(DummyJsonApiClient::class.java)

    override suspend fun fetchProducts() = flow {

        //observer.connectionStatus().collect {
            emit(Result.Loading)
            //if (it) {
                val result = client.fetchProducts()
                AppLoggerImpl.log(DEBUG, this::class.java.simpleName, result.toString())
                emit(Result.Success(result.products))
//            } else {
//                emit(Result.NoInternet)
//            }
       // }
    }
}

interface DummyJsonApiClient {
    @GET("products")
    suspend fun fetchProducts(): Products
}

