package com.nightcoder.greenleaf.data.vehicleSearch

import com.nightcoder.greenleaf.data.model.VehicleInfoDataModel
import com.nightcoder.greenleaf.data.state.ApiResult

interface SearchVehicleService {
   suspend fun queryVehicleInfo(regNo: String): ApiResult<VehicleInfoDataModel>
}


