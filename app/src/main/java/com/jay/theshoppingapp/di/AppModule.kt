package com.jay.theshoppingapp.di

import com.jay.theshoppingapp.productscreen.repository.ProductRepository
import com.jay.theshoppingapp.productscreen.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    ): ProductRepository {
        return ProductRepositoryImpl(
            retrofit
        )
    }

}