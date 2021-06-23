package com.will.bumblebeer.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.ActivityHomeBinding
import com.will.bumblebeer.ui.customer.CustomerActivity
import com.will.bumblebeer.ui.product.ProductActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    /**
     * View Binding
     */
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setTitle(R.string.home_title)
        initializeAdapter()

        viewModel.setup()

        observeProductCustomerJoin()
        observeProductCustomerJoinError()
        observeLoading()
    }

    /**
     * Used to instantiate the adapter
     */
    private fun initializeAdapter() {
        binding.recyclerHome.layoutManager =
            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerHome.adapter = HomeAdapter()
    }

    private fun observeProductCustomerJoin() =
        viewModel.productCustomerLiveData.observe(this@HomeActivity, Observer { list ->
            (binding.recyclerHome.adapter as HomeAdapter).updateItems(list)
        })


    private fun observeLoading() =
        viewModel.loadingLiveData.observe(this@HomeActivity, Observer { willShowLoading ->
            if (willShowLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })


    private fun observeProductCustomerJoinError() =
        viewModel.errorLiveData.observe(this@HomeActivity, Observer {
            AlertDialog.Builder(this@HomeActivity)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_customer -> onMenuCustomerSelected()
            R.id.menu_product -> onMenuProductSelected()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onMenuProductSelected() {
        startActivity(
            Intent(
                this@HomeActivity,
                ProductActivity::class.java
            )
        )
    }

    private fun onMenuCustomerSelected() {
        startActivity(
            Intent(
                this@HomeActivity,
                CustomerActivity::class.java
            )
        )
    }

    private fun showLoading() {
        binding.progressBarHome.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBarHome.visibility = View.GONE
    }
}