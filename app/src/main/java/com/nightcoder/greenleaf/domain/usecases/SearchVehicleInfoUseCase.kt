package com.nightcoder.greenleaf.domain.usecases

import android.util.Log
import com.nightcoder.greenleaf.data.state.ApiResult
import com.nightcoder.greenleaf.data.vehicleSearch.SearchVehicleService
import com.nightcoder.greenleaf.data.model.toDomainModel
import com.nightcoder.greenleaf.domain.model.VehicleInfoModel

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