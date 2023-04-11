package com.nightcoder.vehiclesearch.data.vehicleSearch

import com.vehicleapp.data.api.VehicleSearchApi
import com.vehicleapp.data.model.VehicleInfoDataModel
import com.vehicleapp.domain.state.ApiResult
import com.vehicleapp.data.vehicleSearch.SearchVehicleServiceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
@OptIn(ExperimentalCoroutinesApi::class)
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