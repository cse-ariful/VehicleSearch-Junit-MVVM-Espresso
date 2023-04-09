package com.nightcoder.vehiclesearch.domain.model

data class VehicleFeatureItem(
    val title: String,
    val value: String,
    val isHighlighted: Boolean = false
)