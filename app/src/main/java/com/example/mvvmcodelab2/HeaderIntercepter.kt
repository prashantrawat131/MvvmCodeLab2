package com.example.mvvmcodelab2

import okhttp3.Interceptor
import okhttp3.Response

class HeaderIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
            .newBuilder()
            .addHeader("deviceplatform", "android")
            .build()

        val response = chain.proceed(request)
        return response
    }
}