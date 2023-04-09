package com.nightcoder.vehiclesearch.domain.usecases

import com.nightcoder.vehiclesearch.data.model.toDomainModel
import com.nightcoder.vehiclesearch.data.state.ApiResult
import com.nightcoder.vehiclesearch.domain.service.SearchVehicleService
import com.nightcoder.vehiclesearch.domain.model.VehicleInfoModel

class SearchVehicleInfoUseCase(private val searchVehicleService: SearchVehicleService) {

    suspend fun query(regNo: String): ApiResult<VehicleInfoModel> {
        return try {
            val result = searchVehicleService.queryVehicleInfo(regNo)
            if (result is ApiResult.Success) {
                ApiResult.Success(result.data.toDomainModel())
            }else {
                ApiResult.Failed("Server returned invalid result")
            }
        } catch (ex: Exception) {
            ApiResult.Failed(ex.message?:"Something went wrong")
        }
    }

}