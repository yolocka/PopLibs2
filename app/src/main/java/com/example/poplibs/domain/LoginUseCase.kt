package com.example.poplibs.domain

interface LoginUseCase {
    fun login(login: String, password: String, callback: (Boolean) -> Unit)
    fun credentialsConfirmation(login: String, callback: (Boolean) -> Unit)
    fun newUserCreate(login: String, password: String, callback: (Boolean) -> Unit)
}