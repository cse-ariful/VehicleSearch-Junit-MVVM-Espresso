package com.nightcoder.greenleaf.domain.usecases

import com.nightcoder.vehiclesearch.data.model.VehicleInfoDataModel
import com.nightcoder.vehiclesearch.data.state.ApiResult
import com.nightcoder.vehiclesearch.data.vehicleSearch.SearchVehicleServiceImpl
import com.nightcoder.vehiclesearch.domain.usecases.SearchVehicleInfoUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

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
    fun `Test UseCase Is Handling Exception`() = runBlockingTest {
        coEvery { service.queryVehicleInfo(any()) } throws Exception("No Internet Connection")
        val result = sut.query("")
        assert(result is ApiResult.Failed)
    }

    @Test
    fun `Test UseCase is Delivering Success Result`() = runBlockingTest {
        coEvery {
            service.queryVehicleInfo(any())
        } returns ApiResult.Success(testDataModel)

        val result = sut.query("")

        assert(result is ApiResult.Success)

    }

    @Test
    fun `Test UseCase is Delivering Error Result`() = runBlockingTest {
        coEvery {
            service.queryVehicleInfo(any())
        } returns ApiResult.Failed("Something went wrong")

        val result = sut.query("")

        assert(result is ApiResult.Failed)

    }
}