package com.nightcoder.vehiclesearch.data.vehicleSearch

import com.nightcoder.vehiclesearch.data.api.VehicleSearchApi
import com.nightcoder.vehiclesearch.data.model.VehicleInfoDataModel
import com.nightcoder.vehiclesearch.data.state.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class SearchVehicleServiceImplTest {
    private lateinit var sut: SearchVehicleServiceImpl
    private lateinit var vehicleSearchApi: VehicleSearchApi

    @Before
    fun setup() {
        vehicleSearchApi = mockk()
        sut = SearchVehicleServiceImpl(vehicleSearchApi)
    }

    @Test
    fun `Test if it return success result if api call is successful`() = runTest {
        val expectedResult: VehicleInfoDataModel = mockk(relaxed = true)
        coEvery {
            vehicleSearchApi.queryVehicleInfo(any()).execute()
        } returns Response.success(expectedResult)
        val result = sut.queryVehicleInfo("")
        assert(result is ApiResult.Success)
        val data = (result as ApiResult.Success).data
        assert(data == expectedResult)
    }

    @Test
    fun `Test if server call fail it returns the Fail result without throwing exception`() = runTest{

        val responseBody :ResponseBody = mockk(relaxed = true)
        coEvery {
            vehicleSearchApi.queryVehicleInfo(any()).execute()
        } returns Response.error(400, responseBody)

        val result = sut.queryVehicleInfo("")

        assert(result is ApiResult.Failed)
    }

}