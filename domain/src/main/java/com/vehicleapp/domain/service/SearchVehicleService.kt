package com.vehicleapp.domain.service

import com.vehicleapp.domain.model.VehicleInfoModel
import com.vehicleapp.domain.state.ApiResult


interface SearchVehicleService {
   suspend fun queryVehicleInfo(regNo: String): ApiResult<VehicleInfoModel>
}


