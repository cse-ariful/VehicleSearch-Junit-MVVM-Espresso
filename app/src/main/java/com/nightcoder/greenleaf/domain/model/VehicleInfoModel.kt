package com.nightcoder.greenleaf.domain.model

data class VehicleInfoModel(
    val make: String,
    val model: String,
    val details: String,
    val engine: String,
    val gearbox: String,
    val bodyType: String,
    val year: String,
    val motExpiry: String
)