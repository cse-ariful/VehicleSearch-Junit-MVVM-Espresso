package com.nightcoder.vehiclesearch.domain.state

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Content<out T>(val data: T) : UiState<T>()
    data class Error(val error: String) : UiState<Nothing>()
}