package com.vehicleapp.data.vehicleSearch

import com.vehicleapp.data.api.VehicleSearchApi
import com.vehicleapp.data.model.VehicleInfoDataModel
import com.vehicleapp.data.model.toDomainModel
import com.vehicleapp.domain.model.VehicleInfoModel
import com.vehicleapp.domain.service.SearchVehicleService
import com.vehicleapp.domain.state.ApiResult

class SearchVehicleServiceImpl(private val api: VehicleSearchApi) : SearchVehicleService {

    override suspend fun queryVehicleInfo(regNo: String): ApiResult<VehicleInfoModel> {

        val task = api.queryVehicleInfo(regNo).execute()

        if (task.isSuccessful) {
            return ApiResult.Success(task.body()!!.toDomainModel())
        }

        return ApiResult.Failed("Something went wrong")

    }
}