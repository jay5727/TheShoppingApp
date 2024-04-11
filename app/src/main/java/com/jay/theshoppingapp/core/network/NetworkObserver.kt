package com.jay.theshoppingapp.core.network

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun connectionStatus(): Flow<Boolean>
}
