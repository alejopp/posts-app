package com.example.posts_app.data.api

import com.example.posts_app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ApiResponseStatus.Error(R.string.unknown_host_exception)
    } catch (e: Exception) {
        val errorMessage = when(e.message) {
            else -> R.string.network_error
        }
        ApiResponseStatus.Error(errorMessage)
    }
}