package com.will.bumblebeer.ui.product

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.will.bumblebeer.databinding.ActivityProductCrudBinding
import com.will.bumblebeer.databinding.LoadingDialogBinding
import com.will.bumblebeer.domain.entities.Customer
import com.will.bumblebeer.domain.entities.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductCrudActivity : AppCompatActivity() {

    //TODO In progress, need to implement in the flow and the view model Update, Save and Delete + integration with other layers.

    companion object {
        const val PRODUCT_ID = "product_id"
    }

    private lateinit var spinnerAdapter: ProductCrudArrayAdapter
    private val viewModel: ProductCrudViewModel by viewModel()

    /**
     * Dialog to show when the app is processing something
     */
    private var progressDialog: Dialog? = null

    /**
     * Activity View Binding
     */
    private val bindingActivity by lazy {
        ActivityProductCrudBinding.inflate(layoutInflater)
    }

    /**
     * Progress View Binding
     */
    private val bindingProgress by lazy {
        LoadingDialogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivity.root)
        showLoadingState()
        setupCustomerLiveDataObservable()
        setupProductLiveDataObservable()

        loadProduct(intent.extras)
    }

    private fun setupProductLiveDataObservable() {
        viewModel.productLiveData.observe(this@ProductCrudActivity, Observer { product ->
            showProductData(product)
        })
    }

    private fun showProductData(product: Product?) {
        if (product != null) {
            bindingActivity.editviewProductDescription.setText(product.description)
            setCustomerInSpinner(product.customerId)
        }

        hideLoadingState()
    }

    private fun setCustomerInSpinner(customerId: Int) {
        val position = spinnerAdapter.getPositionByCustomerId(customerId)
        bindingActivity.spinnerProductCustomer.setSelection(position)
    }

    private fun setupSpinner(customerList: List<Customer>) {
        spinnerAdapter =
            ProductCrudArrayAdapter(this, android.R.layout.simple_spinner_item, customerList)
        bindingActivity.spinnerProductCustomer.adapter = spinnerAdapter
    }

    private fun setupCustomerLiveDataObservable() {
        viewModel.customerLiveData.observe(this@ProductCrudActivity, Observer { customerList ->
            showCustomerData(customerList)
        })
    }

    private fun showCustomerData(customerList: List<Customer>) {
        if (customerList.isNotEmpty()) {
            setupSpinner(customerList)
        }
    }

    private fun loadProduct(extras: Bundle?) {
        val productId: Int? = extras?.getInt(PRODUCT_ID)
        viewModel.loadProduct(productId)
    }

    private fun showLoadingState() {
        if (progressDialog == null) {
            progressDialog = buildLoadingDialog(this)
        }
        progressDialog?.show()
    }

    private fun hideLoadingState() {
        progressDialog?.dismiss()
    }

    private fun buildLoadingDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(bindingProgress.root)
        dialog.setCancelable(true)

        return dialog
    }
}
