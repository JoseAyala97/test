package com.example.test.domain

class CartUseCase {
    private val cart = mutableMapOf<Product, Int>()

    fun addProduct(product: Product) {
        val currentQuantity = cart[product] ?: 0
        cart[product] = currentQuantity + 1
    }

    fun removeProduct(product: Product) {
        val currentQuantity = cart[product] ?: 0
        if (currentQuantity > 1) {
            cart[product] = currentQuantity - 1
        } else {
            cart.remove(product)
        }
    }

    fun getCartItems(): Map<Product, Int> {
        return cart
    }

    fun clearCart() {
        cart.clear()
    }
}