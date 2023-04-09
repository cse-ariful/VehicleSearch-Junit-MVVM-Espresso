package com.nightcoder.vehiclesearch.domain.service

import com.nightcoder.vehiclesearch.data.model.VehicleInfoDataModel
import com.nightcoder.vehiclesearch.data.state.ApiResult

interface SearchVehicleService {
   suspend fun queryVehicleInfo(regNo: String): ApiResult<VehicleInfoDataModel>
}


