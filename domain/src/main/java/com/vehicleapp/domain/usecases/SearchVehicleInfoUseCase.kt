package com.vehicleapp.domain.usecases

import com.vehicleapp.domain.service.SearchVehicleService
import com.vehicleapp.domain.model.VehicleInfoModel
import com.vehicleapp.domain.state.ApiResult

class SearchVehicleInfoUseCase(private val searchVehicleService: SearchVehicleService) {

    suspend fun query(regNo: String): ApiResult<VehicleInfoModel> {
        return try {
            val result = searchVehicleService.queryVehicleInfo(regNo)
            if (result is ApiResult.Success) {
                ApiResult.Success(result.data)
            }else {
                ApiResult.Failed("Server returned invalid result")
            }
        } catch (ex: Exception) {
            ApiResult.Failed(ex.message?:"Something went wrong")
        }
    }

}