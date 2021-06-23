package com.will.bumblebeer.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will.bumblebeer.databinding.AdapterItemHomeBinding
import com.will.bumblebeer.domain.entities.JoinProductCustomer

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var productCustomer: List<JoinProductCustomer> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            AdapterItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = productCustomer.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(productCustomer[position])
    }

    fun updateItems(updatedList: List<JoinProductCustomer>) {
        productCustomer = updatedList
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: AdapterItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(joinProductCustomer: JoinProductCustomer) {
            binding.textviewCustomerName.text = joinProductCustomer.customerName
            binding.textviewProductDescription.text = joinProductCustomer.productDescription
        }
    }
}