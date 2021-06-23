package com.will.bumblebeer.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.Product
import com.will.bumblebeer.domain.usecases.product.ProductUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel(private val useCase: ProductUseCase) : ViewModel() {

    /**
     * Mutable live data to post possible errors
     */
    private val _errorLiveData = MutableLiveData<Exception>()

    /**
     * Live data that can be observed by views
     */
    val errorLiveData: LiveData<Exception> = _errorLiveData

    /**
     * Mutable live data to post retrieved product list
     */
    private val _productListLiveData = MutableLiveData<List<Product>>()

    /**
     * Live data that can be observed by views
     */
    val productListLiveData: LiveData<List<Product>> = _productListLiveData

    /**
     * Mutable live data to post to start loading
     */
    private val _loadingLiveData = MutableLiveData<Boolean>()

    /**
     * Live data that can be observed by views
     */
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getProducts() {
        _loadingLiveData.postValue(true)
        viewModelScope.launch {
            useCase.getProducts().let { result ->
                when (result) {
                    is ResultWrapper.Success -> _productListLiveData.postValue(result.data)
                    is ResultWrapper.Error -> _errorLiveData.postValue(result.exception)
                }
                _loadingLiveData.postValue(false)
            }
        }
    }
}
