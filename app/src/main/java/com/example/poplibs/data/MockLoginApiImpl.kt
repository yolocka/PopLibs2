package com.example.poplibs.data

import com.example.poplibs.domain.LoginApi

class MockLoginApiImpl : LoginApi {
    override fun login(login: String, password: String): Boolean {
        Thread.sleep(2000)
        return login == password
    }

    override fun newUser(login: String, password: String): Boolean {
        Thread.sleep(2000)
        return login.isNotEmpty()
    }

    override fun restorePassword(email: String): Boolean {
        Thread.sleep(2000)
        return email.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(2000)
        return true
    }
}