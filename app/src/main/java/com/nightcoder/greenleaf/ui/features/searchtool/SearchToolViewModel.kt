package com.nightcoder.greenleaf.ui.features.searchtool

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nightcoder.greenleaf.data.state.ApiResult
import com.nightcoder.greenleaf.di.IoDispatcher
import com.nightcoder.greenleaf.domain.state.UiState
import com.nightcoder.greenleaf.domain.model.VehicleFeatureItem
import com.nightcoder.greenleaf.domain.model.VehicleInfoModel
import com.nightcoder.greenleaf.domain.usecases.SearchVehicleInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchToolViewModel @Inject constructor(
    private val vehicleInfoSearchUseCase: SearchVehicleInfoUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    companion object {
        private const val TAG = "SearchToolViewModel"
    }

    private val stateData = MutableLiveData<UiState<List<VehicleFeatureItem>>>(UiState.Idle)
    val state: LiveData<UiState<List<VehicleFeatureItem>>> get() = stateData

    fun queryDetails(regNo: String) {
        stateData.postValue(UiState.Loading)
        viewModelScope.launch(dispatcher) {
            try {
                when (val result = vehicleInfoSearchUseCase.query(regNo)) {
                    is ApiResult.Success -> {
                        configureUiModelsFromResponse(result.data)
                    }
                    is ApiResult.Failed -> {
                        throw Exception(result.error)
                    }
                }
            } catch (ex: Exception) {
                stateData.postValue(UiState.Error(ex.message ?: "Unable to load server result"))
            }
        }
    }

    @Throws(Exception::class)
    private fun configureUiModelsFromResponse(data: VehicleInfoModel) {
        val items = mutableListOf<VehicleFeatureItem>()
        items.add(VehicleFeatureItem("Make", data.make))
        items.add(VehicleFeatureItem("Model", data.model))
        items.add(VehicleFeatureItem("BodyType", data.bodyType))
        items.add(VehicleFeatureItem("Details", data.details))
        items.add(VehicleFeatureItem("Engine", data.engine))
        items.add(VehicleFeatureItem("Year", data.year))
        items.add(VehicleFeatureItem("GearBox", data.gearbox))
        val dateData = "Valid until ${parseDate(data.motExpiry)}"
        items.add(VehicleFeatureItem("MOT", dateData, true))
        stateData.postValue(UiState.Content(items))
    }

    private fun parseDate(dateStr: String): String {
        val date = SimpleDateFormat("yyyyMMdd", Locale.US).parse(dateStr)!!
        return SimpleDateFormat("dd-mm-yyyy", Locale.US).format(date)
    }

    override fun onCleared() {
        super.onCleared()
    }
}