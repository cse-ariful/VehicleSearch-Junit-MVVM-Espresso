package com.nightcoder.vehiclesearch.ui.features.searchtool

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vehicleapp.domain.state.ApiResult
import com.nightcoder.vehiclesearch.di.IoDispatcher
import com.vehicleapp.domain.model.VehicleFeatureItem
import com.vehicleapp.domain.model.VehicleInfoModel
import com.vehicleapp.domain.state.UiState
import com.vehicleapp.domain.usecases.SearchVehicleInfoUseCase
import com.vehicleapp.domain.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchToolViewModel @Inject constructor(
    private val vehicleInfoSearchUseCase: SearchVehicleInfoUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val stateData = MutableLiveData<UiState<List<VehicleFeatureItem>>>(UiState.Idle)
    val state: LiveData<UiState<List<VehicleFeatureItem>>> get() = stateData

    fun queryDetails(regNo: String) {

        stateData.postValue(UiState.Loading)

        viewModelScope.launch(dispatcher) {
            try {
                when (val result = vehicleInfoSearchUseCase.query(regNo)) {
                    is ApiResult.Success -> {
                        logger.info("Api Call Success for query $regNo")
                        configureUiModelsFromResponse(result.data)
                    }
                    is ApiResult.Failed -> {
                        logger.info("Api Call Failed for query $regNo")
                        throw Exception(result.error)
                    }
                }
            } catch (ex: Exception) {
                logger.error(null,ex)
                stateData.postValue(UiState.Error(ex.message ?: "Unable to fetch search result"))
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