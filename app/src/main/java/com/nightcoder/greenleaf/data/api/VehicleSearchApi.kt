package com.nightcoder.greenleaf.data.api

import com.nightcoder.greenleaf.data.model.VehicleInfoDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface VehicleSearchApi {

    @GET("vehicle")
    fun queryVehicleInfo(@Query("registration") regNo: String): Call<VehicleInfoDataModel>

}
