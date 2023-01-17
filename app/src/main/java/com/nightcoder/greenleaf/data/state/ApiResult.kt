package com.nightcoder.greenleaf.data.state

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failed(val error: String) : ApiResult<Nothing>()
}
