package com.nightcoder.greenleaf.ui.features.searchtool

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vehicleapp.domain.state.ApiResult
import com.vehicleapp.domain.model.VehicleInfoModel
import com.vehicleapp.domain.state.UiState
import com.vehicleapp.domain.usecases.SearchVehicleInfoUseCase
import com.vehicleapp.domain.logger.Logger
import com.nightcoder.vehiclesearch.ui.features.searchtool.SearchToolViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class SearchToolViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val searchVehicleUseCase: SearchVehicleInfoUseCase = mockk()

    private lateinit var viewModel: SearchToolViewModel

    private val logger = mockk<Logger>{
        every {
            error(any(),any())
        } returns Unit
        every {
            info(any())
        } returns Unit
    }

    private val defaultDataModel =  mockk<VehicleInfoModel>(relaxed = true){
        every {
            motExpiry
        }returns "20231011"
    }

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = SearchToolViewModel(searchVehicleUseCase,logger,dispatcher)
    }


    @Test
    fun `When fetching details success state is show`() =   scope.runTest {

        coEvery {
            searchVehicleUseCase.query(any())
        } returns ApiResult.Success(defaultDataModel)

        viewModel.queryDetails("")

        advanceUntilIdle()

        assert(viewModel.state.value is UiState.Content)

    }

    @Test
    fun `Show error state when date format is wrong`() = scope.runTest {

        coEvery {
            searchVehicleUseCase.query(any())
        } returns ApiResult.Success(
            defaultDataModel.copy(motExpiry = "WrongDate")
        )

        viewModel.queryDetails("")

        advanceUntilIdle()

        assert(viewModel.state.value is UiState.Error)
    }

    @Test
    fun `When error fetching details error state is shown`() = scope.runTest{

        coEvery {
            searchVehicleUseCase.query(any())
        } returns ApiResult.Failed("Error")

        viewModel.queryDetails("")

        advanceUntilIdle()

        assert(viewModel.state.value is UiState.Error)
    }

    @Test
    fun `When fetching details first loading state is shown`() = scope.runTest{

        coEvery {
            searchVehicleUseCase.query(any())
        } returns ApiResult.Success(defaultDataModel)

        val states = mutableListOf<UiState<Any>>()

        viewModel.state.observeForever { states.add(it) }

        viewModel.queryDetails("")

        advanceUntilIdle()

        assert(states[0] is UiState.Idle)

        assert(states[1] is UiState.Loading)

        assert(states[2] is UiState.Content || states[2] is UiState.Error)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

}