package com.example.test.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun onLoginClicked(email: String, password: String) {
        // Simula la validación
        _loginResult.value = email == "admin@example.com" && password == "123456"
    }
}