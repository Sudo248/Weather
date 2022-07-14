package com.sudo.weather.data.utils

import com.sudo.weather.domain.common.Failure.ApiFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

fun <T> handlerResponse(response: Response<T>): T {
    if (response.isSuccessful) {
        return if (response.body() != null) {
            response.body()!!
        } else {
            throw ApiFailure.BadRequest(message = "Response body required not null")
        }
    } else {
        val messageError = response.errorBody()?.string()?.let {
            JSONObject(it).getString("error")
        }
        val code = response.code()
        throw ApiFailure.getInstance(statusCode = code, message = messageError)
    }
}

suspend fun <T> handlerResponseAsync(response: Response<T>): T {
    if (response.isSuccessful) {
        return if (response.body() != null) {
            response.body()!!
        } else {
            throw ApiFailure.BadRequest(message = "Response body required not null")
        }
    } else {
        val messageError = withContext(Dispatchers.Default) {
            response.errorBody()?.string()?.let {
                JSONObject(it).getString("error")
            }
        }
        val code = response.code()
        throw ApiFailure.getInstance(statusCode = code, message = messageError)
    }
}