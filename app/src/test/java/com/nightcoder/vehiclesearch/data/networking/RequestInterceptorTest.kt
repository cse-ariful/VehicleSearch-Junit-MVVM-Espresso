package com.nightcoder.vehiclesearch.data.networking

import okhttp3.Request
import okhttp3.internal.http.HttpHeaders
import okhttp3.internal.http.HttpMethod
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException


@RunWith(JUnit4::class)
class RequestInterceptorTest{
    private lateinit var request: Request
    private lateinit var interceptor: RequestInterceptor


    @Before
    fun setup(){

    }
    @Test()
    fun  `Test if the api-key is added in the request`(){

    }

}

