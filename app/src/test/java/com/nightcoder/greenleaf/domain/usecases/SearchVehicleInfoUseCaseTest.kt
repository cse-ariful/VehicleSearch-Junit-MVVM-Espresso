package com.nightcoder.greenleaf.domain.usecases

import com.vehicleapp.data.model.VehicleInfoDataModel
import com.vehicleapp.domain.state.ApiResult
import com.vehicleapp.data.vehicleSearch.SearchVehicleServiceImpl
import com.vehicleapp.domain.usecases.SearchVehicleInfoUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchVehicleInfoUseCaseTest {
    private val service: SearchVehicleServiceImpl = mockk()
    val sut = SearchVehicleInfoUseCase(service)

    private val testDataModel = VehicleInfoDataModel(
        "testMake",
        "testModel",
        "testDetails",
        "testEngine",
        "testGearBox",
        "testBodyType",
        "testYear",
        "2231210"
    )

    @Test
    fun `Test UseCase Is Handling Exception`() = runTest {
        coEvery { service.queryVehicleInfo(any()) } throws Exception("No Internet Connection")
        val result = sut.query("")
        assert(result is ApiResult.Failed)
    }

    @Test
    fun `Test UseCase is Delivering Success Result`() = runTest {
        coEvery {
            service.queryVehicleInfo(any())
        } returns ApiResult.Success(testDataModel)

        val result = sut.query("")

        assert(result is ApiResult.Success)

    }

    @Test
    fun `Test UseCase is Delivering Error Result`() = runTest {
        coEvery {
            service.queryVehicleInfo(any())
        } returns ApiResult.Failed("Something went wrong")

        val result = sut.query("")

        assert(result is ApiResult.Failed)

    }
}