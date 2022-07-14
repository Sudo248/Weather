package com.sudo.weather.data.data_sources.api

import com.sudo.weather.data.ConstantData
import com.sudo.weather.data.models.responses.location.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("/locations/v1/cities/VN/search")
    suspend fun searchLocationByName(
        @Query("apikey") apiKey: String = ConstantData.apiKey,
        @Query("q") name: String
    ): Response<List<LocationResponse>>

    @GET("/locations/v1/cities/geoposition/search")
    suspend fun searchLocationByLatLng(
        @Query("apikey") apiKey: String = ConstantData.apiKey,
        @Query("q") latLng: String
    ): Response<LocationResponse>

}