package com.example.test.presentation.product.product

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.presentation.login.LoginActivity
import com.example.test.data.ProductRepositoryImpl

class ProductListActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private val viewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(ProductRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        initRecyclerView()
        setupLogoutButton()
        observeViewModel()

        viewModel.loadProducts()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productAdapter = ProductAdapter { product ->
            Toast.makeText(this, "Producto: ${product.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = productAdapter
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
            productAdapter.submitList(products)
        }
    }
}