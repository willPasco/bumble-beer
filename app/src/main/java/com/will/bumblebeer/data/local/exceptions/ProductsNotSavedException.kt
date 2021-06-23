package com.will.bumblebeer.data.local.exceptions

class ProductsNotSavedException : Exception() {

    override val message: String?
        get() = "Error to save the product in local database."
}
