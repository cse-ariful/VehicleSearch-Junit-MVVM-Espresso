package com.nightcoder.vehiclesearch.data.vehicleSearch

import com.nightcoder.vehiclesearch.data.api.VehicleSearchApi
import com.nightcoder.vehiclesearch.data.model.VehicleInfoDataModel
import com.nightcoder.vehiclesearch.data.state.ApiResult
import com.nightcoder.vehiclesearch.domain.service.SearchVehicleService

class SearchVehicleServiceImpl(private val api: VehicleSearchApi) : SearchVehicleService {

    override suspend fun queryVehicleInfo(regNo: String): ApiResult<VehicleInfoDataModel> {

        val task = api.queryVehicleInfo(regNo).execute()

        if (task.isSuccessful) {
            return ApiResult.Success(task.body()!!)
        }

        return ApiResult.Failed("Something went wrong")

    }
}