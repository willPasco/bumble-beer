package com.will.bumblebeer.ui.product

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.ActivityProductBinding
import com.will.bumblebeer.domain.entities.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTitle(R.string.product_title)

        setupAdapter()
        setupFab()

        viewModel.getProducts()

        observeLoading()
        observeProductList()
        observeProductListError()
    }

    private fun observeLoading() {
        viewModel.loadingLiveData.observe(this@ProductActivity, Observer { willShowLoading ->
            if (willShowLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun observeProductListError() =
        viewModel.errorLiveData.observe(this@ProductActivity, Observer {
            AlertDialog.Builder(this@ProductActivity)
                .setTitle(R.string.error)
                .setMessage(it.message)
                .setCancelable(false)
                .setPositiveButton(R.string.ok) { dialogInterface, which ->
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        dialogInterface.dismiss()
                    }
                }
                .show()
        })


    private fun observeProductList() =
        viewModel.productListLiveData.observe(this@ProductActivity, Observer {
            updateAdapterList(it)
        })


    private fun setupFab() =
        binding.fabProduct.setOnClickListener {
            Toast.makeText(
                this@ProductActivity,
                R.string.not_implemented_yet,
                Toast.LENGTH_LONG
            ).show()
        }

    private fun updateAdapterList(updatedList: List<Product>) =
        (binding.recyclerProduct.adapter as ProductAdapter).updateProducts(updatedList)

    private fun setupAdapter() {
        binding.recyclerProduct.layoutManager =
            LinearLayoutManager(this@ProductActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerProduct.adapter = ProductAdapter()
    }

    private fun showLoading() {
        binding.progressBarProduct.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBarProduct.visibility = View.GONE
    }
}

