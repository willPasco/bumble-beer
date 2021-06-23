package com.will.bumblebeer.data.remote

import com.will.bumblebeer.data.remote.exceptions.ApiException
import com.will.bumblebeer.infraestructure.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRemoteDataSource(private val dispatcher: CoroutineDispatcher) {
    suspend fun <T : Any> serviceCall(call: suspend () -> Response<T>): ResultWrapper<T> =
        withContext(dispatcher) {
            val response = call.invoke()

            return@withContext if (response.isSuccessful) {
                ResultWrapper.Success(response.body()!!)
            } else {
                ResultWrapper.Error(ApiException(response.errorBody().toString()))
            }
        }
}