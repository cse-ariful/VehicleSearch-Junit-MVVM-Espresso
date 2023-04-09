package com.nightcoder.vehiclesearch.data.model

import com.google.gson.annotations.SerializedName
import com.nightcoder.vehiclesearch.domain.model.VehicleInfoModel

data class VehicleInfoDataModel(
    @SerializedName("make")
    val make: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("details")
    val details: String,
    @SerializedName("engine")
    val engine: String,
    @SerializedName("gearbox")
    val gearbox: String,
    @SerializedName("bodyType")
    val bodyType: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("motExpiry")
    val motExpiry: String
)

fun VehicleInfoDataModel.toDomainModel(): VehicleInfoModel {
    return VehicleInfoModel(
        make = this.make,
        model = this.model,
        details = this.details,
        engine = this.engine,
        gearbox = this.gearbox,
        bodyType = this.bodyType,
        year = this.year,
        motExpiry = this.motExpiry
    )
}