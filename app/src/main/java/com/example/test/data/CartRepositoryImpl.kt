package com.example.test.data

import com.example.test.domain.CartUseCase
import com.example.test.domain.Product

class CartRepositoryImpl(private val cartUseCase: CartUseCase) {

    fun addProductToCart(product: Product) {
        cartUseCase.addProduct(product)
    }

    fun removeProductFromCart(product: Product) {
        cartUseCase.removeProduct(product)
    }

    fun getCartItems(): Map<Product, Int> {
        return cartUseCase.getCartItems()
    }

    fun clearCart() {
        cartUseCase.clearCart()
    }
}