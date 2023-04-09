package com.nightcoder.vehiclesearch.data.api

import com.nightcoder.vehiclesearch.data.model.VehicleInfoDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface VehicleSearchApi {

    @GET("vehicle")
    fun queryVehicleInfo(@Query("registration") regNo: String): Call<VehicleInfoDataModel>

}
