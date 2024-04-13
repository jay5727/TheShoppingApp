package com.jay.theshoppingapp.di.network

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.jay.theshoppingapp.BuildConfig
import com.jay.theshoppingapp.core.interceptors.ConnectivityInterceptor
import com.jay.theshoppingapp.core.interceptors.NetworkLoggerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class NetworkModuleDI {

    @Provides
    @Singleton
    fun provideClientConfig(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkLoggerInterceptor())
            .addInterceptor(ConnectivityInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, t ->
            run {
                Log.e("Api Error: ", "Exception ${t.message}")
                t.printStackTrace()
            }
        }
        return Dispatchers.IO + coroutineExceptionHandler
    }

}