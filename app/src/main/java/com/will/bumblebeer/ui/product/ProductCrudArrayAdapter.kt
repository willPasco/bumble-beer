package com.will.bumblebeer.ui.product

import android.content.Context
import android.widget.ArrayAdapter
import com.will.bumblebeer.domain.entities.Customer

class ProductCrudArrayAdapter(context: Context, view: Int, private val customerList: List<Customer>) : ArrayAdapter<Customer>(context, view) {

    fun getPositionByCustomerId(customerId: Int): Int {
        val customer = getCustomerById(customerId)
        return getPosition(customer)
    }

    private fun getCustomerById(customerId: Int): Customer? {
        var customer: Customer? = null
        for (index in 0..customerList.size) {
            if (customerList[index].id == customerId) {
                customer = customerList[index]
                break
            }
        }
        return customer
    }
}