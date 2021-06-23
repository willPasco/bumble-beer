package com.will.bumblebeer.data.remote.dto

import com.google.gson.annotations.SerializedName

/***
 * Class to represent the customer object received from backend.
 */
class CustomerDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)