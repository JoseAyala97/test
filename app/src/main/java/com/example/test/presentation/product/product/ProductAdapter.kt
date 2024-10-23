package com.example.test.presentation.product.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemProductBinding
import com.example.test.domain.Product

class ProductAdapter(
    private val onQuantityChanged: (Product, Int) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentQuantity = 0

        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = "$${product.price}"
            binding.cbSelect.isChecked = false
            binding.tvQuantity.text = currentQuantity.toString()

            binding.btnPlus.setOnClickListener {
                currentQuantity++
                binding.tvQuantity.text = currentQuantity.toString()
                onQuantityChanged(product, currentQuantity)
            }

            binding.btnMinus.setOnClickListener {
                if (currentQuantity > 0) {
                    currentQuantity--
                    binding.tvQuantity.text = currentQuantity.toString()
                    onQuantityChanged(product, currentQuantity)
                }
            }

            binding.cbSelect.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    currentQuantity = 0
                    binding.tvQuantity.text = "0"
                    onQuantityChanged(product, 0)
                }
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}