package com.vehicleapp.data.api

import com.vehicleapp.data.model.VehicleInfoDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface VehicleSearchApi {

    @GET("vehicle")
    fun queryVehicleInfo(@Query("registration") regNo: String): Call<VehicleInfoDataModel>

}
