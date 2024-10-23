package com.example.test.domain

interface ProductRepository {
    fun getProducts(): List<Product>
}