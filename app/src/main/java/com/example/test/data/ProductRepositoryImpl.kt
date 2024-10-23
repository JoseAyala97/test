package com.example.test.data

import com.example.test.domain.Product
import com.example.test.domain.ProductRepository

class ProductRepositoryImpl : ProductRepository {
    override fun getProducts(): List<Product> {
        // Datos simulados
        return listOf(
            Product(1, "Producto A", 10.99),
            Product(2, "Producto B", 25.49),
            Product(3, "Producto C", 15.00),
            Product(4, "Producto D", 30.00)
        )
    }
}