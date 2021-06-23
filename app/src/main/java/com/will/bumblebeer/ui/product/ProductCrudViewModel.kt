package com.will.bumblebeer.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.bumblebeer.infraestructure.ResultWrapper
import com.will.bumblebeer.domain.entities.Customer
import com.will.bumblebeer.domain.entities.Product
import com.will.bumblebeer.domain.usecases.customer.CustomerUseCase
import com.will.bumblebeer.domain.usecases.product.ProductUseCase
import kotlinx.coroutines.launch

class ProductCrudViewModel(private val customerUseCase: CustomerUseCase,
                           private val productUseCase: ProductUseCase) : ViewModel() {

    private val _customersLiveData = MutableLiveData<List<Customer>>()
    private val _productLiveData = MutableLiveData<Product?>()
    private val _errorLiveData = MutableLiveData<Exception>()
    private val _finishCrudLiveData = MutableLiveData<Boolean>()

    val customerLiveData: LiveData<List<Customer>> = _customersLiveData
    val productLiveData: LiveData<Product?> = _productLiveData
    val errorLiveData: LiveData<Exception> = _errorLiveData
    val finishCrudLiveData: LiveData<Boolean> = _finishCrudLiveData

    private suspend fun loadCustomers(): ResultWrapper<List<Customer>> {
        customerUseCase.getCustomers().let { result ->
            return when (result) {
                is ResultWrapper.Success -> {
                    updateCustomerLiveData(result.data)
                    result
                }
                is ResultWrapper.Error -> {
                    handleError(result.exception)
                    result
                }
            }
        }
    }

    /**
     * Handle the product request.
     * Before to load the product we need to load all customers, in case of a null product id, the product isn't loaded and notify the view that
     * isn't necessary to load the product.
     *
     * @param productId Id of product that needs to be loaded.
     */
    private fun handleProductRequest(productId: Int?) {
        viewModelScope.launch {
            if (loadCustomers() is ResultWrapper.Success) {
                if (productId != null) {
                    productUseCase.getProductById(productId).let { result ->
                        when (result) {
                            is ResultWrapper.Success -> updateProductLiveData(result.data)
                            is ResultWrapper.Error -> handleError(result.exception)
                        }
                    }
                } else {
                    updateProductLiveData(null)
                }
            }
        }
    }

    private fun updateCustomerLiveData(customerList: List<Customer>) {
        _customersLiveData.postValue(customerList)
    }

    private fun updateProductLiveData(product: Product?) {
        _productLiveData.postValue(product)
    }

    private fun updateFinishCrudLiveData(isFinished: Boolean) {
        _finishCrudLiveData.postValue(isFinished)
    }

    private fun handleError(exception: Exception) {
        _errorLiveData.postValue(exception)
    }

    /**
     * Main method to insert the view information in local database..
     *
     * @param id Id of product that will be insert.
     * @param description Description of product that will be insert.
     * @param customer Customer related of product, needs to use the id to make the relationship between them.
     */
    private fun saveProduct(id: Int, description: String, customer: Customer) {
        viewModelScope.launch {
            val product = Product(id, description, customer.id)
            val insertResponse = productUseCase.insertSingleProduct(product)
            handleSaveResponse(insertResponse)
        }
    }

    /**
     * Method to handle the response of porduct insert operation
     *
     * @param insertResponse The result of insert operation as a boolean if was success.
     */
    private fun handleSaveResponse(insertResponse: ResultWrapper<Boolean>) {
        when (insertResponse) {
            is ResultWrapper.Success -> {
                updateFinishCrudLiveData(insertResponse.data)
            }
            is ResultWrapper.Error -> {
                handleError(insertResponse.exception)
            }
        }
    }

    /**
     * Main method to link the view with the data in database.
     * It will request the product to product use case.
     *
     * @param productId Id of product that needs to be loaded.
     */
    fun loadProduct(productId: Int?) {
        handleProductRequest(productId)
    }
}