package com.will.bumblebeer.data.remote.customer

import com.will.bumblebeer.data.remote.dto.CustomerDTO
import retrofit2.Response
import retrofit2.http.GET

interface CustomerApi {
    @GET(value = "/getCustomers")
    suspend fun getCustomers(): Response<List<CustomerDTO>>
}
