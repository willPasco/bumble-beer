package com.will.bumblebeer.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.usecases.customer.CustomerUseCase
import com.will.bumblebeer.domain.usecases.product.ProductUseCase
import com.will.bumblebeer.ui.splash.SplashActivity.Companion.logTag
import kotlinx.coroutines.launch

class SplashViewModel(
    private val customerUseCase: CustomerUseCase,
    private val productUseCase: ProductUseCase
) : ViewModel() {

    /**
     * Mutable live data to post true when redirecting to a new View
     */
    private val _showNextViewLiveData = MutableLiveData<Boolean>()

    /**
     * Live data that can be observed by views
     */
    val showNextViewLiveData: LiveData<Boolean> = _showNextViewLiveData

    /**
     * Mutable live data to post possible errors
     */
    private val _errorLiveData = MutableLiveData<Exception>()

    /**
     * Live data that can be observed by views
     */
    val errorLiveData: LiveData<Exception> = _errorLiveData

    /**
     * Initial called method
     * If customers where successfully loaded, load products
     */
    fun setup() {
        viewModelScope.launch {
            if (loadCustomers()) {
                loadProducts()
            }
        }
    }

    private suspend fun loadCustomers(): Boolean {
        customerUseCase.getCustomers().let { customerResult ->
            return when (customerResult) {
                is ResultWrapper.Success -> {
                    Log.i(logTag, "Customers data successfully retrieved")
                    true
                }
                is ResultWrapper.Error -> {
                    Log.i(logTag, "Customers data couldn't be retrieved, closing app")
                    _errorLiveData.postValue(customerResult.exception)
                    false
                }
            }
        }
    }

    private suspend fun loadProducts() {
        productUseCase.getProducts().let { productResult ->
            when (productResult) {
                is ResultWrapper.Success -> {
                    Log.i(logTag, "Products data successfully retrieved")
                    startHome()
                }
                is ResultWrapper.Error -> {
                    Log.i(logTag, "Products data couldn't be retrieved, closing app")
                    _errorLiveData.postValue(productResult.exception)
                }
            }
        }
    }

    /**
     * Responsible to notify the activity to redirect to HomeActivity
     */
    private fun startHome() {
        _showNextViewLiveData.postValue(true)
    }
}