package com.example.poplibs.domain

interface LoginApi {
    fun login(login: String, password: String): Boolean
    fun newUser(login: String, password: String): Boolean
    fun restorePassword(email: String): Boolean
    fun logout(): Boolean
}