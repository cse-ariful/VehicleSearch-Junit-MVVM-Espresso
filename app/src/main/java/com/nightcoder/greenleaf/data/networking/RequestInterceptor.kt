package com.nightcoder.greenleaf.data.networking

import com.nightcoder.greenleaf.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("x-api-key", BuildConfig.SEARCH_API_KEY)
            .build()

        return chain.proceed(request)
    }
}