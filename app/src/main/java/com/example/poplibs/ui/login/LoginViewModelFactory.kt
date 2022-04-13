package com.example.poplibs.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.poplibs.domain.LoginUseCase

class LoginViewModelFactory (
    private val loginUseCase: LoginUseCase
        ) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return LoginViewModel(loginUseCase) as T
    }
}