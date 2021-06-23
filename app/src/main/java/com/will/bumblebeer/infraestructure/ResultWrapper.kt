package com.will.bumblebeer.infraestructure

/***
 * Wrapper class to handle with the returns between local database base and the interface.
 */
sealed class ResultWrapper<out R> {
    /***
     * Data class inside the wrapper that contains the result of the call.
     */
    class Success<out T>(val data: T) : ResultWrapper<T>()

    /***
     * Data class inside the wrapper to handle with error/exceptions.
     */
    class Error(val exception: Exception) : ResultWrapper<Nothing>()
}