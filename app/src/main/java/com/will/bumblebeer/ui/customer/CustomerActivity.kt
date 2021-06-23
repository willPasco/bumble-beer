package com.will.bumblebeer.ui.customer

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.ActivityCustomerBinding
import com.will.bumblebeer.domain.entities.Customer
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_CREATE
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCustomerBinding.inflate(layoutInflater)
    }

    private val viewModel: CustomerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTitle(R.string.customer_title)

        setupAdapter()
        setupFab()

        viewModel.getCustomers()

        observeCustomerList()
        observeCustomerListError()
    }

    private fun observeCustomerListError() =
        viewModel.errorLiveData.observe(this@CustomerActivity, Observer {
            AlertDialog.Builder(this@CustomerActivity)
                .setTitle(R.string.error)
                .setMessage(it.message)
                .setCancelable(false)
                .setPositiveButton(R.string.ok) { dialogInterface, which ->
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        dialogInterface.dismiss()
                    }
                }
                .show()
            hideLoading()
        })


    private fun observeCustomerList() =
        viewModel.customerListLiveData.observe(this@CustomerActivity, Observer {
            updateAdapterList(it)
            hideLoading()
        })


    private fun setupFab() =
        binding.fabCustomer.setOnClickListener {
            val actionType = CRUD_ACTION_CREATE
            val intent = Intent(this, CustomerCrudActivity::class.java)
            intent.putExtra(CRUD_ACTION_TYPE, actionType)
            startActivity(intent)
        }

    private fun updateAdapterList(updatedList: List<Customer>) =
        (binding.recyclerCustomer.adapter as CustomerAdapter).updateCustomers(updatedList)

    private fun setupAdapter() {
        binding.recyclerCustomer.layoutManager =
            LinearLayoutManager(this@CustomerActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerCustomer.adapter = CustomerAdapter()
    }

    private fun hideLoading() {
        binding.progressBarCustomer.visibility = View.GONE
    }
}

