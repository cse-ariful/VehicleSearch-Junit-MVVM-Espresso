package com.nightcoder.greenleaf.data.vehicleSearch

import com.nightcoder.greenleaf.data.api.VehicleSearchApi
import com.nightcoder.greenleaf.data.model.VehicleInfoDataModel
import com.nightcoder.greenleaf.data.state.ApiResult

class SearchVehicleServiceImpl(private val api: VehicleSearchApi) : SearchVehicleService {

    override suspend fun queryVehicleInfo(regNo: String): ApiResult<VehicleInfoDataModel> {

        val task = api.queryVehicleInfo(regNo).execute()

        if (task.isSuccessful) {
            return ApiResult.Success(task.body()!!)
        }

        return ApiResult.Failed("Something went wrong")

    }
}