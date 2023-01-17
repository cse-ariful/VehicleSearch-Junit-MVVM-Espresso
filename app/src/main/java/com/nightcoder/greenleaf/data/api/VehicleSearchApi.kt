package com.nightcoder.greenleaf.data.api

import com.nightcoder.greenleaf.BuildConfig
import com.nightcoder.greenleaf.data.model.VehicleInfoDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface VehicleSearchApi {
    @GET("vehicle")
    fun queryVehicleInfo(@Query("registration") regNo: String,@Header("x-api-key") apiKey:String): Call<VehicleInfoDataModel>

}
