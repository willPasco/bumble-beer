package com.will.bumblebeer.ui.product


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.AdapterItemProductBinding
import com.will.bumblebeer.domain.entities.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            AdapterItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(productList[position])

    fun updateProducts(updatedList: List<Product>) {
        productList = updatedList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: AdapterItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.textviewProductId.text = String.format(
                binding.root.context.getString(R.string.product_id_label), product.id
            )
            binding.textviewProductDescription.text = String.format(
                binding.root.context.getString(R.string.product_description_label), product.description
            )
            binding.textviewProductCustomerId.text = String.format(
                binding.root.context.getString(R.string.product_customer_id_label), product.customerId
            )
        }
    }
}
