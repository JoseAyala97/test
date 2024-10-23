package com.example.test.presentation.product.product

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.ProductRepositoryImpl
import com.example.test.domain.CartItem
import com.example.test.domain.Product
import com.example.test.presentation.cart.CartActivity
import com.example.test.presentation.login.LoginActivity

class ProductListActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private val viewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(ProductRepositoryImpl())
    }

    private val selectedProducts = mutableMapOf<Product, Int>()  // Mapa para guardar productos seleccionados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        initRecyclerView()
        setupLogoutButton()
        setupCartButton()  // Asegúrate de llamar esta función.
        observeViewModel()

        viewModel.loadProducts()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Solo pasamos el callback, no una lista inicial.
        productAdapter = ProductAdapter { product, quantity ->
            updateSelectedProducts(product, quantity)
        }

        recyclerView.adapter = productAdapter
    }

    private fun setupCartButton() {
        val btnCart = findViewById<Button>(R.id.btnCart)
        btnCart.setOnClickListener {
            val selectedProductsList = selectedProducts.entries.map {
                CartItem(it.key, it.value)
            }

            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("selectedProducts", ArrayList(selectedProductsList))
            startActivity(intent)
        }
    }

    private fun setupLogoutButton() {
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun observeViewModel() {
        viewModel.products.observe(this) { products ->
            productAdapter.submitList(products)  // Ahora funciona correctamente.
        }
    }

    private fun updateSelectedProducts(product: Product, quantity: Int) {
        if (quantity > 0) {
            selectedProducts[product] = quantity
        } else {
            selectedProducts.remove(product)
        }
    }
}