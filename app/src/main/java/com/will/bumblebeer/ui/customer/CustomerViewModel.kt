package com.will.bumblebeer.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.Customer
import com.will.bumblebeer.domain.usecases.customer.CustomerUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class CustomerViewModel(private val useCase: CustomerUseCase) : ViewModel() {

    /**
     * Mutable live data to post possible errors
     */
    private val _errorLiveData = MutableLiveData<Exception>()

    /**
     * Live data that can be observed by views
     */
    val errorLiveData: LiveData<Exception> = _errorLiveData

    /**
     * Mutable live data to post retrieved customer list
     */
    private val _customerListLiveData = MutableLiveData<List<Customer>>()

    /**
     * Live data that can be observed by views
     */
    val customerListLiveData: LiveData<List<Customer>> = _customerListLiveData

    fun getCustomers() {
        viewModelScope.launch {
            useCase.getCustomers().let { result ->
                when (result) {
                    is ResultWrapper.Success -> _customerListLiveData.postValue(result.data)
                    is ResultWrapper.Error -> _errorLiveData.postValue(result.exception)
                }
            }
        }
    }
}
