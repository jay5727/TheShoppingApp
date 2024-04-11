package com.jay.theshoppingapp.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkObserverImpl(context: Context): NetworkObserver {
    private val connectivityManager: ConnectivityManager = context.getSystemService(ConnectivityManager::class.java)
    private val TAG = this::class.simpleName

    override fun connectionStatus(): Flow<Boolean> = callbackFlow{
        val networkCallback = object: ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.e(TAG, "onAvailable: available")
                trySend(true)
            }

            override fun onLost(network: Network) {
                Log.e(TAG, "onLost: lost", )
                trySend(false)
            }

            override fun onUnavailable() {
                trySend(false)

            }
        }
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().addCapability(NET_CAPABILITY_INTERNET)
                .addTransportType(TRANSPORT_WIFI)
                .addTransportType(TRANSPORT_CELLULAR)
                .build(),
            networkCallback
        )
        awaitClose { connectivityManager.unregisterNetworkCallback(networkCallback) }
    }
}
