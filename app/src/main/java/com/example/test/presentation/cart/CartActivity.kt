package com.example.test.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.domain.CartItem
import com.example.test.presentation.product.product.ProductListActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initRecyclerView()
        setupHomeButton()
    }

    private fun initRecyclerView() {
        val cartItems = intent.getParcelableArrayListExtra<CartItem>("selectedProducts") ?: emptyList()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartAdapter(cartItems)
    }

    private fun setupHomeButton() {
        val btnHome = findViewById<Button>(R.id.btnHome)
        btnHome.setOnClickListener {
            // Navegar a la lista de productos
            val intent = Intent(this, ProductListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}