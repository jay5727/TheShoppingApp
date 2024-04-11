package com.jay.theshoppingapp.di

import android.content.Context
import com.jay.theshoppingapp.core.network.NetworkObserver
import com.jay.theshoppingapp.core.network.NetworkObserverImpl
import com.jay.theshoppingapp.productscreen.repository.ProductRepository
import com.jay.theshoppingapp.productscreen.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        retrofit: Retrofit
        // @ApplicationContext context: Context
    ): ProductRepository {
        return ProductRepositoryImpl(
            retrofit,
            // NetworkObserverImpl(context)
        )
    }

    @Provides
    @Singleton
    fun provideNetworkObserverState(@ApplicationContext context: Context): NetworkObserver {
        return NetworkObserverImpl(context)
    }
}