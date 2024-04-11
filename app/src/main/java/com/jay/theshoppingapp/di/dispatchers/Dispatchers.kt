package com.jay.theshoppingapp.di.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class Dispatchers {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    @Provides
    @Singleton
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineContext = (Dispatchers.Default + coroutineExceptionHandler)

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher():CoroutineContext = (Dispatchers.IO + coroutineExceptionHandler)

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher():CoroutineContext = (Dispatchers.Main + coroutineExceptionHandler)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DefaultDispatcher

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IODispatcher

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainDispatcher

}
