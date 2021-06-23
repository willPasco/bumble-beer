package com.will.bumblebeer.ui.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.AdapterItemCustomerBinding
import com.will.bumblebeer.domain.entities.Customer

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    private var customerList: List<Customer> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder =
        CustomerViewHolder(
            AdapterItemCustomerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = customerList.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) =
        holder.bind(customerList[position])

    fun updateCustomers(updatedList: List<Customer>) {
        customerList = updatedList
        notifyDataSetChanged()
    }

    inner class CustomerViewHolder(private val binding: AdapterItemCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(customer: Customer) {
            binding.textviewCustomerId.text = String.format(
                binding.root.context.getString(R.string.customer_id_label), customer.id
            )
            binding.textviewCustomerName.text = String.format(
                binding.root.context.getString(R.string.customer_name_label), customer.name
            )
        }
    }
}
