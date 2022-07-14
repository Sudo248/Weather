package com.sudo.weather.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.sudo.weather.data.data_sources.api.LocationApi
import com.sudo.weather.data.utils.handlerResponseAsync
import com.sudo.weather.domain.async.withContextHandler
import com.sudo.weather.domain.common.Failure
import javax.inject.Inject
import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.repository.LocationRepository

class LocationRepositoryImpl @Inject constructor(
    private val locationApi: LocationApi,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : LocationRepository {
    override suspend fun getLocationKey(location: String): Status<String> {
        return try {
            val response = withContextHandler{
                locationApi.searchLocationByName(name = location)
            }

            val locations = handlerResponseAsync(response)
            Status.Success(locations[0].Key)

        }catch (e: Exception){
            Status.Error(error = e)
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocationKey(): Status<String> {
        return try {
            val location = fusedLocationProviderClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null)

            if(location.isSuccessful){
                val response = withContextHandler{
                    locationApi.searchLocationByLatLng(latLng = "${location.result.latitude},${location.result.longitude}")
                }
                val locationResponse = handlerResponseAsync(response)
                Status.Success(locationResponse.Key)
            }else{
                throw location.exception ?: Failure.UnexpectedError()
            }
        }catch (e: Exception){
            Status.Error(error = e)
        }
    }
}