package com.will.bumblebeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.JoinProductCustomer
import com.will.bumblebeer.domain.usecases.product.ProductUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val productUseCase: ProductUseCase) : ViewModel() {

    /**
     * Mutable live data to post true when redirecting to a new View
     */
    private val _productCustomerLiveData = MutableLiveData<List<JoinProductCustomer>>()

    /**
     * Live data that can be observed by views
     */
    val productCustomerLiveData: LiveData<List<JoinProductCustomer>> = _productCustomerLiveData

    /**
     * Mutable live data to post possible errors
     */
    private val _errorLiveData = MutableLiveData<Exception>()

    /**
     * Live data that can be observed by views
     */
    val errorLiveData: LiveData<Exception> = _errorLiveData

    /**
     * Mutable live data to post to start loading
     */
    private val _loadingLiveData = MutableLiveData<Boolean>()

    /**
     * Live data that can be observed by views
     */
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    /**
     * Initial called method
     */
    fun setup() {
        _loadingLiveData.postValue(true)
        viewModelScope.launch {
            getJoinProductCustomer()
        }
    }

    private suspend fun getJoinProductCustomer() {
        productUseCase.getJoinProductCustomer().let { result ->
            when (result) {
                is ResultWrapper.Success -> _productCustomerLiveData.postValue(result.data)
                is ResultWrapper.Error -> _errorLiveData.postValue(result.exception)
            }
            _loadingLiveData.postValue(false)
        }
    }
}