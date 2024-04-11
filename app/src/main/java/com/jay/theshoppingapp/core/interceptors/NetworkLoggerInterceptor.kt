package com.jay.theshoppingapp.core.interceptors

import android.util.Log
import com.jay.theshoppingapp.core.logger.AppLoggerImpl.log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class NetworkLoggerInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        log(Log.ERROR, this::class.java.simpleName, requestDetails(request))
        val response = chain.proceed(request)
        log(Log.ERROR, this::class.java.simpleName, responseDetails(response))
        return response
    }

    private fun requestDetails(request: Request): String {
        val endPoint = request.url.toUrl().toString().replace("${request.url.scheme}://${request.url.host}", "")
        return buildString {
            append("Request Details: ")
            append("Endpoint: $endPoint\n")
            append("Method: ${request.method}\n")
        }
    }

    private fun responseDetails(response: Response): String {
        return buildString {
            append("Response Details: ")
            append("isSuccess: ${response.isSuccessful}\n")
            append("code: ${response.code}\n")
            append("body: ${JSONObject(response.peekBody(1000000).string())}\n")
            append("message: ${response.message}\n")
            append("Request completed in Sec: ${TimeUnit.MILLISECONDS.toSeconds(
                (response.receivedResponseAtMillis - response.sentRequestAtMillis)
            )}")
        }
    }
}