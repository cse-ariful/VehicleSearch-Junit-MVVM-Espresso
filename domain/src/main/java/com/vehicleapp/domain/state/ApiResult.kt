package com.vehicleapp.domain.state

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failed(val error: String) : ApiResult<Nothing>()
}
