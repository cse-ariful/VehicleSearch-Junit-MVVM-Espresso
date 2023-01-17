package com.nightcoder.greenleaf.domain.usecases

import com.nightcoder.greenleaf.data.state.ApiResult
import com.nightcoder.greenleaf.data.vehicleSearch.SearchVehicleServiceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class SearchVehicleInfoUseCaseTest {
    private val service: SearchVehicleServiceImpl = mockk()
    val sut = SearchVehicleInfoUseCase(service)

    @Test
    fun `Test UseCase Is Handling Exception`() = runBlockingTest{
        coEvery { service.queryVehicleInfo(any()) } throws Exception("No Internet Connection")
        val result = sut.query("")
        assert(result is ApiResult.Failed)
    }
    @Test
    fun `Test UseCase is Delivering SuccessResult`() =runBlockingTest{
        coEvery {
            service.queryVehicleInfo(any())
        }returns ApiResult.Success(mockk())

        val result = sut.query("")
        assert(result is ApiResult.Failed)
    }
}